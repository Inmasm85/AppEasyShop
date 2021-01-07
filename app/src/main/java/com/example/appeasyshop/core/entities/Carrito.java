package com.example.appeasyshop.core.entities;

import java.util.Collections;
import java.util.List;

public class Carrito {

    public Pedido pedido;
    private static Carrito instance;

    private Carrito() {
        pedido = new Pedido();
    }

    public static Carrito getInstance() {

        if (instance == null)
            instance = new Carrito();

        return instance;
    }

    public void limpiar() {
        pedido = new Pedido();
    }

    public void comprar(Producto producto, int cantidad) {
        LineaPedido linea = new LineaPedido(producto, cantidad);
        pedido.anyadir(linea);
    }

    public double getTotal() {
        return pedido.getTotal();
    }

    public List<LineaPedido> getProductos() {
        return Collections.unmodifiableList(pedido.getLineas());
    }

    public void finalizar() {
        // TODO: guardar el pedido
    }


}
