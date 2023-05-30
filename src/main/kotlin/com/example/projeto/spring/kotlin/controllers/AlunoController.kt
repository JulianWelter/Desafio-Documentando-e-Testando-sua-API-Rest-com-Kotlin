package com.example.projeto.spring.kotlin.controllers

import com.example.projeto.spring.kotlin.entities.Aluno
import com.example.projeto.spring.kotlin.services.IAlunoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/alunos")
class AlunoController {

    @Autowired
    private lateinit var alunoService: IAlunoService

    @GetMapping
    fun listarAlunos(): List<Aluno> {
        return alunoService.findAll()
    }

    @GetMapping("/{id}")
    fun buscarAlunoPorId(@PathVariable id: Long): ResponseEntity<Aluno> {
        val aluno = alunoService.findById(id)

        return ResponseEntity.ok(aluno)

    }

    @PostMapping
    fun criarAluno(@RequestBody aluno: Aluno): ResponseEntity<Aluno> {
        val novoAluno = alunoService.save(aluno)
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno)
    }

//    @PutMapping("/{id}")
//    fun atualizarAluno(
//        @PathVariable id: Long,
//        @RequestBody alunoAtualizado: Aluno
//    ): ResponseEntity<Aluno> {
//        val aluno = alunoService.atualizarAluno(id, alunoAtualizado)
//        return if (aluno != null) {
//            ResponseEntity.ok(aluno)
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    fun excluirAluno(@PathVariable id: Long): ResponseEntity<Void> {
//        return if (alunoService.excluirAluno(id)) {
//            ResponseEntity.noContent().build()
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }
}