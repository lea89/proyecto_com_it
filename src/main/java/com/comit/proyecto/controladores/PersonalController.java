package com.comit.proyecto.controladores;

import com.comit.proyecto.entidades.Personal;
import com.comit.proyecto.modelos.PersonalDB;


public class PersonalController {
	
	public void altaPersonal(Personal persona) {
		
		PersonalDB.crearPersonal(persona);
	}
	
	public void actualizarPersonal(Personal personal) {
		PersonalDB.updatePersonal(personal);
	}
	
	public void eliminarPersonal(String legajo) {
		PersonalDB.eliminarPersonal(legajo);
	}
}
