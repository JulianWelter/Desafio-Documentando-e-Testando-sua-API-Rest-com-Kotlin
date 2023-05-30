package com.example.projeto.spring.kotlin.repositories

import com.example.projeto.spring.kotlin.entities.Formacao
import org.springframework.data.jpa.repository.JpaRepository

interface FormacaoRepository: JpaRepository<Formacao,Long>{
    fun existsByNome(nome: String): Boolean
}