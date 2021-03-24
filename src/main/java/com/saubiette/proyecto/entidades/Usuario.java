package com.saubiette.proyecto.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import java.text.SimpleDateFormat;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Usuario")
public class Usuario implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	private String nombre;
	private String apellido;
	@Column(unique = true)
	private String email;
	private String clave;
	private String obra_social;
	private String n_socio;
	@Column(unique = true)
	private String dni;

	@OneToOne
	private Rol rol;

}