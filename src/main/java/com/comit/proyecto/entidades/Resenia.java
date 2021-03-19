package com.comit.proyecto.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class Resenia {
    private int id;
    private String comentario;
    private int puntaje;
    private int id_usuario;
    private int id_tipo_resenia;

}