package com.example.projeto.spring.kotlin.controllers

import com.example.projeto.spring.kotlin.entities.ConteudoEducacional
import com.example.projeto.spring.kotlin.services.IConteudoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/conteudos")
class ConteudoEducacionalController {

    @Autowired
    private lateinit var conteudoEducacionalService: IConteudoService

    @GetMapping
    fun findAll(): ResponseEntity<List<ConteudoEducacional>> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(conteudoEducacionalService.findAll())
    }

    @GetMapping("/{id}")
    fun buscarConteudoPorId(@PathVariable id: Long): ResponseEntity<ConteudoEducacional> {
        val conteudo = conteudoEducacionalService.findById(id)
        return ResponseEntity.ok(conteudo)
    }

    @PostMapping
    fun criarConteudo(@RequestBody conteudo: ConteudoEducacional): ResponseEntity<ConteudoEducacional> {
        val novoConteudo = conteudoEducacionalService.save(conteudo)
        return ResponseEntity.status(HttpStatus.CREATED).body(novoConteudo)
    }

//    @PutMapping("/{id}")
//    fun atualizarConteudo(
//        @PathVariable id: Long,
//        @RequestBody conteudoAtualizado: ConteudoEducacional
//    ): ResponseEntity<ConteudoEducacional> {
//        val conteudo = conteudoEducacionalService.atualizarConteudo(id, conteudoAtualizado)
//        return if (conteudo != null) {
//            ResponseEntity.ok(conteudo)
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }

//    @DeleteMapping("/{id}")
//    fun excluirConteudo(@PathVariable id: Long): ResponseEntity<Void> {
//        return if (conteudoEducacionalService.excluirConteudo(id)) {
//            ResponseEntity.noContent().build()
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }
}