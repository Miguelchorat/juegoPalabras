package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.EquipoNotFoundException;
import com.example.juegopalabras.error.JugadorNotFoundException;
import com.example.juegopalabras.model.Jugador;
import com.example.juegopalabras.service.JugadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class JugadorController {
    @Autowired
    private JugadorService jugadorService;

    @GetMapping("/jugadores")
    public ResponseEntity<List<Jugador>> obtenerJugadores() {
        List<Jugador> jugadores = jugadorService.obtenerJugadores();

        if(jugadores.isEmpty()){
            throw new JugadorNotFoundException();
        }

        return new ResponseEntity<>(jugadores, HttpStatus.OK);
    }

    @GetMapping("/jugador/{id}")
    public ResponseEntity<Jugador> obtenerJugador(@PathVariable Long id) {
        Jugador jugador = jugadorService.obtenerJugador(id).orElseThrow(() -> new JugadorNotFoundException(id));;
        return ResponseEntity.ok(jugador);
    }

    @PostMapping("/jugador")
    public ResponseEntity<Jugador> guardarJugador(@RequestBody Jugador jugador) {
        jugador = jugadorService.guardarJugador(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).body(jugador);
    }

    @PutMapping("/jugador/{id}")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable Long id, @RequestBody Jugador jugador) {
        Optional<Jugador> jugadorActual = jugadorService.obtenerJugador(id);
        if (jugadorActual.isPresent()) {
            jugador.setId(id);
            Jugador jugadorActualizado = jugadorService.guardarJugador(jugador);
            return new ResponseEntity<>(jugadorActualizado, HttpStatus.OK);
        } else {
            throw new JugadorNotFoundException(id);
        }
    }

    @DeleteMapping("/jugador/{id}")
    public ResponseEntity<HttpStatus> borrarJugador(@PathVariable Long id) {
        Optional<Jugador> jugador = jugadorService.obtenerJugador(id);
        if (jugador.isPresent()) {
            jugadorService.borrarJugador(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new JugadorNotFoundException(id);
        }
    }
    @GetMapping("/equipo/{id_equipo}/jugadores")
    public ResponseEntity<List<Jugador>> obtenerJugadoresPorEquipo(@PathVariable Long id_equipo) {
        List<Jugador> jugadores = jugadorService.obtenerJugadoresPorEquipo(id_equipo);

        if(jugadores == null || jugadores.isEmpty()) {
            throw new EquipoNotFoundException(id_equipo);
        }

        return ResponseEntity.ok(jugadores);
    }
}
