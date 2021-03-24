package com.saubiette.proyecto.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
	private int tipo;

	@OneToMany(mappedBy = "establecimiento")
	private List<Visita> visita;

	@OneToMany(mappedBy = "establecimiento")
	private List<Area> area;

	@OneToMany(mappedBy = "establecimiento")
	private List<Personal> personal;

}