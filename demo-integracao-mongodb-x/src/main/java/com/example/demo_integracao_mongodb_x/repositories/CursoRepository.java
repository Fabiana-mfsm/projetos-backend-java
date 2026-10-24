package com.example.demo_integracao_mongodb_x.repositories;

import com.example.demo_integracao_mongodb_x.models.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CursoRepository extends MongoRepository<Curso, String> {
}
