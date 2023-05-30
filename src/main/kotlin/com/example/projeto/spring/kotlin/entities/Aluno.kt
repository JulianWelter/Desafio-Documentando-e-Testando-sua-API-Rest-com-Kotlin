package com.example.projeto.spring.kotlin.entities

import com.example.projeto.spring.kotlin.enums.Assinatura
import com.example.projeto.spring.kotlin.enums.Nivel
import jakarta.persistence.*

@Entity
class Aluno(
    @Column(nullable = false) val nome: String,
    @Enumerated var assinatura: Assinatura = Assinatura.FREE,
    @Enumerated var nivel: Nivel = Nivel.BASICO,
    @ManyToMany @JoinTable(
        name = "aluno_formacao",
        joinColumns = [JoinColumn(name = "aluno_id")],
        inverseJoinColumns = [JoinColumn(name = "formacao_id")]
    ) val formacoes: List<Formacao> = mutableListOf(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0
)

