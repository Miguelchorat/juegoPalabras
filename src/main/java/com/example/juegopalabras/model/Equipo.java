package com.example.juegopalabras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Equipo {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String logo;
    private LocalDate fecha_creacion;
    private LocalDate fecha_modificacion;

    @OneToMany(mappedBy = "equipo")
    @JsonIgnore
    private List<Jugador> jugadores;
}
