package com.example.appeasyshop.core.entities;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private String descripcion;

    Producto(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

}
