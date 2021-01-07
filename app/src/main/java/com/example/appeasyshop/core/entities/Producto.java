package com.example.appeasyshop.core.entities;

import java.io.Serializable;
import java.util.Objects;

public abstract class Producto implements Serializable {

    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private String pathToImage;

    public Producto(int id, String nombre, String descripcion, double precio, String pathToImage) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.pathToImage = pathToImage;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getPathToImage() {
        return  pathToImage;
    }
}