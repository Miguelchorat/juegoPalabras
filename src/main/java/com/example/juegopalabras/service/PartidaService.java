package com.example.juegopalabras.service;

import com.example.juegopalabras.model.Partida;
import com.example.juegopalabras.repos.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

}
