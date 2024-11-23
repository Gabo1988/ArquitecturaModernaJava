package com.project.pedidos.domain.event;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class ProductoAgregadoACarritoEvent {
    Long carritoId;        // ID del carrito
    String productoId;     // ID del producto
    String nombreProducto; // Nombre del producto
    int cantidad;          // Cantidad añadida
    BigDecimal precioTotal; // Precio total de los productos añadidos

    public ProductoAgregadoACarritoEvent(Long carritoId, String productoId, String nombreProducto, int cantidad, BigDecimal precioUnitario) {
        this.carritoId = carritoId;
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioTotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}

