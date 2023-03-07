package com.example.juegopalabras.service;

import com.example.juegopalabras.model.Equipo;
import com.example.juegopalabras.model.Juego;
import com.example.juegopalabras.repos.EquipoRepository;
import com.example.juegopalabras.repos.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JuegoService {
    @Autowired
    private JuegoRepository juegoRepository;

    public List<Juego> obtenerJuegos() {
        return juegoRepository.findAll();
    }

    public Optional<Juego> obtenerJuego(Long id) {
        return juegoRepository.findById(id);
    }

    public Juego guardarJuego(Juego juego) {
        juego.setFecha_creacion(LocalDate.now());
        juego.setFecha_modificacion(LocalDate.now());
        return juegoRepository.save(juego);
    }

    public void borrarJuego(Long id) {
        juegoRepository.deleteById(id);
    }


}
