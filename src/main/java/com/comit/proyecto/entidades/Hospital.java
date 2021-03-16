package com.comit.proyecto.entidades;

import java.util.ArrayList;

public class Hospital extends Establecimiento {
    ArrayList<Hospital> lista_hospitales;
    
    public Hospital(String nombre, String direccion, int tipo) {
    	super(nombre, direccion, tipo);
    }
}