package com.example.juegopalabras.repos;

import com.example.juegopalabras.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long>, JpaSpecificationExecutor<Partida> {
    List<Partida> findByJugadorId(Long id_jugador);
}
