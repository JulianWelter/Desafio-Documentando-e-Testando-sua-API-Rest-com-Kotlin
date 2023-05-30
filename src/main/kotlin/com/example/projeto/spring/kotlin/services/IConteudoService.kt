package com.example.projeto.spring.kotlin.services

import com.example.projeto.spring.kotlin.entities.ConteudoEducacional

interface IConteudoService {
    fun save(conteudoEducacional: ConteudoEducacional): ConteudoEducacional
    fun findAll(): List<ConteudoEducacional>
    fun findById(conteudoEducacionalId: Long): ConteudoEducacional
}