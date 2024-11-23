package com.project.pedidos.domain.event;

import lombok.Value;

@Value
public class StockReducidoEvent {
    String productoId; // ID del producto
    int cantidadReducida; // Cantidad reducida del stock
    int stockRestante;   // Stock restante después de la operación

    public StockReducidoEvent(String productoId, int cantidadReducida, int stockRestante) {
        if (stockRestante < 0) {
            throw new IllegalArgumentException("El stock restante no puede ser negativo");
        }
        this.productoId = productoId;
        this.cantidadReducida = cantidadReducida;
        this.stockRestante = stockRestante;
    }
}
