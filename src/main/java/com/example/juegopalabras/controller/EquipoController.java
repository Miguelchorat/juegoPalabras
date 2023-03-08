package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.EquipoNotFoundException;
import com.example.juegopalabras.error.JugadorNotFoundException;
import com.example.juegopalabras.model.Equipo;
import com.example.juegopalabras.model.Jugador;
import com.example.juegopalabras.service.EquipoService;
import com.example.juegopalabras.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EquipoController {
    private final EquipoService equipoService;
    private final PartidaService partidaService;

    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> obtenerEquipos() {
        List<Equipo> equipos = equipoService.obtenerEquipos();

        if(equipos.isEmpty()){
            throw new EquipoNotFoundException();
        }

        return ResponseEntity.ok(equipos);
    }
    @GetMapping("/equipo/{id}")
    public ResponseEntity<Equipo> obtenerEquipo(@PathVariable Long id) {
        Equipo equipo = equipoService.obtenerEquipo(id).orElseThrow(() -> new EquipoNotFoundException(id));
        return ResponseEntity.ok(equipo);
    }
    @PostMapping("/equipo")
    public ResponseEntity<Equipo> guardarEquipo(@RequestBody Equipo equipo) {
        equipo = equipoService.guardarEquipo(equipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipo);
    }
    @PutMapping("/equipo/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        Optional<Equipo> equipoActual = equipoService.obtenerEquipo(id);
        if (equipoActual.isPresent()) {
            equipo.setId(id);
            Equipo equipoActualizado = equipoService.guardarEquipo(equipo);
            return new ResponseEntity<>(equipoActualizado, HttpStatus.OK);
        } else {
            throw new EquipoNotFoundException(id);
        }
    }
    @DeleteMapping("/equipo/{id}")
    public ResponseEntity<Void> borrarEquipo(@PathVariable Long id) {
        Optional<Equipo> equipoOptional = equipoService.obtenerEquipo(id);
        if (equipoOptional.isPresent()) {
            equipoService.borrarEquipo(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new EquipoNotFoundException(id);
        }
    }

    @GetMapping("/equipo/{id}/puntos")
    public int obtenerPuntos(@PathVariable Long id){
        List<Jugador> jugadores = equipoService.obtenerEquipo(id).orElseThrow(() -> new EquipoNotFoundException(id)).getJugadores();
        int puntos = 0;

        for (Jugador jugador : jugadores) {
            puntos += partidaService.getPuntosByJugadorId(jugador.getId());
        }
        return puntos;
    }
}
