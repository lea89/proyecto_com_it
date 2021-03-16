package com.comit.proyecto.entidades;

import java.util.ArrayList;


public class CentroSalud extends Establecimiento {
    
    private ArrayList<CentroSalud> lista_centros;

    public CentroSalud(String nombre, String direccion, int tipo) {
        super(direccion, nombre, tipo);
    }
}