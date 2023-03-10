package com.example.juegopalabras.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PalabraService {
    public String obtenerPalabra(){
        List<String> palabras = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource("palabras.txt");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));) {
            String line;
            while((line = reader.readLine()) != null){
                palabras.add(line.trim());
            }
        } catch (IOException ioe){
            return null;
        }
        return palabras.get((int)(Math.random() * palabras.size()) + 1);
    }

    public List<String> obtenerPalabras(){
        List<String> palabras = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource("palabras.txt");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));) {
            String line;
            while((line = reader.readLine()) != null){
                palabras.add(line.trim());
            }
        } catch (IOException ioe){
            return null;
        }
        return palabras;
    }
    public List<String> obtenerPalabras(Long tamanyo){
        List<String> palabras = new ArrayList<>();
        List<String> palabrasRandom = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource("palabras.txt");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));) {
            String line;
            while((line = reader.readLine()) != null){
                palabras.add(line.trim());
            }
            for (int i=0; i<tamanyo; i++ ){
                palabrasRandom.add(palabras.get((int)(Math.random() * palabras.size()) + 1));
            }
        } catch (IOException ioe){
            return null;
        }
        return palabrasRandom;
    }
    public List<String> obtenerPalabras(String filtro){
        List<String> palabras = new ArrayList<>();
        List<String> palabrasRandom = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource("palabras.txt");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));) {
            String line;
            while((line = reader.readLine()) != null){
                palabras.add(line.trim());
            }
            palabrasRandom = palabras.stream().filter(palabra -> palabra.contains(filtro)).collect(Collectors.toList());
        } catch (IOException ioe){
            return null;
        }
        return palabrasRandom;
    }
    public String obtenerPalabra(String filtro){
        List<String> palabras = new ArrayList<>();
        List<String> palabrasRandom;
        String palabraElegida = "";

        ClassPathResource resource = new ClassPathResource("palabras.txt");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));) {
            String line;
            while((line = reader.readLine()) != null){
                palabras.add(line.trim());
            }
            palabrasRandom = palabras.stream().filter(palabra -> palabra.contains(filtro)).collect(Collectors.toList());
            palabraElegida = palabrasRandom.get((int)(Math.random() * palabrasRandom.size()) + 1);
        } catch (Exception ioe){
            return null;
        }
        return palabraElegida;
    }
}
