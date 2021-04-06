package com.saubiette.proyecto.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saubiette.proyecto.entidades.Personal;

@Repository
public interface PersonalRepositorio extends CrudRepository<Personal, Integer> {

}
