package com.comit.proyecto.modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.comit.proyecto.entidades.Personal;


public class PersonalDB {

	public static boolean crearPersonal(Personal personal) {

		try {

			Statement query = DB.conexion.createStatement();

			query.execute("insert " + "into establecimiento (legajo, nombre, apellido, direccion, id_area) "
					+ "values ('"
					+ personal.getLegajo() + "','"
					+ personal.getNombre() + "','" 
					+ personal.getDireccion() + "'," 
					+ personal.getId_area() + ")");

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	public static boolean eliminarPersonal(String legajo) {

		try {

			Statement query = DB.conexion.createStatement();

			query.execute("delete from personal where legajo = " + legajo);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());

			return false;
		}
	}

	public static boolean updatePersonal(Personal personal) {

		try {

			Statement query = DB.conexion.createStatement();

			query.executeUpdate("update from personal" 
					+ " nombre = '" + personal.getNombre() + "'"
					+ " apellido = '" + personal.getApellido() + "'"
					+ " dni = '" + personal.getDni() + "'"
					+ " direccion = '" + personal.getDireccion() + "'" 
					+ " id_area = " + personal.getId_area()
					+ " where id = " + personal.getLegajo());

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	public static ArrayList<Personal> getPersonalCompleto() {

		ResultSet rs;

		ArrayList<Personal> lista_personal = new ArrayList<Personal>();

		try {
			Statement stmt = DB.conexion.createStatement();

			stmt = DB.conexion.createStatement();
			rs = stmt.executeQuery("SELECT * FROM personal");

			while (rs.next()) {

				String nombre = rs.getString("nombre");
				String direccion = rs.getString("direccion");
				String apellido = rs.getString("apellido");
				String dni = rs.getString("dni");
				String legajo = rs.getString("legajo");
				int id = rs.getInt("id");
				int id_area = rs.getInt("id_area");

				Personal e = new Personal(id, legajo, nombre, apellido, direccion, dni, id_area);
				lista_personal.add(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return lista_personal;
	}

	public static Personal getPersonal(String legajo) {

		Personal personal = null;

		try {
			Statement st = DB.conexion.createStatement();

			ResultSet rs = st.executeQuery("SELECT * from personal where id = " + legajo);

			while (rs.next()) {

				String leg = rs.getString("legajo");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String direccion = rs.getString("direccion");
				String dni = rs.getString("dni");
				int id = Integer.parseInt(rs.getString("id"));
				int id_area = Integer.parseInt(rs.getString("id_area"));

				personal = new Personal(id, leg, nombre, apellido, direccion, dni, id_area);

			}

			return personal;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			return null;
		}
	}
}
