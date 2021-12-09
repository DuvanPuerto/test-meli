package com.test.meli.models;

import org.springframework.stereotype.Service;

@Service
public class Mutant implements IMutant{
    /*

    El método recorre posición a posición la "matriz" de carácteres, valiéndose de la condición del tamaño NxN
    se intenta que en un solo recorrido de toda la matriz se haga la evaluación de las repeticiones.

    Para las oblicuas es necesario un bucle extra para analizar las secuencias en diagonal, es dificl optimizar
    esta evaluación.
    */


    public boolean isMutant(String[] dna){
        int counterHorizontal, counterVertical, counterDiagonal;
        int flagMutant = 0;

        for (int i=0; i<dna.length;i++){
            counterHorizontal = 0;
            counterVertical = 0;
            for (int j=0; j<dna[i].length();j++){
                if(j<dna.length-1){ //Realizar las evaluaciones hasta que se llegue a la penúltima posición



                    //=============================== EVALUACIÓN DE HORIZONTALES ================================


                    if(dna[i].charAt(j) == dna[i].charAt(j+1)){
                        counterHorizontal++;
                    }else{
                        counterHorizontal = 0;
                    }
                    if(counterHorizontal >= 3){
                        flagMutant++;
                        counterHorizontal = 0;
                        System.out.println("%%%%%%%% Detectado Horizontal %%%%%%%%");
                        if(flagMutant > 1) return true; //Si hay más de una secuencia de 4 carácteres, salga de la función
                    }



                    //=============================== EVALUACIÓN DE VERTICALES ================================


                    if(dna[j].charAt(i) == dna[j+1].charAt(i)){
                        counterVertical++;
                    }else{
                        counterVertical = 0;
                    }
                    if(counterVertical >= 3){
                        flagMutant++;
                        counterVertical = 0;
                        System.out.println("%%%%%%%% Detectado Vertical %%%%%%%%");
                        if(flagMutant > 1) return true;
                    }



                    // ================== EVALUACIÓN DE DIAGONALES (El más costoso) ==========================

                    counterDiagonal = 0;
                    if((i == 0 && (j < dna.length-3)) || (j == 0 && (i < dna.length-3) && i > 0)){ //Recorrer la primera fila y columna excepto los tres últimos elementos
                        for(int k=1;((i+k) < dna.length && (j+k) < dna.length);k++) {
                            if (dna[i + k -1].charAt(j + k -1) == dna[i + k].charAt(j + k)) {
                                counterDiagonal++;
                            }else{
                                counterDiagonal = 0;
                            }
                            if(counterDiagonal >= 3){
                                flagMutant++;
                                counterDiagonal = 0;
                                System.out.println("%%%%%%%% Detectado Diagonal %%%%%%%%");
                                if(flagMutant > 1) return true;
                            }
                        }
                    }

                    //System.out.println("============================================");
                }

            }
        }

        return false;
    }

}