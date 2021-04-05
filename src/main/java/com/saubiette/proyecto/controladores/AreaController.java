package com.saubiette.proyecto.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.saubiette.proyecto.entidades.Area;
import com.saubiette.proyecto.repositorios.AreaRepositorio;

@Controller
public class AreaController {
	AreaRepositorio areaRepositorio;

	@Autowired
	public AreaController(AreaRepositorio areaRepositorio) {
		this.areaRepositorio = areaRepositorio;
	}

	public Iterable<Area> traerAreas() {
		return areaRepositorio.findAll();
	}

	public Area traerArea(int idArea) {

		Optional<Area> area = areaRepositorio.findById(idArea);

		if (area.isPresent())
			return area.get();
		return null;
	}

	public Area crearArea(Area area) {

		return areaRepositorio.save(area);
	}

	public void eliminarArea(Area area) {
		areaRepositorio.deleteById(area.getId());
	}

	public Area actualizarRol(Area area) {

		return areaRepositorio.save(area);
	}
}
