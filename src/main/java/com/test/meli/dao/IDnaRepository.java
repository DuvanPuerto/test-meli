package com.test.meli.dao;

import com.test.meli.models.Dna;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IDnaRepository extends MongoRepository<Dna, String> {

    public List<Dna> findByMutant(boolean mutant);
}
