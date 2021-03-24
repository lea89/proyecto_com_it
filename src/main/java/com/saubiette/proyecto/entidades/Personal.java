package com.saubiette.proyecto.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "Personal")
public class Personal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String legajo;
	private String nombre;
	private String apellido;
	private String direccion;
	private String dni;

	@ManyToOne
	@JoinColumn(name = "id_area")
	private Area area;

	@ManyToOne
	@JoinColumn(name = "id_establecimiento")
	private Establecimiento establecimiento;

}