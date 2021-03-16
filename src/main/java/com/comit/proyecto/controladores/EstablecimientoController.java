package com.comit.proyecto.controladores;

import java.util.ArrayList;

import com.comit.proyecto.modelos.EstablecimientoDB;
import com.comit.proyecto.entidades.Establecimiento;


public class EstablecimientoController {
	public static ArrayList<Establecimiento> getLista_establecimientos() {
		return EstablecimientoDB.getEstablecimientos();
	}

	public static boolean crearEstablecimiento(Establecimiento establecimiento) {

		return EstablecimientoDB.crearEstablecimiento(establecimiento);
		
	}
	
	public static boolean editarEstablecimiento(Establecimiento establecimiento) {
		
		return EstablecimientoDB.updateEstablecimiento(establecimiento);
	}
	
	public static boolean eliminarEstablecimiento(Establecimiento establecimiento) {
		return EstablecimientoDB.eliminarEstablecimiento(establecimiento.getId());
	}
}
