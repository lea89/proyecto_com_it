package com.saubiette.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.saubiette.proyecto.entidades.Personal;
import com.saubiette.proyecto.repositorios.PersonalRepositorio;

@Controller
public class PersonalController {
	PersonalRepositorio personalRepositorio;

	@Autowired
	public PersonalController(PersonalRepositorio personalRepositorio) {
		this.personalRepositorio = personalRepositorio;
	}

	public Iterable<Personal> traerPersonal() {
		return personalRepositorio.findAll();
	}

	public Personal traerPersonal(int idPersonal) {
		return personalRepositorio.findById(idPersonal).get();
	}

	public Personal crearPersonal(Personal personal) {

		return personalRepositorio.save(personal);
	}

	public void eliminarPersonal(Personal personal) {
		personalRepositorio.deleteById(personal.getId());
	}

	public Personal actualizarPersonal(Personal personal) {

		return personalRepositorio.save(personal);
	}
}
