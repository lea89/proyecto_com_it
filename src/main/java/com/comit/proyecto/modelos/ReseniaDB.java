package com.comit.proyecto.modelos;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.comit.proyecto.entidades.Resenia;


public class ReseniaDB {

	public static boolean crearResenia(Resenia resenia) {
		
		try {
			Statement query = DB.conexion.createStatement();
			
			return query.execute("INSERT INTO resenia (comentario, puntaje, idUsuario, idTipoComentario) VALUES ('"
			+resenia.getComentario()+"',"
			+resenia.getPuntaje()+","
			+resenia.getIdUsuario()+","
			+resenia.getIdTipoResenia()+")");
			

		} catch(Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	public static Resenia traerResenia(int id) {
		
		Resenia resenia = new Resenia();
		
		try {
			
			Statement query = DB.conexion.createStatement();
			ResultSet res = query.executeQuery("SELECT * FROM resenia WHERE id = "+ id);
			
			while(res.next()) {

				resenia.setComentario(res.getString("comentario"));
				resenia.setId(res.getInt("id"));
				resenia.setIdTipoResenia(res.getInt("idTipoResenia"));
				resenia.setIdUsuario(res.getInt("idUsuario"));
				resenia.setPuntaje(res.getInt("puntaje"));
			}
			
			return resenia;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	public static ArrayList<Resenia> traerListaReseniaTipo(int tipoResenia) {
		
		ArrayList<Resenia> listaResenias = new ArrayList<Resenia>();
		
		try {
			
			Statement query = DB.conexion.createStatement();
			ResultSet res = query.executeQuery("SELECT * FROM resenia WHERE idTipoResenia = "+ tipoResenia);
			
			while(res.next()) {
				Resenia resenia = new Resenia();
				
				resenia.setComentario(res.getString("comentario"));
				resenia.setId(res.getInt("id"));
				resenia.setIdTipoResenia(res.getInt("idTipoResenia"));
				resenia.setIdUsuario(res.getInt("idUsuario"));
				resenia.setPuntaje(res.getInt("puntaje"));
				
				listaResenias.add(resenia);
			}
			
			return listaResenias;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	public static boolean eliminarResenia(int id) {
		
		try {
			
			Statement query = DB.conexion.createStatement();
			return query.execute("DELETE FROM resenia where id = "+id);
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean editarResenia (Resenia resenia) {
		
		try {
			Statement query = DB.conexion.createStatement();
			
			return query.execute("UPDATE comentario = '"+resenia.getComentario()+"' puntaje = "+resenia.getPuntaje()+" FROM resenia WHERE id = "+ resenia.getId());
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
