package com.saubiette.proyecto.controladores;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saubiette.proyecto.entidades.Usuario;
import com.saubiette.proyecto.repositorios.UsuarioRepositorio;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/usuarios")
public class UsuarioController {

	UsuarioRepositorio userRepository;

	private HorizontalLayout headerLayout;
	private HorizontalLayout navContentLayout;

	public UsuarioController(UsuarioRepositorio userRepository) {
		this.userRepository = userRepository;
	}

	public Iterable<Usuario> traerUsuarios(String email) {

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
		Usuario u = new Usuario();

		Optional<Usuario> opUsuario = userRepository.findById(id);

		if (opUsuario.isPresent()) {
			System.out.print(opUsuario.get());
			return opUsuario.get();
		}

		return null;

		// return null;

	}

	public void eliminarUsuario(int id) {
		userRepository.deleteById(id);
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
