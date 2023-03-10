package com.example.juegopalabras.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PalabraNotFoundException extends RuntimeException{
    public PalabraNotFoundException(){
        super("No se puede encontrar ninguna palabra");
    }

    public PalabraNotFoundException(String filtro){
        super("No se puede encontrar la palabra con el filtro: " + filtro);
    }
}
