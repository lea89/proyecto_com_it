package com.comit.proyecto.controladores;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.comit.proyecto.entidades.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {

	@Query(value = "SELECT * FROM Usuario WHERE email = ?1", nativeQuery = true)
	  Usuario findByEmail(String emailAddress);
}
