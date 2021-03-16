package com.comit.proyecto.entidades;

import java.util.ArrayList;

public class Clinica extends Establecimiento {
	ArrayList<Clinica> lista_clinicas;
	private String nombre;
	private String direccion;

	public Clinica(String nombre, String direccion, int tipo) {
		super(nombre, direccion, tipo);
	}
}