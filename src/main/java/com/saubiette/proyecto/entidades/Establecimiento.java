package com.saubiette.proyecto.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity

@Table(name = "Establecimiento")
@AllArgsConstructor
@NoArgsConstructor
public class Establecimiento implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;

	private String direccion;
	private String nombre;

	@OneToOne
	private TipoEstablecimiento tipo;

	@OneToMany(mappedBy = "establecimiento")
	private List<Visita> visita;

	@OneToMany(mappedBy = "establecimiento")
	private List<Area> areas;

	@OneToMany(mappedBy = "establecimiento")
	private List<Personal> personal;

}