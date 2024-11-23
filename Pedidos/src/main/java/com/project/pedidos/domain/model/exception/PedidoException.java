package com.project.pedidos.domain.exception;

/**
 * Excepción personalizada para manejar errores específicos en el contexto de Pedidos.
 */
public class PedidoException extends RuntimeException {

    // Constructor por defecto
    public PedidoException() {
        super();
    }

    // Constructor con mensaje personalizado
    public PedidoException(String message) {
        super(message);
    }

    // Constructor con mensaje personalizado y causa (otra excepción)
    public PedidoException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor con causa (otra excepción)
    public PedidoException(Throwable cause) {
        super(cause);
    }
}
