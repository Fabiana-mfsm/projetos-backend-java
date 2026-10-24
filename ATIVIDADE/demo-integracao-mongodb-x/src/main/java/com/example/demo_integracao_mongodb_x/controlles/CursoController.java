package com.example.demo_integracao_mongodb_x.controlles;

import com.example.demo_integracao_mongodb_x.models.Curso;
import com.example.demo_integracao_mongodb_x.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public Curso gravar(@RequestBody Curso curso) {
        return this.cursoRepository.save(curso);
    }

   @GetMapping
   public List<Curso> buscarTodos(){
        return this.cursoRepository.findAll();
   }

   @GetMapping("/{id}")
   public Optional<Curso> buscarPorId(@PathVariable String id) {
       return this.cursoRepository.findById(id);
   }

    @PutMapping("/{id}")
    public Curso atualizar(@PathVariable String id, @RequestBody Curso cursoAtualizado) {
        cursoAtualizado.setId(id);
        return this.cursoRepository.save(cursoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable String id) {
        this.cursoRepository.deleteById(id);
    }
}
