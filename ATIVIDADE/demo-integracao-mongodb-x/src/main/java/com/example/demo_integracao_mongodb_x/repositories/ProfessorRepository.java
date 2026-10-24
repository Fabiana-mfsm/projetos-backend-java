package com.example.demo_integracao_mongodb_x.repositories;

import com.example.demo_integracao_mongodb_x.models.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
}
