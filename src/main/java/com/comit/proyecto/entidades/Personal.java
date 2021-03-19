package com.comit.proyecto.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class Personal {
	private int id;
    private String legajo;
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private int id_area;
   

    public Personal (int id, String legajo, String nombre, String apellido, String direccion, String dni, int id_area) {
        super();
        this.id_area = id;
        this.dni = dni;
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;

    }

}