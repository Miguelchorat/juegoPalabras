package com.example.juegopalabras.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Juego {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String instrucciones;
    private LocalDate fecha_creacion;
    private LocalDate fecha_modificacion;
    private Long intentos;
    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;

    enum Dificultad {
        FACIL, INTERMEDIO, DIFICIL;
    }
}
