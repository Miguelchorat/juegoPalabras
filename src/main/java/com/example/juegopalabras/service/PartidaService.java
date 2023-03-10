package com.example.juegopalabras.service;

import com.example.juegopalabras.error.JugadorNotFoundException;
import com.example.juegopalabras.model.Partida;
import com.example.juegopalabras.repos.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    public List<Partida> obtenerPartidas() {
        return partidaRepository.findAll();
    }

    public Optional<Partida> obtenerPartida(Long id) {
        return partidaRepository.findById(id);
    }

    public Partida guardarPartida(Partida partida) {
        partida.setFecha(LocalDate.now());
        return partidaRepository.save(partida);
    }

    public void borrarPartida(Long id) {
        partidaRepository.deleteById(id);
    }

    public List<Partida> findByJugadorId(Long id_jugador){
        return partidaRepository.findByJugadorId(id_jugador);
    }

    public int getPuntosByJugadorId(Long id_jugador){
        List<Partida> partidas = findByJugadorId(id_jugador);
        if(partidas == null || partidas.isEmpty())
            throw new JugadorNotFoundException(id_jugador);
        int puntos = 0;
        for (Partida partida: partidas) {
            puntos += partida.getPuntos();
        }
        return puntos;
    }
}
