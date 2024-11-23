package com.project.pedidos.domain.service;

import com.project.pedidos.domain.model.Carrito;
import com.project.pedidos.domain.model.Producto;

import java.util.List;

public class CarritoService implements ICarritoService {

    @Override
    public void agregarProductoAlCarrito(Carrito carrito, Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("Producto no válido");
        }

        // Verificar si el carrito ya contiene el producto
        List<Producto> productosEnCarrito = carrito.getProductos();
        if (!productosEnCarrito.contains(producto)) {
            // Si el producto no está en el carrito, lo agregamos
            productosEnCarrito.add(producto);
        }

        // Verificar si hay stock disponible
        if (!producto.verificarDisponibilidad(cantidad)) {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        // Aquí puedes agregar la cantidad al carrito si es necesario
        // Suponiendo que el carrito lleva el conteo de cantidad por producto
    }

    @Override
    public void eliminarProductoDelCarrito(Carrito carrito, Producto producto) {
        if (producto == null || !carrito.getProductos().contains(producto)) {
            throw new IllegalArgumentException("El producto no está en el carrito");
        }
        // Eliminar el producto del carrito
        carrito.getProductos().remove(producto);
    }

    @Override
    public void vaciarCarrito(Carrito carrito) {
        carrito.getProductos().clear();  // Limpiar todos los productos
    }

    @Override
    public double calcularTotal(Carrito carrito) {
        return carrito.getProductos().stream()
                .map(producto -> producto.getPrecio().doubleValue())  // Sumamos el precio de cada producto
                .reduce(0.0, Double::sum);  // Reducimos a un solo total
    }
}
