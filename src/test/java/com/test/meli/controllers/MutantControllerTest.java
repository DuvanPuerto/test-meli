package com.test.meli.controllers;

import com.test.meli.dao.IDnaRepository;
import com.test.meli.models.Mutant;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MutantControllerTest {

    @InjectMocks
    @Autowired
    private IDnaRepository iDnaRepository;

    @Test
    void shouldReturnSuccessful(){
        Mutant mutant = Mockito.mock(Mutant.class);
        IDnaRepository iDnaRepository = Mockito.mock(IDnaRepository.class);
        String[] dnaMutant = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"}; //Diagonal y Horizontal
        when(mutant.isMutant(dnaMutant)).thenReturn(true);

        MutantController mutantController = new MutantController(mutant, iDnaRepository);
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
        IDnaRepository iDnaRepository = Mockito.mock(IDnaRepository.class);
        String[] dnaHuman = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        when(mutant.isMutant(dnaHuman)).thenReturn(false);

        MutantController mutantController = new MutantController(mutant, iDnaRepository);
        Map<String,String> response = new HashMap<>();
        Map<String,String[]> request = new HashMap<>();

        //Caso No exitoso
        request.put("dna", new String[]{"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"});
        response.put("message","It isn't a mutant!");
        assertEquals(new ResponseEntity<>(response, HttpStatus.FORBIDDEN),mutantController.evaluateMutant(request));

    }

    @Test
    void shouldReturnBadRequest(){
        Mutant mutant = Mockito.mock(Mutant.class);
        IDnaRepository iDnaRepository = Mockito.mock(IDnaRepository.class);
        String[] dnaHuman = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        when(mutant.isMutant(dnaHuman)).thenReturn(false);

        MutantController mutantController = new MutantController(mutant, iDnaRepository);
        Map<String,String> response = new HashMap<>();
        Map<String,String[]> request = new HashMap<>();

        //Caso No exitoso
        request.put("dna", new String[]{"ATGCGA", "CGC", "TTATTT", "AGAGG", "GCGTCA", "TCACTG"});
        response.put("message","Bad request!. One property with key 'dna' is required with an array of strings as its value. " +
                "Every string should have the same number of character (size) and this size should be equal to the number of strings.");
        assertEquals(new ResponseEntity<>(response, HttpStatus.BAD_REQUEST),mutantController.evaluateMutant(request));

    }

    @Test
    void shouldReturnInternalServerError(){
        Mutant mutant = Mockito.mock(Mutant.class);
        IDnaRepository iDnaRepository = null;
        String[] dnaHuman = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        when(mutant.isMutant(dnaHuman)).thenReturn(false);

        MutantController mutantController = new MutantController(mutant, iDnaRepository);
        Map<String,String> response = new HashMap<>();
        Map<String,String[]> request = new HashMap<>();

        //Caso No exitoso
        request.put("dna", new String[]{"ATGCGA", "ATGCGA", "TTATTT", "AGAAGG", "GCGTCA", "TCACTG"});
        response.put("message","There was an internal server error!");
        assertEquals(new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR),mutantController.evaluateMutant(request));

    }

    @Test
    void shouldReturnStats(){
        Mutant mutant = Mockito.mock(Mutant.class);
        IDnaRepository iDnaRepository = Mockito.mock(IDnaRepository.class);

        MutantController mutantController = new MutantController(mutant, iDnaRepository);
        Map<String,String> response = new HashMap<>();
        Map<String,Object> request = new HashMap<>();

        assertEquals(200, mutantController.statsMutant().getStatusCodeValue());
    }

    @Test
    void shouldReturnStatsError(){
        Mutant mutant = Mockito.mock(Mutant.class);
        IDnaRepository iDnaRepository = null;

        MutantController mutantController = new MutantController(mutant, iDnaRepository);
        Map<String,String> response = new HashMap<>();
        Map<String,Object> request = new HashMap<>();

        assertEquals(500, mutantController.statsMutant().getStatusCodeValue());
    }
}