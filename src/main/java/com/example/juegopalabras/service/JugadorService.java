package com.example.juegopalabras.service;

import com.example.juegopalabras.model.Jugador;
import com.example.juegopalabras.repos.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class JugadorService {
    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Jugador> obtenerJugadores() {
        return jugadorRepository.findAll();
    }

    public Optional<Jugador> obtenerJugador(Long id) {
        return jugadorRepository.findById(id);
    }

    public Jugador guardarJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public void borrarJugador(Long id) {
        jugadorRepository.deleteById(id);
    }

    public List<Jugador> obtenerJugadoresPorEquipo(Long id_equipo) {
        return jugadorRepository.findByEquipoId(id_equipo);
    }
}
