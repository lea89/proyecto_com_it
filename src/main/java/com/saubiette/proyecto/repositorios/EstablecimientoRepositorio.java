package com.saubiette.proyecto.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saubiette.proyecto.entidades.Establecimiento;

@Repository
public interface EstablecimientoRepositorio extends CrudRepository<Establecimiento, Integer> {

}
