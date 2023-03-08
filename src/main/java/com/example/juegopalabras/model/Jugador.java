package com.example.juegopalabras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
    private Set<Partida> partidas = new HashSet<>();
}
