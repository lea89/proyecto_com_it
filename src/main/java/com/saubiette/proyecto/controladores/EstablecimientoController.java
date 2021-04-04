package com.saubiette.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.saubiette.proyecto.entidades.Establecimiento;
import com.saubiette.proyecto.entidades.Rol;
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

	public Establecimiento crearEstablecimiento(Establecimiento rol) {

		return establecimientoRepositorio.save(rol);
	}

	public void eliminarRol(Rol rol) {
		establecimientoRepositorio.deleteById(rol.getId());
	}

	public Establecimiento actualizarRol(Establecimiento rol) {

		return establecimientoRepositorio.save(rol);
	}

}
