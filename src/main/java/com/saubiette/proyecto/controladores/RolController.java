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

	public Rol traerRolesById(int id) {
		if (rolRepositorio.findById(id).isPresent())
			return rolRepositorio.findById(id).get();
		return null;
	}

	public Rol crearRol(Rol rol) {

		return rolRepositorio.save(rol);
	}

	public void eliminarRol(Rol rol) {
		rolRepositorio.deleteById(rol.getId());
	}

	public Rol actualizarRol(Rol rol) {

		return rolRepositorio.save(rol);
	}
}
