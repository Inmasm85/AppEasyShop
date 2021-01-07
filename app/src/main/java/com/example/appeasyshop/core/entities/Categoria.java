package com.example.appeasyshop.core.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Categoria implements Serializable {

    private int id;
    private String nombre;
    private String pathToImage;
    private List<Producto> productos;

    public Categoria(int id, String nombre, String pathToImage){
        this.id = id;
        this.nombre = nombre;
        this.pathToImage = pathToImage;
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

    public String getPathToImage() {
        return pathToImage;
    }
}
