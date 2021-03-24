package com.saubiette.proyecto.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saubiette.proyecto.entidades.Usuario;
import com.saubiette.proyecto.repositorios.UsuarioRepositorio;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/usuarios")
public class UsuarioController {

	UsuarioRepositorio userRepository;

	@Autowired
	public UsuarioController(UsuarioRepositorio userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping({ "/traer", "/traer/{email}" })
	@ResponseBody
	public Iterable<Usuario> traerUsuarios(@PathVariable(required = false) String email) {

		ArrayList<Usuario> lista_usuarios = new ArrayList<Usuario>();

		if (email != null && !email.equals("")) {

			lista_usuarios.add(userRepository.findByEmail(email));

			return lista_usuarios;
		} else {

			return userRepository.findAll();
		}
	}

	public Usuario crearUsuario(Usuario usuario) {
		// UsuarioDB.nuevoUsuario(usuario);

		return userRepository.save(usuario);
	}

	public Usuario traerUsuario(int id) {
		// UsuarioDB.nuevoUsuario(usuario);

		return userRepository.findById(id).get();
	}

	public boolean login(@RequestParam String email, @RequestParam String clave) {

		// return UsuarioDB.loginUsuario(usuario, clave);
		Usuario usuario = userRepository.findByEmail(email);

		if (usuario.getClave().equals(clave)) {
			return true;
		} else {
			return false;
		}

	}

}
