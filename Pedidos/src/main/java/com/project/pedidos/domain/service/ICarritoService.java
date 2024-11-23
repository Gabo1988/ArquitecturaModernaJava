package com.project.pedidos.domain.service;

import com.project.pedidos.domain.model.Carrito;
import com.project.pedidos.domain.model.Producto;

public interface ICarritoService {
    void agregarProductoAlCarrito(Carrito carrito, Producto producto, int cantidad);
    void eliminarProductoDelCarrito(Carrito carrito, Producto producto);
    void vaciarCarrito(Carrito carrito);
    double calcularTotal(Carrito carrito);
}
