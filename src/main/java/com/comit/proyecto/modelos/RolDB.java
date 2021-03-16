package com.comit.proyecto.modelos;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.comit.proyecto.entidades.Rol;


public class RolDB {

	public static boolean crearRol (String rol) {
		try {
			
			Statement query = DB.conexion.createStatement();
			
			return query.execute("INSERT INTO rol (rol) VALUES ('"+rol+"')");
			
		} catch(Exception e ) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	public static boolean editarRol (Rol rol) {
		try {
			
			Statement query = DB.conexion.createStatement();
			
			return query.execute("UPDATE SET rol = '"+rol.getRol()+"' FROM rol WHERE id = "+ rol.getId());
			
		} catch(Exception e ) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	public static boolean eliminarRol (Rol rol) {
		try {
			
			Statement query = DB.conexion.createStatement();
			
			return query.execute("DELET FROM rol WHERE id = "+ rol.getId());
			
		} catch(Exception e ) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	public static ArrayList<Rol> traerListaRoles() {
		
		ArrayList<Rol> listaRoles = new ArrayList<Rol>();
		
		try {
			Statement query = DB.conexion.createStatement();
			
			ResultSet res = query.executeQuery("SELECT * FROM rol");
			
			while(res.next()) {
				Rol rol = new Rol();
				
				rol.setId(res.getInt("id"));
				rol.setRol("tipoResenia");
				
				listaRoles.add(rol);
			}
			
			return listaRoles;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			return null;
			
		}
		
	}
}
