package com.project.pedidos.domain.model;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
@ToString
public class Usuario {

    private final UUID id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private EstadoUsuario estado;
    private final LocalDateTime fechaRegistro;

    // Constructor privado para control de creación
    private Usuario(String nombre, String correoElectronico, String contrasena) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.estado = EstadoUsuario.PENDIENTE_CONFIRMACION;
        this.fechaRegistro = LocalDateTime.now();
    }

    public static Usuario registrarUsuario(String nombre, String correoElectronico, String contrasena) {
        validarCorreo(correoElectronico);
        validarContrasena(contrasena);
        return new Usuario(nombre, correoElectronico, cifrarContrasena(contrasena));
    }

    public void activarCuenta() {
        if (this.estado != EstadoUsuario.PENDIENTE_CONFIRMACION) {
            throw new IllegalStateException("Solo usuarios pendientes pueden ser activados.");
        }
        this.estado = EstadoUsuario.ACTIVO;
    }

    public static void validarCorreo(String correo) {
        if (!Pattern.matches("^[\\w-.]+@[\\w-]+\\.+[\\w-]{2,}$", correo)) {
            throw new IllegalArgumentException("Correo electrónico inválido.");
        }
    }

    public static void validarContrasena(String contrasena) {
        if (contrasena.length() < 8 || !contrasena.matches(".*[A-Z].*") || !contrasena.matches(".*\\d.*")) {
            throw new IllegalArgumentException("La contraseña no cumple los criterios de seguridad.");
        }
    }

    private static String cifrarContrasena(String contrasena) {
        // Lógica de cifrado
        return "CIFRADO_" + contrasena;
    }

    public boolean esEstadoPendiente() {
        return this.estado == EstadoUsuario.PENDIENTE_CONFIRMACION;
    }

    public enum EstadoUsuario {
        ACTIVO,
        INACTIVO,
        PENDIENTE_CONFIRMACION
    }
}
