package com.example.demo_integracao_mongodb_x.controlles;

import com.example.demo_integracao_mongodb_x.models.Professor;
import com.example.demo_integracao_mongodb_x.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping
    public Professor gravar(@RequestBody Professor professor) {
        return this.professorRepository.save(professor);
    }

    @GetMapping
    public List<Professor> buscarTodos() {
        return this.professorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Professor> buscarPorId(@PathVariable String id) {
        return this.professorRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Professor atualizar(@PathVariable String id, @RequestBody Professor professorAtualizado) {
        professorAtualizado.setId(id);
        return this.professorRepository.save(professorAtualizado);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable String id) {
        this.professorRepository.deleteById(id);
    }
}

