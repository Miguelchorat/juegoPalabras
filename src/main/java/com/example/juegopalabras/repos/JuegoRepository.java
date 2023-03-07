package com.example.juegopalabras.repos;

import com.example.juegopalabras.model.Juego;
import com.example.juegopalabras.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JuegoRepository extends JpaRepository<Juego, Long>, JpaSpecificationExecutor<Juego> {
}
