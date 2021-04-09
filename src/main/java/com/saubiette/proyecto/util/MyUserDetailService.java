package com.saubiette.proyecto.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saubiette.proyecto.controladores.RolController;
import com.saubiette.proyecto.entidades.Usuario;
import com.saubiette.proyecto.repositorios.UsuarioRepositorio;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	RolController rolController;

	@Autowired
	UsuarioRepositorio usuarioRepositorio;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if (usuario == null) {
			new UsernameNotFoundException("Username does not exist");
		}
		return new MyUserDetails(usuario, rolController);
	}

}
