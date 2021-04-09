package com.saubiette.proyecto.util;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.saubiette.proyecto.controladores.RolController;
import com.saubiette.proyecto.controladores.UsuarioController;
import com.saubiette.proyecto.entidades.Rol;
import com.saubiette.proyecto.entidades.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;

	@Autowired
	RolController rolController;

	@Autowired
	UsuarioController usuarioController;

	Usuario usuario;
	Rol rol;

	public MyUserDetails(Usuario usuario, RolController rolController) {
		this.usuario = usuario;
		this.rolController = rolController;
	}

	@Override
	public Collection getAuthorities() {

		Rol rol = rolController.traerRolesById(usuario.getRol().getId());

		if (rol != null)
			return List.of(new SimpleGrantedAuthority(rol.getRol()));
		return null;
	}

	@Override
	public String getPassword() {

		if (usuario != null)
			return usuario.getClave();
		return "";
	}

	@Override
	public String getUsername() {
		return usuario.toString();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
