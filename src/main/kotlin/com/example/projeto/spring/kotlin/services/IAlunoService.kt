package com.example.projeto.spring.kotlin.services

import com.example.projeto.spring.kotlin.entities.Aluno

interface IAlunoService {
    fun save(aluno: Aluno): Aluno
    fun findAll(): List<Aluno>
    fun findById(alunoId: Long): Aluno
}