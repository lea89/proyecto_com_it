package com.saubiette.proyecto.controladores;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saubiette.proyecto.entidades.Usuario;
import com.saubiette.proyecto.repositorios.UsuarioRepositorio;
import com.saubiette.proyecto.util.AuthenticationRequest;
import com.saubiette.proyecto.util.AuthenticationResponse;
import com.saubiette.proyecto.util.JwtService;
import com.saubiette.proyecto.util.MyUserDetailService;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/usuarios")
@RestController
public class UsuarioController {

	private AuthenticationManager authenticationManager;
	private MyUserDetailService myUserDetailService;
	private JwtService jwtService;

	UsuarioRepositorio userRepository;

	public UsuarioController(UsuarioRepositorio userRepository, MyUserDetailService myUserDetailService,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		jwtService = new JwtService();
		myUserDetailService = new MyUserDetailService();
	}

	public Usuario traerUsuarioByEmail(String email) {

		Usuario usuario;

		if (email != null && !email.equals("")) {

			usuario = userRepository.findByEmail(email);

			return usuario;
		}

		return null;

	}

	public Iterable<Usuario> traerUsuarios() {
		return userRepository.findAll();
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

	@PostMapping("/login")
	public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword());
			authenticationManager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid username or password", e);
		}
		UserDetails userDetails = myUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtService.createToken(userDetails);

		return new AuthenticationResponse(token);
	}
}
