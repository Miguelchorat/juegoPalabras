package com.example.juegopalabras.service;

import com.example.juegopalabras.model.Equipo;
import com.example.juegopalabras.repos.EquipoRepository;
import com.example.juegopalabras.repos.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {
    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> obtenerEquipos() {
        return equipoRepository.findAll();
    }

    public Optional<Equipo> obtenerEquipo(Long id) {
        return equipoRepository.findById(id);
    }

    public Equipo guardarEquipo(Equipo equipo) {
        equipo.setFecha_creacion(LocalDate.now());
        equipo.setFecha_modificacion(LocalDate.now());
        return equipoRepository.save(equipo);
    }

    public Equipo actualizarEquipo(Long id, Equipo equipoActualizado) {
        Optional<Equipo> equipoOptional = equipoRepository.findById(id);

        if (!equipoOptional.isPresent()) {
            return null;
        }

        Equipo equipoExistente = equipoOptional.get();
        equipoExistente.setNombre(equipoActualizado.getNombre());
        equipoExistente.setLogo(equipoActualizado.getLogo());
        equipoExistente.setFecha_modificacion(LocalDate.now());

        return equipoRepository.save(equipoExistente);
    }
    public void borrarEquipo(Long id) {
        equipoRepository.deleteById(id);
    }
}
