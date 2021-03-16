package com.comit.proyecto.entidades;

public class Telefono {
    private int id;
    private String telefono;
    private int id_area;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_area() {
        return this.id_area;
    }

    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    public Telefono(String telefono, int id_area) {
        this.telefono = telefono;
        this.id_area = id_area;
    }

}