package com.test.meli.models;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

@Document("dna")
public class Dna {

    public Dna(String[] dna, boolean mutant) {
        this.dna = dna;
        this.mutant = mutant;
    }

    @Id
    private String idDna;

    private String[] dna;

    private boolean mutant;
}
