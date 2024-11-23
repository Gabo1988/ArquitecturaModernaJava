package com.project.pedidos.domain.model;

import com.project.pedidos.domain.model.enums.EstadoProducto;  // Import del Enum separado
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    private UUID productId;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stockDisponible;
    private String categoria;
    private EstadoProducto estado;  // Se sigue utilizando el enum separado

    // Métodos getter adicionales
    public UUID getProductId() {
        return productId;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    // Métodos setter adicionales
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public boolean verificarDisponibilidad(int cantidad) {
        return this.stockDisponible >= cantidad;
    }

    public void reducirStock(int cantidad) {
        if (!verificarDisponibilidad(cantidad)) {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " + nombre);
        }
        this.stockDisponible -= cantidad;
    }

    public void reponerStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        this.stockDisponible += cantidad;
    }

    public void cambiarEstado(EstadoProducto nuevoEstado) {
        this.estado = nuevoEstado;
    }
}
