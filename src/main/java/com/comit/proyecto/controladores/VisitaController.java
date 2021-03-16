package com.comit.proyecto.controladores;
import java.util.ArrayList;

import com.comit.proyecto.entidades.Visita;
import com.comit.proyecto.modelos.VisitaDB;



public class VisitaController {

	
	public void crearVisita(Visita visita) {
		VisitaDB.crearVisita(visita);
	}
	
	public ArrayList<Visita> listarVisita(int idUsuario) {
		return VisitaDB.listarVisitas(idUsuario);
	}
	
	public int cancelarVisita(int idVisita) {
		return VisitaDB.cancelarVisita(idVisita);
	}
	
	public int modificarVisita(Visita visita) {
		return VisitaDB.actualizarVisita(visita);
	}
}
