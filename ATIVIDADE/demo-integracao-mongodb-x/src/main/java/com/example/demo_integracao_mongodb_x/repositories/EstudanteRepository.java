package com.example.demo_integracao_mongodb_x.repositories;

import com.example.demo_integracao_mongodb_x.models.Estudante;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstudanteRepository extends MongoRepository<Estudante, String> {
}
