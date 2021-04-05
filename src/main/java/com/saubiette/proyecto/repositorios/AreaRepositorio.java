package com.saubiette.proyecto.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saubiette.proyecto.entidades.Area;

@Repository
public interface AreaRepositorio extends CrudRepository<Area, Integer> {

}
