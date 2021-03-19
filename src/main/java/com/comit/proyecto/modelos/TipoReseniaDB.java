package com.comit.proyecto.modelos;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.comit.proyecto.entidades.TipoResenia;


public class TipoReseniaDB {

	public static boolean crearTipoResenia(TipoResenia tipo) {
		try {
			
			Statement query = DB.conexion.createStatement();
			
			return query.execute("INSERT INTO tipo_resenia (tipoResenia) VALUES ('"+tipo.getResenia()+"')");
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean eliminarTipoResenia(TipoResenia tipo) {
		try {
			
			Statement query = DB.conexion.createStatement();
			
			return query.execute("DELETE FROM tipo_resenia WHERE id = "+ tipo.getId());
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean editarTipoResenia(TipoResenia tipo) {
		try {
			
			Statement query = DB.conexion.createStatement();
			
			return query.execute("UPDATE SET tipoResenia = "+tipo.getResenia()+" FROM tipo_resenia WHERE id = "+ tipo.getId());
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<TipoResenia> traerListaTipoResenia() {
		
		ArrayList<TipoResenia> listaTipoResenia = new ArrayList<TipoResenia>();
		
		try {
			Statement query = DB.conexion.createStatement();
			
			ResultSet res = query.executeQuery("SELECT * FROM tipo_resenia");
			
			while(res.next()) {
				TipoResenia tipo = new TipoResenia();
				
				tipo.setId(res.getInt("id"));
				tipo.setResenia("tipoResenia");
				
				listaTipoResenia.add(tipo);
			}
			
			return listaTipoResenia;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			return null;
			
		}
		
	}
}
