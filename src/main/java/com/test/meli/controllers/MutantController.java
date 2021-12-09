package com.test.meli.controllers;

import com.test.meli.dao.IDnaRepository;
import com.test.meli.models.Dna;
import com.test.meli.models.IMutant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MutantController {

    @Autowired
    private IDnaRepository iDnaRepository;

    private IMutant iMutant;

    MutantController(IMutant iMutant){
        this.iMutant = iMutant;
    }

    @PostMapping("/mutant")
    public ResponseEntity<Map<String, Object>> evaluateMutant(
           @RequestBody() Map<String, String[]> dnaRequest
    ){
        HashMap<String, Object> response = new HashMap<>();
        try {
            if(validateRequest(dnaRequest)){
                String[] dna = dnaRequest.get("dna");
                if(iMutant.isMutant(dna)){
                    iDnaRepository.save(new Dna(dna,true));
                    response.put("message", "It is a mutant!");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }else {
                    iDnaRepository.save(new Dna(dna,false));
                    response.put("message", "It isn't a mutant!");
                    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
                }
            }else{
                response.put("message", "Bad request!. One property with key 'dna' is required with an array of strings as its value. " +
                        "Every string should have the same number of character (size) and this size should be equal to the number of strings.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            response.put("message", "There was an internal server error!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateRequest(Map<String, String[]> dnaRequest){
        try{
            String[] dna = dnaRequest.get("dna");
            for (String dnaRow:
                 dna) {
                if(dna.length != dnaRow.length()){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> statsMutant(
    ){
        HashMap<String, Object> response = new HashMap<>();
        try {
            int countMutant = iDnaRepository.findByMutant(true).size();
            int countHuman = iDnaRepository.findByMutant(false).size();
            response.put("count_mutant_dna", countMutant);
            response.put("count_human_dna", countHuman);
            float ratio = (float) countMutant / countHuman;
            response.put("ratio", ratio);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("message", "There was an internal server error!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
