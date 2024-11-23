package com.project.pedidos.domain.event;

import lombok.Value;
import java.time.Instant;
import java.util.UUID;

@Value
public class PedidoCanceladoEvent {
    UUID pedidoId;      // ID del pedido cancelado
    Long usuarioId;     // Usuario que canceló el pedido
    Instant fechaCancelacion; // Fecha y hora de la cancelación
    String motivo;      // Motivo de la cancelación (opcional)

    public PedidoCanceladoEvent(UUID pedidoId, Long usuarioId, String motivo) {
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
        this.fechaCancelacion = Instant.now();
        this.motivo = motivo == null || motivo.isBlank() ? "Sin motivo especificado" : motivo;
    }
}
