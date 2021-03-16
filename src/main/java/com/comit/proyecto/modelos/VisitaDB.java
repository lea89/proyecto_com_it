package com.comit.proyecto.modelos;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.comit.proyecto.entidades.Visita;



public class VisitaDB {

	public static boolean crearVisita(Visita visita) {

		try {
			Statement query = DB.conexion.createStatement();
			boolean a = query.execute("INSERT into visita (id_establecimiento,id_usuario, fecha) values ("
					+ visita.getId_establecimiento() + "," + visita.getId_usuario() + ",'" + visita.getFecha() + "')");

			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<Visita> listarVisitas(int id) {

		ArrayList<Visita> listaVisitasUsuario = new ArrayList<Visita>();
		Visita visita = null;

		try {
			Statement query = DB.conexion.createStatement();

			ResultSet res = query.executeQuery("Select * FROM visita where id_usuario = " + id);

			while (res.next()) {

				visita = new Visita(res.getDate("fecha"), res.getInt("id_establecimiento"), res.getInt("id_usuario"),
						res.getInt("id_profesional"));

				listaVisitasUsuario.add(visita);
			}

			return listaVisitasUsuario;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

	}

	public static int cancelarVisita(int idVisita) {

		try {

			Statement query = DB.conexion.createStatement();
			return query.executeUpdate("update set estado = false from visita where id = " + idVisita);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public static int actualizarVisita(Visita visita) {
		try {

			Statement query = DB.conexion.createStatement();
			return query.executeUpdate("update set id_establecimiento = "+visita.getId_establecimiento()+", id_usuario = "+visita.getId_usuario()+", id_profesional = "+visita.getId_profesional()+", estado = true from visita where visita = '" + visita.getFecha() + "'");

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
