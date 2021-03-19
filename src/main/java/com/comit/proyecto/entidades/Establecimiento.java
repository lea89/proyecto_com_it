package com.comit.proyecto.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Establecimiento {
	private int id;

	private String direccion;
	private String nombre;
	private int tipo;

	public Establecimiento(String direccion, String nombre, int tipo) {
		super();
		this.direccion = direccion;
		this.nombre = nombre;
		this.tipo = tipo;
	}



}