package com.project.pedidos.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.NonNull;

/**
 * Representa una dirección como un Value Object en el dominio.
 */
@Getter
@EqualsAndHashCode
@ToString
public class Direccion {

    @NonNull private final String calle;       // Calle
    @NonNull private final String ciudad;      // Ciudad
    @NonNull private final String estado;      // Estado/Provincia
    @NonNull private final String codigoPostal; // Código postal
    @NonNull private final String pais;        // País

    public Direccion(String calle, String ciudad, String estado, String codigoPostal, String pais) {
        if (calle.isBlank()) throw new IllegalArgumentException("La calle es obligatoria");
        if (ciudad.isBlank()) throw new IllegalArgumentException("La ciudad es obligatoria");
        if (estado.isBlank()) throw new IllegalArgumentException("El estado es obligatorio");
        if (codigoPostal.isBlank()) throw new IllegalArgumentException("El código postal es obligatorio");
        if (pais.isBlank()) throw new IllegalArgumentException("El país es obligatorio");

        this.calle = calle;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
    }
}
