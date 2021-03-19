package com.comit.proyecto.controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comit.proyecto.entidades.Usuario;
import com.comit.proyecto.modelos.UsuarioDB;

@Component
@Controller // This means that this class is a Controller
@RequestMapping(path="/usuarios")
public class UsuarioController {

	@Autowired
	public
	static UsuarioRepositorio userRepository;
	
	@GetMapping({"/traer","/traer/{email}"})
	@ResponseBody
	public Iterable<Usuario> traerUsuarios(@PathVariable(required = false) String email) {
		
		ArrayList<Usuario> lista_usuarios = new ArrayList<Usuario>();
		
		if(email != null && !email.equals("")) {
			
			lista_usuarios.add(userRepository.findByEmail(email));
			
			return lista_usuarios;
		} else {
			
			return userRepository.findAll();
		}	
	}
	
	@ResponseBody
	public Usuario crearUsuario(Usuario usuario) {
		//UsuarioDB.nuevoUsuario(usuario);

		return userRepository.save(usuario);
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
	
	
	public boolean login(@RequestParam String email, @RequestParam String clave) {

		//return UsuarioDB.loginUsuario(usuario, clave);
		Usuario usuario = userRepository.findByEmail(email);
		
		if(usuario.getClave().equals(clave)) {
			return true;
		} else {
			return false;
		}

	}

}
