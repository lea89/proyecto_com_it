package com.saubiette.proyecto.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saubiette.proyecto.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {

	@Query(value = "SELECT * FROM Usuario WHERE email = ?1", nativeQuery = true)
	Usuario findByEmail(String emailAddress);
}
