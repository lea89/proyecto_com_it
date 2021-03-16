package com.comit.proyecto.entidades;

//import java.text.SimpleDateFormat;
import java.util.Date;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String clave;
    private int id_rol;    
    private String obraSocial;
    private String nSocio;
    private String dni;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

	public String getnSocio() {
		return nSocio;
	}

	public void setnSocio(String nSocio) {
		this.nSocio = nSocio;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getIdRol() {
		return this.id_rol;
	}

	public void setIdRol(int id_rol) {
		this.id_rol = id_rol;
	}

}