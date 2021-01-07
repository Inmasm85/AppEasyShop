package com.example.appeasyshop.core.entities;

import java.util.Collections;
import java.util.List;

public class Categoria {

    private int id;
    private String nombre;
    private List<Producto> productos;

    public Categoria(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
