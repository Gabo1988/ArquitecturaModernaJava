package com.project.pedidos.domain.model;

import com.project.pedidos.domain.model.enums.EstadoPedido;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    private Long id;
    private Long clienteId;
    private List<Producto> productos;
    private LocalDate fecha;
    private EstadoPedido estado; // Ahora es un Enum en lugar de un String
    private Double total;
    private Direccion direccionEnvio;
    private String metodoPago;
    private LocalDate fechaConfirmacion;
    private LocalDate fechaCancelacion;

    // Constructor
    public Pedido(Long id, Long clienteId, List<Producto> productos, LocalDate fecha, EstadoPedido estado,
                  Double total, Direccion direccionEnvio, String metodoPago, LocalDate fechaConfirmacion,
                  LocalDate fechaCancelacion) {
        this.id = id;  // Puede ser null si el ID se genera automáticamente
        this.clienteId = clienteId;
        this.productos = productos;
        this.fecha = fecha != null ? fecha : LocalDate.now();  // Si no se pasa fecha, se usa la actual
        this.estado = estado != null ? estado : EstadoPedido.PENDIENTE; // Estado por defecto si es null
        this.total = total != null ? total : 0.0; // Asignar 0.0 si el total es null
        this.direccionEnvio = direccionEnvio;
        this.metodoPago = metodoPago != null ? metodoPago : "No especificado";  // Por defecto
        this.fechaConfirmacion = fechaConfirmacion;
        this.fechaCancelacion = fechaCancelacion;
    }

    // Métodos
    public void validarDisponibilidad() {
        // Lógica para validar la disponibilidad de los productos
    }

    public void procesarPago() {
        // Lógica para procesar el pago
    }

    public void enviarConfirmacion() {
        // Lógica para enviar un correo de confirmación
    }

    public void cancelar() {
        if (EstadoPedido.PENDIENTE.equals(this.estado)) {
            this.estado = EstadoPedido.CANCELADO;
            this.fechaCancelacion = LocalDate.now();
            // Lógica para emitir un reembolso
        }
    }

    public void emitirReembolso() {
        // Lógica para emitir el reembolso
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
