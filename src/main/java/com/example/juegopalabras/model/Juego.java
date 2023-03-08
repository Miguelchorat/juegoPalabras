package com.example.juegopalabras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL)
    private Set<Partida> partidas = new HashSet<>();

    enum Dificultad {
        FACIL, INTERMEDIO, DIFICIL;
    }
}
