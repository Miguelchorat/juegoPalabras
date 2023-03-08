package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.JuegoNotFoundException;
import com.example.juegopalabras.error.JugadorNotFoundException;
import com.example.juegopalabras.error.PartidaNotFoundException;
import com.example.juegopalabras.model.Partida;
import com.example.juegopalabras.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PartidaController {
    @Autowired
    private PartidaService partidaService;

    @GetMapping("/partidas")
    public ResponseEntity<List<Partida>> obtenerPartidas() {
        List<Partida> partidas = partidaService.obtenerPartidas();

        if(partidas.isEmpty()){
            throw new PartidaNotFoundException();
        }

        return new ResponseEntity<>(partidas, HttpStatus.OK);
    }
    @GetMapping("/partida/{id}")
    public ResponseEntity<Partida> obtenerPartida(@PathVariable Long id) {
        Partida partida = partidaService.obtenerPartida(id).orElseThrow(() -> new PartidaNotFoundException(id));
        return ResponseEntity.ok(partida);
    }

    @PostMapping("/partida")
    public ResponseEntity<Partida> guardarPartida(@RequestBody Partida partida) {
        partida = partidaService.guardarPartida(partida);
        return ResponseEntity.status(HttpStatus.CREATED).body(partida);
    }

    @PutMapping("/partida/{id}")
    public ResponseEntity<Partida> actualizarPartida(@PathVariable Long id, @RequestBody Partida partida) {
        Optional<Partida> partidaActual = partidaService.obtenerPartida(id);
        if (partidaActual.isPresent()) {
            partida.setId(id);
            Partida partidaActualizado = partidaService.guardarPartida(partida);
            return new ResponseEntity<>(partidaActualizado, HttpStatus.OK);
        } else {
            throw new JuegoNotFoundException(id);
        }
    }

    @DeleteMapping("/partida/{id}")
    public ResponseEntity<HttpStatus> borrarPartida(@PathVariable Long id) {
        Optional<Partida> partida = partidaService.obtenerPartida(id);
        if (partida.isPresent()) {
            partidaService.borrarPartida(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new JuegoNotFoundException(id);
        }
    }

    @GetMapping("/jugador/{id}/puntos")
    public int getPuntosJugador(@PathVariable Long id){
        return partidaService.getPuntosByJugadorId(id);
    }

    @GetMapping("/partida/palabra")
    public String obtenerPalabra(){
        return partidaService.obtenerPalabra();
    }
}
