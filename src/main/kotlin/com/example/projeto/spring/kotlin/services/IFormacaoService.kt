package com.example.projeto.spring.kotlin.services

import com.example.projeto.spring.kotlin.entities.Formacao

interface IFormacaoService {
    fun criaFormacao(nome: String): Formacao
    fun findAll(): List<Formacao>
    fun findById(formacaoId: Long): Formacao
    fun matricular(alunoId: Long, formacaoId: Long)
    fun existsByNome(nomeFormacao: String):Boolean
}