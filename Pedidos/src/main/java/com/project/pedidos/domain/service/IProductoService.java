package com.project.pedidos.domain.service;

import com.project.pedidos.domain.model.Producto;

import java.util.UUID;

public interface IProductoService {

    /**
     * Reduce el stock de un producto.
     *
     * @param productId ID del producto
     * @param cantidad Cantidad a reducir
     */
    void reducirStock(UUID productId, int cantidad);

    /**
     * Repone el stock de un producto.
     *
     * @param productId ID del producto
     * @param cantidad Cantidad a reponer
     */
    void reponerStock(UUID productId, int cantidad);

    /**
     * Cambia el estado de un producto.
     *
     * @param productId ID del producto
     * @param nuevoEstado Nuevo estado del producto
     */
    void cambiarEstado(UUID productId, String nuevoEstado);

    /**
     * Obtiene los detalles de un producto.
     *
     * @param productId ID del producto
     * @return Producto encontrado
     */
    Producto obtenerProducto(UUID productId);
}
