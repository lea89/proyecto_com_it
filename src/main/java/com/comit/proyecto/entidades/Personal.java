package com.comit.proyecto.entidades;

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

	public String getLegajo() {
		return this.legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getId_area() {
		return this.id_area;
	}

	public void setId_area(int id_area) {
		this.id_area = id_area;
	}

	public String getDni() {
		
		return this.dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}

}