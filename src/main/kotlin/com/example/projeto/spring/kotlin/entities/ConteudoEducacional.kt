package com.example.projeto.spring.kotlin.entities

import com.example.projeto.spring.kotlin.enums.Nivel
import jakarta.persistence.*
@Entity
data class ConteudoEducacional(
    @Column(nullable = false) val nome: String,
    @Column(nullable = false) val duracao: Int = 60,
    @ManyToOne var formacao: Formacao? = null,
    @Enumerated val nivelRequerido: Nivel = Nivel.BASICO,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0
)