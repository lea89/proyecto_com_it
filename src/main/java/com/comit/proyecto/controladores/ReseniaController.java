package com.comit.proyecto.controladores;

import java.util.ArrayList;

import com.comit.proyecto.entidades.Resenia;
import com.comit.proyecto.modelos.ReseniaDB;



public class ReseniaController {
	
	public boolean crearResenia(Resenia resenia) {
		
		return ReseniaDB.crearResenia(resenia);
		
	}
	
	public boolean editarResenia(Resenia resenia) {
		return ReseniaDB.editarResenia(resenia);
	}
	
	public boolean eliminarResenia(int id) {
		
		return ReseniaDB.eliminarResenia(id);
		
	}
	
	public ArrayList<Resenia> listarResenia(int tipoResenia){
	
		return ReseniaDB.traerListaReseniaTipo(tipoResenia);
	}

}
