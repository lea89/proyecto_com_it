package com.saubiette.proyecto.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saubiette.proyecto.entidades.Rol;

@Repository
public interface RolRepositorio extends CrudRepository<Rol, Integer> {

}
