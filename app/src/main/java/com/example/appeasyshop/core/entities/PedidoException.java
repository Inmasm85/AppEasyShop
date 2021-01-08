package com.example.appeasyshop.core.entities;

public class PedidoException extends Exception {

    public static final int ERR_TELEFONO_INCORRECTO = 0;
    public static final int ERR_TELEFONO_VACIO = 1;
    public static final int ERR_NOMBRE_VACIO = 2;

    private int errCode;

    public PedidoException(String msg, int err_code) {
        super(msg);
        this.errCode = err_code;
    }

    public PedidoException(int err_code) {
        this.errCode = err_code;
    }

    public int getErrCode() {
        return errCode;
    }
}
