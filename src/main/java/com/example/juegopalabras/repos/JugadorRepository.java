package com.example.juegopalabras.repos;

import com.example.juegopalabras.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Long>, JpaSpecificationExecutor<Jugador> {
    List<Jugador> findByEquipoId(Long id_equipo);
}
