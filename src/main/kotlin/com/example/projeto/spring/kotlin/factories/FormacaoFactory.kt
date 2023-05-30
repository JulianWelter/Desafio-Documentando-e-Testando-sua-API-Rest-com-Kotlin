package com.example.projeto.spring.kotlin.factories

import com.example.projeto.spring.kotlin.entities.ConteudoEducacional
import com.example.projeto.spring.kotlin.entities.Formacao
import com.example.projeto.spring.kotlin.enums.Assinatura
import com.example.projeto.spring.kotlin.enums.Nivel

class FormacaoFactory {
    companion object {
        fun criarFormacao(nomeDaFormacao: String, conteudos: List<ConteudoEducacional> = listOf()) =
            when (nomeDaFormacao.lowercase()) {
                "logica" -> Formacao("Lógica", conteudos, Nivel.BASICO, Assinatura.FREE)
                "kotlin" -> Formacao("Kotlin", conteudos, Nivel.INTERMEDIARIO, Assinatura.PRO)
                "java" -> Formacao("Java", conteudos, Nivel.INTERMEDIARIO, Assinatura.FREE)
                "php" -> Formacao("PHP", conteudos, Nivel.INTERMEDIARIO, Assinatura.GLOBAL)
                "docker" -> Formacao("Docker", conteudos, Nivel.AVANCADO, Assinatura.FREE)
                else -> throw IllegalArgumentException("Formação inválida: $nomeDaFormacao");
            }
    }
}