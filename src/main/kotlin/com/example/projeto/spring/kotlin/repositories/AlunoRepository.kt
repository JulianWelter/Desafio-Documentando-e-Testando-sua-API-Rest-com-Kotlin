package com.example.projeto.spring.kotlin.repositories

import com.example.projeto.spring.kotlin.entities.Aluno
import com.example.projeto.spring.kotlin.entities.Formacao
import org.springframework.data.jpa.repository.JpaRepository

interface AlunoRepository: JpaRepository<Aluno,Long>