package com.project.pedidos.domain.service;

import com.project.pedidos.domain.model.Pedido;
import com.project.pedidos.domain.model.Carrito;
import com.project.pedidos.domain.model.Direccion;
import com.project.pedidos.domain.model.Producto;
import com.project.pedidos.domain.exception.PedidoException;
import com.project.pedidos.domain.model.enums.EstadoPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoService implements IPedidoService {

    @Override
    public Pedido crearPedido(Carrito carrito, Direccion direccionEntrega) {
        // Verificar que el carrito tiene productos
        if (!carrito.tieneProductos()) {
            throw new PedidoException("No se puede crear un pedido vacío.");
        }

        // Obtener los productos y calcular el total del pedido
        List<Producto> productos = carrito.getProductos();
        BigDecimal total = carrito.calcularTotal();

        // Crear el nuevo pedido, pasando los parámetros correctos
        Pedido pedido = new Pedido(
                null, // ID puede ser null, luego se generará automáticamente
                carrito.getUsuarioId(), // ID del usuario (cliente)
                productos,
                LocalDate.now(), // Fecha actual
                EstadoPedido.PENDIENTE, // Estado inicial del pedido
                total.doubleValue(), // Total como Double
                direccionEntrega,
                null, // Metodo de pago, puede ser null por el momento
                null, // Fecha de confirmación, puede ser null por el momento
                null // Fecha de cancelación, puede ser null por el momento
        );

        // Reducir el stock de los productos en el carrito
        for (Producto producto : productos) {
            producto.reducirStock(1); // Reducir el stock en 1 por cada producto
        }

        // Devolver el pedido creado
        return pedido;
    }

    @Override
    public void cancelarPedido(Pedido pedido) {
        // Verificar si el pedido ya fue cancelado
        if (pedido.getEstado() == EstadoPedido.CANCELADO) {
            throw new PedidoException("El pedido ya ha sido cancelado.");
        }

        // Cambiar el estado del pedido a cancelado
        pedido.cancelar();

        // Reponer el stock de los productos cancelados
        for (Producto producto : pedido.getProductos()) {
            producto.reponerStock(1); // Reponer el stock de 1 unidad
        }
    }
}
