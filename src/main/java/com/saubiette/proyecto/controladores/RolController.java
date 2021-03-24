package com.saubiette.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saubiette.proyecto.entidades.Rol;
import com.saubiette.proyecto.repositorios.RolRepositorio;

@Controller
@RequestMapping(path = "/roles")
public class RolController {

	RolRepositorio rolRepositorio;

	@Autowired
	public RolController(RolRepositorio rolRepositorio) {
		this.rolRepositorio = rolRepositorio;
	}

	public Iterable<Rol> traerRoles() {
		return rolRepositorio.findAll();
	}

	public Rol crearRol(Rol rol) {

		return rolRepositorio.save(rol);
	}
}
