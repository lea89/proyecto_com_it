package com.comit.proyecto.controladores;

import java.util.ArrayList;

import com.comit.proyecto.entidades.Usuario;
import com.comit.proyecto.modelos.UsuarioDB;


public class UsuarioController {

	
	public void crearUsuario(Usuario usuario) {
		UsuarioDB.nuevoUsuario(usuario);
	}
	
	public void editarUsuario(Usuario usuario) {
		UsuarioDB.editarUsuario(usuario);
	}
	
	public void eliminarUsuario(int id) {
		UsuarioDB.eliminarUsuario(id);
	}
	
	public Usuario traerUsuario(int id) {
		
		return UsuarioDB.traerUsuario(id);
		
	}
	
	public ArrayList<Usuario> traerListaUsuarios() {
		
		return UsuarioDB.traerListaUsuarios();
		
	}

}
