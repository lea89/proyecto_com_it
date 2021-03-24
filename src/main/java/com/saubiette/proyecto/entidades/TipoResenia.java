package com.saubiette.proyecto.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TipoResenia")
@NoArgsConstructor
@AllArgsConstructor
public class TipoResenia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String resenia;

}