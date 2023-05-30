package com.example.projeto.spring.kotlin.controllers

import com.example.projeto.spring.kotlin.entities.Formacao
import com.example.projeto.spring.kotlin.services.IFormacaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/formacoes")
class FormacaoController {

    @Autowired
    private lateinit var formacaoService: IFormacaoService

    @GetMapping
    fun listarFormacoes(): List<Formacao> {
        return formacaoService.findAll()
    }

    @GetMapping("/{id}")
    fun buscarFormacaoPorId(@PathVariable id: Long): ResponseEntity<Formacao> {
        val formacao = formacaoService.findById(id)
        return ResponseEntity.ok(formacao)
    }

    @PostMapping("/{nome}")
    fun criarFormacao(@PathVariable nome: String): Formacao {
        return formacaoService.criaFormacao(nome)
    }

//    @PutMapping("/{id}")
//    fun atualizarFormacao(
//        @PathVariable id: Long,
//        @RequestBody formacaoAtualizada: Formacao
//    ): ResponseEntity<Formacao> {
//        val formacao = formacaoService.atualizarFormacao(id, formacaoAtualizada)
//        return if (formacao != null) {
//            ResponseEntity.ok(formacao)
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    fun excluirFormacao(@PathVariable id: Long): ResponseEntity<Void> {
//        val excluido = formacaoService.excluirFormacao(id)
//        return if (excluido) {
//            ResponseEntity.noContent().build()
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }
}