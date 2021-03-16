package com.comit.proyecto.entidades;

public class Resenia {
    private int id;
    private String comentario;
    private int puntaje;
    private int id_usuario;
    private int id_tipo_resenia;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int getIdUsuario() {
		return this.id_usuario;
	}

	public void setIdUsuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getIdTipoResenia() {
		return this.id_tipo_resenia;
	}

	public void setIdTipoResenia(int id_tipo_resenia) {
		this.id_tipo_resenia = id_tipo_resenia;
	}

}