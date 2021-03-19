package com.comit.proyecto.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//import java.text.SimpleDateFormat;

@Getter @Setter
@Entity
@Table(name = "Usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String clave;
    private int id_rol;    
    private String obra_social;
    private String n_socio;
    private String dni;


}