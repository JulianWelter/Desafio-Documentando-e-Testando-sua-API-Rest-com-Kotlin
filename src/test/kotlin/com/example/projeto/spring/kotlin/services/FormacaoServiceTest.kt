package com.example.projeto.spring.kotlin.services

import com.example.projeto.spring.kotlin.entities.Aluno
import com.example.projeto.spring.kotlin.entities.Formacao
import com.example.projeto.spring.kotlin.enums.Assinatura
import com.example.projeto.spring.kotlin.enums.Nivel
import com.example.projeto.spring.kotlin.repositories.FormacaoRepository
import com.example.projeto.spring.kotlin.services.imp.FormacaoService
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

class FormacaoServiceTest {
    private lateinit var formacaoService: FormacaoService
    private lateinit var formacaoRepository: FormacaoRepository
    private lateinit var alunoService: IAlunoService

    @BeforeEach
    fun setUp() {
        formacaoRepository = mockk(relaxed = true)
        alunoService = mockk(relaxed = true)
        formacaoService = FormacaoService(formacaoRepository, alunoService)
    }



    @Test
    fun `nao deve matricular aluno em formacao se nao atender aos requisitos`() {
        // given
        val alunoId = 1L
        val formacaoId = 2L
        val aluno = Aluno(id = alunoId, nome = "João", assinatura = Assinatura.PRO, nivel = Nivel.BASICO)
        val formacao = Formacao(id = formacaoId, nome = "Kotlin", assinaturaMinima = Assinatura.FREE, nivel = Nivel.INTERMEDIARIO, conteudos = mutableListOf())

        every { formacaoRepository.findByIdOrNull(formacaoId) } returns formacao
        every { alunoService.findById(alunoId) } returns aluno

        // when
        val exception = assertThrows(Exception::class.java) {
            formacaoService.matricular(alunoId, formacaoId)
        }

        // then
        assertEquals("Não foi possivel cadastrar o aluno: João na formacao Kotlin", exception.message)
        assertFalse(formacao.inscritos.contains(aluno))
        verify(exactly = 0) { formacaoRepository.save(formacao) }
    }



    @Test
    fun `deve criar formacao se nao existir outra com o mesmo nome`() {
        // given
        val nome = "Kotlin"
        val formacao = Formacao(id = 1L, nome = nome, assinaturaMinima = Assinatura.FREE, nivel = Nivel.BASICO, inscritos = mutableSetOf(), conteudos = mutableListOf())

        every { formacaoRepository.existsByNome(nome) } returns false
        every { formacaoRepository.save(any()) } returns formacao

        // when
        val result = formacaoService.criaFormacao(nome)

        // then
        assertEquals(formacao, result)
        verify { formacaoRepository.save(any()) }
    }

    @Test
    fun `nao deve criar formacao se ja existir outra com o mesmo nome`() {
        // given
        val nome = "Kotlin"

        every { formacaoRepository.existsByNome(nome) } returns true

        // when
        val exception = assertThrows(Exception::class.java) {
            formacaoService.criaFormacao(nome)
        }

        // then
        assertEquals("Formação Kotlin ja Existe", exception.message)
        verify(exactly = 0) { formacaoRepository.save(any()) }
    }
}