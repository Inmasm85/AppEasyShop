package com.example.appeasyshop.core.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

    private int id;
    private String telefono;
    private String nombreCliente;
    private List<LineaPedido> productos;

    public Pedido() {
        productos = new ArrayList<>();
    }

    public void anyadir(LineaPedido nuevaLinea) {
        LineaPedido lineaAux = null;
        int i = 0;

        while (lineaAux == null && i < productos.size()) {
            LineaPedido lineaPedido = productos.get(i);

            if (lineaPedido.equals(nuevaLinea))
                lineaAux = lineaPedido;

            i++;
        }

        if (lineaAux == null)
            productos.add(nuevaLinea);
        else {
            int suma = lineaAux.getCantidad() + nuevaLinea.getCantidad();
            lineaAux.setCantidad(suma);
        }

    }

    public void quitar(LineaPedido linea) {
        this.productos.remove(linea);
    }

    public List<LineaPedido> getLineas() {
        return Collections.unmodifiableList(productos);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public double getTotal() {

        if (productos.isEmpty())
            return 0;
        else
            return productos.stream()
                    .mapToDouble((l) -> l.getCantidad() * l.getProducto().getPrecio())
                    .sum();
    }
}
