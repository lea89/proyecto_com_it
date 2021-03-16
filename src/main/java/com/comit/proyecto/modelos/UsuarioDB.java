package com.comit.proyecto.modelos;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.comit.proyecto.entidades.Usuario;
import com.comit.proyecto.util.BCrypt;


public class UsuarioDB {
	
	private static String hashPassword(String plainTextPassword){
	    return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	} 
	
	private static boolean checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword))
			return true;
		else
			return false;
		}
	
	
	public static ArrayList<Usuario> traerListaUsuarios() {
		
		ArrayList<Usuario> lista_usuarios = new ArrayList<Usuario>();
		try {
			Statement query = DB.conexion.createStatement();
			ResultSet res = query.executeQuery("select * from usuarios");

			while ( res.next() ) {
				Usuario usuario = new Usuario();
				
				usuario.setDni(res.getString("dni"));
				usuario.setNombre(res.getString("nombre"));
				usuario.setApellido(res.getString("apellido"));
				usuario.setEmail(res.getString("email"));
				usuario.setIdRol(res.getInt("id_rol"));
				usuario.setnSocio(res.getString("nSocio"));
				usuario.setObraSocial(res.getString("obraSocial"));
				
				lista_usuarios.add(usuario);
			}
			
		}catch (Exception e) {

			e.printStackTrace();
			
		}
		
		return lista_usuarios;
	}
	
	public static String nuevoUsuario(Usuario usuario) {
		
		//String pw_hash = BCrypt.hashpw(plain_password, BCrypt.gensalt());

		try {
			Statement query = DB.conexion.createStatement();
			ResultSet res = query.executeQuery("Select rol from tipo_rol where id_rol = " + usuario.getId_rol());
			
			if (usuario.getId_rol() == Integer.parseInt(res.getString("id_rol"))) {

				query.execute("insert " + "into usuarios (nombre, apellido, dni, email, clave, rol, obraSocial, n_Socio) "
						+ "values (" + usuario.getNombre() + "','" + usuario.getApellido() + "','" + usuario.getDni() + "','"
						+ usuario.getEmail() + "','" + hashPassword(usuario.getClave()) +"', '" +usuario.getObraSocial() + "','"+usuario.getnSocio()+"')");

				return "El usuario se creo con exito.";
			} else {
				return "Ud. no tiene los privilegios para crear un usuario.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error al crea el usuario.";
		}

	}
	
	
	public static Usuario traerUsuario(int id_usuario) {
		
		Usuario user = null;
		
		try {
			Statement query = DB.conexion.createStatement();
			ResultSet res = query.executeQuery("Select * from usuario where id_usuario = " + id_usuario);
			
			if(res.next()) {
				user = new Usuario();
				
				user.setApellido(res.getString("apellido"));
				user.setNombre(res.getString("nombre"));
				user.setClave(res.getString("clave"));
				user.setEmail(res.getString("email"));
				user.setObraSocial(res.getString("obra_social"));
				user.setnSocio(res.getString("n_socio"));
				user.setId(res.getInt("id"));
				user.setIdRol(res.getInt("id_rol"));
				user.setDni(res.getString("dni"));
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
			
		}
		
		return user;
	}
	
	 public static int editarUsuario(Usuario usuario) {
		
		try {
			Statement query= DB.conexion.createStatement();
			
			return query.executeUpdate("Update usuario set "
					+ "nombre = '"+usuario.getNombre()+"'"
					+ "apellido = '"+usuario.getApellido()+"'"
					+ "dni = '"+usuario.getDni()+"'"
					+ "email = '"+usuario.getEmail()+"'"
					+ "obraSocial = '"+usuario.getObraSocial()+"'"
					+ "n_socio = '"+usuario.getnSocio()+"'"
					+ "id_rol = '"+usuario.getIdRol()+"'"
					+ "clave = '"+hashPassword(usuario.getClave())+"'"
					);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
			return -1;
		}
	}
	 
	 public static int eliminarUsuario(int id_usuario) {
		 
		 try {
			 Statement query = DB.conexion.createStatement();
			 return	 query.executeUpdate("delet from usuarios where id = " + id_usuario);
		 }catch(Exception e) {
			 e.printStackTrace();
			 return -1;
		 }
		 
	 }
	 
	 public static boolean loginUsuario(String dni, String clave) {
		 boolean login = false;
		 try {
			 Statement query =  DB.conexion.createStatement();
			 ResultSet res = query.executeQuery("select * from usuarios where dni = '"+dni+"'");
			 
			 if(res.next()) {
				 if (checkPass(clave, res.getString("clave"))) {
					 login = true;
				 }
			 }
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		return login;
		 
	 }
}