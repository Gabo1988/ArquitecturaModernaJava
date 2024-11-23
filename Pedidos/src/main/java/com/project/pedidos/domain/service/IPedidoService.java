package com.project.pedidos.domain.service;

import com.project.pedidos.domain.model.Pedido;
import com.project.pedidos.domain.model.Carrito;
import com.project.pedidos.domain.model.Direccion;

public interface IPedidoService {
    /**
     * Crea un nuevo pedido a partir del carrito y la dirección de entrega proporcionada.
     *
     * @param carrito el carrito con los productos
     * @param direccionEntrega la dirección a la que se enviará el pedido
     * @return el pedido creado
     */
    Pedido crearPedido(Carrito carrito, Direccion direccionEntrega);

    /**
     * Cancela un pedido existente y restablece el stock de los productos.
     *
     * @param pedido el pedido a cancelar
     */
    void cancelarPedido(Pedido pedido);
}
