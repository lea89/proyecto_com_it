package com.comit.proyecto.modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.comit.proyecto.entidades.Establecimiento;


public class EstablecimientoDB {

	public static boolean crearEstablecimiento(Establecimiento establecimiento) {

		try {

			Statement query = DB.conexion.createStatement();

			query.execute("insert " + "into establecimiento (nombre, direccion, tipo) values ('"
					+ establecimiento.getNombre() + "','" + establecimiento.getDireccion() + "'," + 1 + ")");

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	public static boolean eliminarEstablecimiento(int id) {

		try {

			Statement query = DB.conexion.createStatement();

			query.execute("delete from establecimiento where id = " + id);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public static boolean updateEstablecimiento(Establecimiento establecimiento) {

		try {

			Statement query = DB.conexion.createStatement();

			query.executeUpdate("update from establecimiento" + " nombre = '" + establecimiento.getNombre() + "'"
					+ " direccion = '" + establecimiento.getDireccion() + "'" + " tipo = " + establecimiento.getTipo()
					+ " where id = " + establecimiento.getId());

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}
	
	public static ArrayList<Establecimiento> getEstablecimientos() {
		
		ResultSet rs;
		
		ArrayList<Establecimiento> lista_establecimientos = new ArrayList<Establecimiento>();
		
		try {
			Statement stmt = DB.conexion.createStatement();
			
			stmt = DB.conexion.createStatement();
			rs = stmt.executeQuery("SELECT * FROM establecimiento");

			

			while (rs.next()) {

				String nombre = rs.getString("nombre");
				String direccion = rs.getString("direccion");
				int tipo = rs.getInt("tipo");

				Establecimiento e = new Establecimiento(nombre, direccion, tipo);
				lista_establecimientos.add(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return lista_establecimientos;
	}
	
	public Establecimiento getEestablecimiento(int id) {
		
		Establecimiento establecimiento = null;
		
		try {
			Statement st = DB.conexion.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * from establecimiento where id = "+ id);
			
			
			while (rs.next()) {
				
				String nombre = rs.getString("nombre");
				String direccion = rs.getString("direccion");
				int tipo = Integer.parseInt(rs.getString("tipo"));
				
				establecimiento = new Establecimiento(nombre, direccion, tipo);
				
			}
			
			return establecimiento;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			return null;
		}
	}
}
