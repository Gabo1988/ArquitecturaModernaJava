package com.project.pedidos.domain.service;

import com.project.pedidos.domain.model.Producto;
import com.project.pedidos.domain.exception.PedidoException;
import com.project.pedidos.domain.model.enums.EstadoProducto; // Importar el Enum separado

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductService implements IProductoService {

    // Simularemos un repositorio con un Map (para simplificar en este ejemplo).
    private final Map<UUID, Producto> repositorio = new HashMap<>();

    @Override
    public void reducirStock(UUID productId, int cantidad) {
        Producto producto = obtenerProducto(productId);
        if (!producto.verificarDisponibilidad(cantidad)) {
            throw new PedidoException("Stock insuficiente para el producto: " + producto.getNombre());
        }
        producto.reducirStock(cantidad);
    }

    @Override
    public void reponerStock(UUID productId, int cantidad) {
        Producto producto = obtenerProducto(productId);
        producto.setStockDisponible(producto.getStockDisponible() + cantidad);
    }

    @Override
    public void cambiarEstado(UUID productId, String nuevoEstado) {
        Producto producto = obtenerProducto(productId);
        try {
            // Convertir el String a EstadoProducto
            EstadoProducto estado = EstadoProducto.valueOf(nuevoEstado.toUpperCase());
            producto.cambiarEstado(estado);
        } catch (IllegalArgumentException e) {
            throw new PedidoException("Estado inválido: " + nuevoEstado);
        }
    }

    @Override
    public Producto obtenerProducto(UUID productId) {
        Producto producto = repositorio.get(productId);
        if (producto == null) {
            throw new PedidoException("Producto no encontrado con ID: " + productId);
        }
        return producto;
    }

    // Método adicional para agregar productos al repositorio (para pruebas).
    public void agregarProducto(Producto producto) {
        repositorio.put(producto.getProductId(), producto);
    }
}
