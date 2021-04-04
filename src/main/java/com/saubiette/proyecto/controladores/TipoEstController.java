package com.saubiette.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.saubiette.proyecto.entidades.TipoEstablecimiento;
import com.saubiette.proyecto.repositorios.TipoEstRepositorio;

@Controller
public class TipoEstController {

	TipoEstRepositorio tipoEstRepositorio;

	@Autowired
	public TipoEstController(TipoEstRepositorio tipoEstRepositorio) {
		this.tipoEstRepositorio = tipoEstRepositorio;
	}

	public Iterable<TipoEstablecimiento> traerTipos() {
		return tipoEstRepositorio.findAll();
	}

	public TipoEstablecimiento crearTipo(TipoEstablecimiento tipo) {

		return tipoEstRepositorio.save(tipo);
	}

	public void eliminarTipo(TipoEstablecimiento tipo) {
		tipoEstRepositorio.deleteById(tipo.getId());
	}

	public TipoEstablecimiento actualizarTipo(TipoEstablecimiento tipo) {

		return tipoEstRepositorio.save(tipo);
	}
}
