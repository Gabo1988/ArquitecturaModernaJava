package com.project.pedidos.domain.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Carrito {

    private Long id;
    private Long usuarioId;
    private List<Producto> productos;
    private BigDecimal total;

    public Carrito(Long usuarioId) {
        this.usuarioId = usuarioId;
        this.productos = new ArrayList<>();
        this.total = BigDecimal.ZERO;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (!producto.verificarDisponibilidad(cantidad)) {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre());
        }
        for (int i = 0; i < cantidad; i++) {
            productos.add(producto);
        }
        actualizarTotal();
    }

    public void eliminarProducto(Producto producto) {
        if (!productos.contains(producto)) {
            throw new IllegalArgumentException("El producto no está en el carrito.");
        }
        productos.remove(producto);
        actualizarTotal();
    }

    public void vaciarCarrito() {
        productos.clear();
        total = BigDecimal.ZERO;
    }

    public BigDecimal calcularTotal() {
        return total;
    }

    private void actualizarTotal() {
        total = productos.stream()
                .map(Producto::getPrecio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean tieneProductos() {
        return !productos.isEmpty();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}
