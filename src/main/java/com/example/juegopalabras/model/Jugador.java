package com.example.juegopalabras.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Jugador {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String correo;
    private String clave;
    private String avatar;
    private String rol;
    private LocalDate fecha_creacion;
    private LocalDate fecha_modificacion;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="id_equipo")
    private Equipo equipo;
}
