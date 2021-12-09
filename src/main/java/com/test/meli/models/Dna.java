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

    public String getIdDna() {
        return idDna;
    }

    public void setIdDna(String idDna) {
        this.idDna = idDna;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }
}
