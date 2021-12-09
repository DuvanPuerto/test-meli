package com.test.meli.controllers;

import com.test.meli.models.Mutant;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MutantControllerTest {

    @Test
    void shouldReturnSuccessful(){
        Mutant mutant = Mockito.mock(Mutant.class);
        String[] dnaMutant = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"}; //Diagonal y Horizontal
        when(mutant.isMutant(dnaMutant)).thenReturn(true);

        MutantController mutantController = new MutantController(mutant);
        Map<String,String> response = new HashMap<>();
        Map<String,String[]> request = new HashMap<>();

        //Caso Exitoso
        request.put("dna", new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"});
        response.put("message","It is a mutant!");
        assertEquals(new ResponseEntity<>(response, HttpStatus.OK),mutantController.evaluateMutant(request));
    }

    @Test
    void shouldReturnNotMutant(){
        Mutant mutant = Mockito.mock(Mutant.class);
        String[] dnaHuman = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        when(mutant.isMutant(dnaHuman)).thenReturn(false);

        MutantController mutantController = new MutantController(mutant);
        Map<String,String> response = new HashMap<>();
        Map<String,String[]> request = new HashMap<>();

        //Caso No exitoso
        request.put("dna", new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"});
        response.put("message","It isn't a mutant!");
        assertEquals(new ResponseEntity<>(response, HttpStatus.FORBIDDEN),mutantController.evaluateMutant(request));

    }
}