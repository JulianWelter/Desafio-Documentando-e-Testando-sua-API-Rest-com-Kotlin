package com.example.projeto.spring.kotlin.services.imp

import com.example.projeto.spring.kotlin.entities.ConteudoEducacional
import com.example.projeto.spring.kotlin.repositories.ConteudoRepository
import com.example.projeto.spring.kotlin.services.IConteudoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ConteudoService : IConteudoService {

    @Autowired
    private lateinit var conteudoRepository: ConteudoRepository

    override fun save(conteudoEducacional: ConteudoEducacional): ConteudoEducacional =
        conteudoRepository.save(conteudoEducacional)

    override fun findAll(): List<ConteudoEducacional> = conteudoRepository.findAll()

    override fun findById(conteudoEducacionalId: Long): ConteudoEducacional {
        return conteudoRepository.findByIdOrNull(conteudoEducacionalId)
            ?: throw Exception("Conteudo $conteudoEducacionalId não encontrado")
    }
}