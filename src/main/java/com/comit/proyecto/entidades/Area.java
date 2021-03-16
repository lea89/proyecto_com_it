package com.comit.proyecto.entidades;

public class Area {
    private int id;
    private String area;
    private int id_establecimiento;

    public Area(int id, String area, int id_establecimiento) {
        this.id = id;
        this.area = area;
        this.id_establecimiento = id_establecimiento;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getId_establecimiento() {
        return this.id_establecimiento;
    }

    public void setId_establecimiento(int id_establecimiento) {
        this.id_establecimiento = id_establecimiento;
    }

}