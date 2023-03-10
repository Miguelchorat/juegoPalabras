package com.example.juegopalabras.controller;

import com.example.juegopalabras.error.PalabraNotFoundException;
import com.example.juegopalabras.service.PalabraService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class PalabraController {
    @Autowired
    private PalabraService palabraService;
    @GetMapping("/palabra")
    public String obtenerPalabra(){
        String palabra = palabraService.obtenerPalabra();
        if(palabra==null){
            throw new PalabraNotFoundException();
        }
        return palabra;
    }
    @GetMapping("/palabras")
    public List<String> obtenerPalabras(){
        List<String> palabras =palabraService.obtenerPalabras();
        if(palabras == null || palabras.isEmpty())
            throw new PalabraNotFoundException();

        return palabras;
    }
    @GetMapping("/palabra/filtro={filtro}")
    public String obtenerPalabraFiltro(@PathVariable String filtro){
        String palabra =palabraService.obtenerPalabra(filtro);
        if(palabra == null)
            throw new PalabraNotFoundException(filtro);

        return palabra;
    }
    @GetMapping("/palabras/filtro={filtro}")
    public List<String> obtenerPalabrasFiltro(@PathVariable String filtro){
        List<String> palabras =palabraService.obtenerPalabras(filtro);
        if(palabras == null || palabras.isEmpty())
            throw new PalabraNotFoundException(filtro);

        return palabras;
    }
    @GetMapping("/palabras/{numero}")
    public List<String> obtenerPalabras(@PathVariable Long numero){
        List<String> palabras =palabraService.obtenerPalabras(numero);
        if(palabras == null || palabras.isEmpty())
            throw new PalabraNotFoundException();

        return palabras;
    }
}
