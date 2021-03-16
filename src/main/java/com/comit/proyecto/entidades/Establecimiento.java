package com.comit.proyecto.entidades;

import java.util.ArrayList;

public class Establecimiento {
	private int id;

	private String direccion;
	private String nombre;
	private int tipo;

	

	protected static ArrayList<Establecimiento> lista_establecimientos = new ArrayList<Establecimiento>();

	public Establecimiento(String direccion, String nombre, int tipo) {
		super();
		this.direccion = direccion;
		this.nombre = nombre;
		this.tipo = tipo;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}