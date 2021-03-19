package com.comit.proyecto.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class Area {
    private int id;
    private String area;
    private int id_establecimiento;

    public Area(int id, String area, int id_establecimiento) {
        this.id = id;
        this.area = area;
        this.id_establecimiento = id_establecimiento;
    }

}