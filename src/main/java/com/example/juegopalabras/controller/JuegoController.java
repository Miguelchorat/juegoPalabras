package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.JuegoNotFoundException;
import com.example.juegopalabras.error.JugadorNotFoundException;
import com.example.juegopalabras.model.Juego;
import com.example.juegopalabras.model.Jugador;
import com.example.juegopalabras.service.JuegoService;
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
public class JuegoController {
    @Autowired
    private JuegoService juegoService;

    @GetMapping("/juegos")
    public ResponseEntity<List<Juego>> obtenerJuegos() {
        List<Juego> juegos = juegoService.obtenerJuegos();

        if(juegos.isEmpty()){
            throw new JuegoNotFoundException();
        }

        return new ResponseEntity<>(juegos, HttpStatus.OK);
    }
    @GetMapping("/juego/{id}")
    public ResponseEntity<Juego> obtenerJuego(@PathVariable Long id) {
        Juego juego = juegoService.obtenerJuego(id).orElseThrow(() -> new JuegoNotFoundException(id));;
        return ResponseEntity.ok(juego);
    }

    @PostMapping("/juego")
    public ResponseEntity<Juego> guardarJuego(@RequestBody Juego juego) {
        juego = juegoService.guardarJuego(juego);
        return ResponseEntity.status(HttpStatus.CREATED).body(juego);
    }

    @PutMapping("/juego/{id}")
    public ResponseEntity<Juego> actualizarJuego(@PathVariable Long id, @RequestBody Juego juego) {
        Optional<Juego> juegoActual = juegoService.obtenerJuego(id);
        if (juegoActual.isPresent()) {
            juego.setId(id);
            Juego juegoActualizado = juegoService.guardarJuego(juego);
            return new ResponseEntity<>(juegoActualizado, HttpStatus.OK);
        } else {
            throw new JuegoNotFoundException(id);
        }
    }

    @DeleteMapping("/juego/{id}")
    public ResponseEntity<HttpStatus> borrarJuego(@PathVariable Long id) {
        Optional<Juego> juego = juegoService.obtenerJuego(id);
        if (juego.isPresent()) {
            juegoService.borrarJuego(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new JuegoNotFoundException(id);
        }
    }
}
