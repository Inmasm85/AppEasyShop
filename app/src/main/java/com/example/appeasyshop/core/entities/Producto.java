package com.example.appeasyshop.core.entities;

public class Producto {

    private int id;
    private String nombre;
    private double precioUD;
    private double precioKg;
    private String descripcion;

    Producto(String nombre, String descripcion, double precioUD, double precioKg) {
        this.nombre = nombre;
        this.precioUD = precioUD;
        this.precioKg = precioKg;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioUD() {
        return precioUD;
    }

    public double getPrecioKg() {
        return precioKg;
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