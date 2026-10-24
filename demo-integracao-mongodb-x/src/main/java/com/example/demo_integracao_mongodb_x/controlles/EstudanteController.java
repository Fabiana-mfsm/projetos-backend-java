package com.example.demo_integracao_mongodb_x.controlles;


import com.example.demo_integracao_mongodb_x.models.Estudante;
import com.example.demo_integracao_mongodb_x.repositories.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/estudantes")
public class EstudanteController {
    @Autowired
    private EstudanteRepository estudanteRepository;

    @PostMapping
    public void gravar(@RequestBody Estudante estudante) {
        this.estudanteRepository.save(estudante);
    }

    @GetMapping
    public List<Estudante> buscarTodos() {
        return this.estudanteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Estudante buscarPorId(@PathVariable String id) {
        return this.estudanteRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public Estudante atualizar (@PathVariable String id, @RequestBody Estudante estudanteAtualizado) {
        estudanteAtualizado.setId(id);
        return this.estudanteRepository.save(estudanteAtualizado);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable String id) {
        this.estudanteRepository.deleteById(id);
    }
}
