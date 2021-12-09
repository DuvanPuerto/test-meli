package com.test.meli.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutantTest {

    @Test
    void shouldEvaluateMutant(){
        Mutant mutant = new Mutant();

        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"}; //Ninguna
        String[] dna2 = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"}; //Diagonal y Horizontal
        String[] dna3 = {"ATGCGA", "CATTGC", "TTATGT", "AGAATG", "CCCCTA", "TCACTG"}; //Diagonal y Diagonal
        String[] dna4 = {"ATGCGA", "CGGGGC", "TTATGT", "AGAATT", "CCCCTA", "TCACTG"}; //Horizontal y Horizontal
        String[] dna5 = {"TTGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"}; //Horizontal y Vertical
        String[] dna6 = {"TTGCGA", "TAGTGC", "TTATGT", "TGAAGG", "CACCTA", "TCACTG"}; //Vertical y Vertical
        String[] dna7 = {"TTTTAAAA", "TAGTGCAA", "ATATGTAA", "TGAAGGAA", "CACCTAAA", "TCACTGAA", "CACCTAAA", "TCACTGAA"}; //Horizontal y Horizontal
        String[] dna8 = {"TCACTGAA", "TAGTGCAA", "ATATGTAA", "TGAAGGAA", "CACCTAAA", "TCACTGAA", "CACCTAAA", "TCACTGAA"}; //Vertical y Diagonal
        String[] dna9 = {"TAGCGAAA", "TAGTGCAA", "ATATGTAA", "TGAAGGAA", "CACCTAAA", "TCACTTAA", "CACCTATA", "TCACTGAT"}; //Diagonal y Vertical
        String[] dna10 = {"TCACTGAA", "TAGTGCAA", "ATATGTAA", "TGAAGGAA", "TACCTCAA", "TCACTGAA", "CACCTAAA", "TCACTGAA"}; //Vertical y Vertical


        assertEquals(false, mutant.isMutant(dna));
        assertEquals(true, mutant.isMutant(dna2));
        assertEquals(true, mutant.isMutant(dna3));
        assertEquals(true, mutant.isMutant(dna4));
        assertEquals(true, mutant.isMutant(dna5));
        assertEquals(true, mutant.isMutant(dna6));
        assertEquals(true, mutant.isMutant(dna7));
        assertEquals(true, mutant.isMutant(dna8));
        assertEquals(true, mutant.isMutant(dna9));
        assertEquals(true, mutant.isMutant(dna10));

    }

}