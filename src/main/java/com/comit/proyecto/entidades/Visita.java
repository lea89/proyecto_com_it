package com.comit.proyecto.entidades;

import java.util.Date;

public class Visita {
    private int id;
    private Date fecha;
    private int id_establecimiento;
    private int id_usuario;
    private int id_profesional;
    private boolean estado;


	public Visita(Date fecha, int id_establecimiento, int id_usuario,int id_profesional) {
		this.fecha = fecha;
		this.id_establecimiento = id_establecimiento;
		this.id_usuario = id_usuario;
		this.id_profesional = id_profesional;
		this.estado = true;
	}
	
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getId_profesional() {
		return id_profesional;
	}

	public void setId_profesional(int id_profesional) {
		this.id_profesional = id_profesional;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getId_establecimiento() {
		return this.id_establecimiento;
	}

	public void setId_establecimiento(int id_establecimiento) {
		this.id_establecimiento = id_establecimiento;
	}

	public int getId_usuario() {
		return this.id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

}