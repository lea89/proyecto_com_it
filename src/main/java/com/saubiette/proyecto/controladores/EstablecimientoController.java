package com.saubiette.proyecto.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.saubiette.proyecto.entidades.Establecimiento;
import com.saubiette.proyecto.repositorios.EstablecimientoRepositorio;

@Controller
public class EstablecimientoController {

	EstablecimientoRepositorio establecimientoRepositorio;

	@Autowired
	public EstablecimientoController(EstablecimientoRepositorio establecimientoRepositorio) {
		this.establecimientoRepositorio = establecimientoRepositorio;
	}

	public Iterable<Establecimiento> traerEstablecimientos() {
		return establecimientoRepositorio.findAll();
	}

	public Establecimiento traerEstablecimiento(int idEstablecimiento) {

		Optional<Establecimiento> establecimiento = establecimientoRepositorio.findById(idEstablecimiento);

		if (establecimiento.isPresent())
			return establecimiento.get();
		return null;
	}

	public Establecimiento crearEstablecimiento(Establecimiento establecimiento) {

		return establecimientoRepositorio.save(establecimiento);
	}

	public void eliminarEstablecimiento(Establecimiento establecimiento) {
		establecimientoRepositorio.deleteById(establecimiento.getId());
	}

	public Establecimiento actualizarRol(Establecimiento establecimiento) {

		return establecimientoRepositorio.save(establecimiento);
	}

}
