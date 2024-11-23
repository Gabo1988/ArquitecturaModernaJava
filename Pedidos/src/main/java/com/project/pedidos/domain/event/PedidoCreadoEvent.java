package com.project.pedidos.domain.event;

import lombok.Value;
import java.util.UUID;
import java.time.Instant;

@Value
public class PedidoCreadoEvent {
    UUID pedidoId;
    Long usuarioId;
    Instant fechaCreacion;

    public PedidoCreadoEvent(UUID pedidoId, Long usuarioId) {
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
        this.fechaCreacion = Instant.now();
    }
}
