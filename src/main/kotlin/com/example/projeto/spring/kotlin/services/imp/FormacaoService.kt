package com.example.projeto.spring.kotlin.services.imp

import com.example.projeto.spring.kotlin.entities.Aluno
import com.example.projeto.spring.kotlin.entities.Formacao
import com.example.projeto.spring.kotlin.factories.FormacaoFactory.Companion.criarFormacao
import com.example.projeto.spring.kotlin.repositories.FormacaoRepository
import com.example.projeto.spring.kotlin.services.IAlunoService
import com.example.projeto.spring.kotlin.services.IFormacaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FormacaoService(private val formacaoRepository: FormacaoRepository, private val alunoService: IAlunoService) : IFormacaoService {
//    @Autowired
//    private lateinit var formacaoRepository: FormacaoRepository
//
//    @Autowired
//    private lateinit var alunoService: IAlunoService

    override fun matricular(alunoId: Long, formacaoId: Long) {
        val formacao = findById(formacaoId)
        val aluno = alunoService.findById(alunoId)

        if (podeSeMatricular(aluno, formacao)) {
            formacao.inscritos.add(aluno)
            save(formacao)
        } else {
            throw Exception("Não foi possivel cadastrar o aluno: ${aluno.nome} na formacao ${formacao.nome}")
        }
    }

    override fun existsByNome(nomeFormacao: String): Boolean {
        return formacaoRepository.existsByNome(nomeFormacao)
    }

//    fun removerInscrito(alunoId: Long, formacaoId: Long) {
//        val formacao = findById(formacaoId)
//
//        formacao.inscritos.removeIf { it.id == alunoId }
//        save(formacao)
//    }

    fun removerInscrito(alunoId: Long, formacaoId: Long) {
        val formacao = findById(formacaoId)

        formacao.inscritos.removeIf { it.id == alunoId }
        formacaoRepository.save(formacao)
    }

    override fun criaFormacao(nome: String): Formacao {
        val existe = existsByNome(nome)

        return if (!existe) {
            formacaoRepository.save(criarFormacao(nome))
        } else {
            throw Exception("Formação $nome ja Existe")
        }
    }

    fun save(formacao: Formacao): Formacao = formacaoRepository.save(formacao)

    override fun findAll(): List<Formacao> = formacaoRepository.findAll()
    fun findAllAlunos(formacaoId: Long) = findById(formacaoId).inscritos
    fun findAllConteudos(formacaoId: Long) = findById(formacaoId).conteudos

    override fun findById(formacaoId: Long): Formacao =
        formacaoRepository.findByIdOrNull(formacaoId) ?: throw Exception("Formação $formacaoId não encontrada")

    private fun poassuiAssinaturaRequerida(aluno: Aluno, formacao: Formacao) =
        aluno.assinatura.indice >= formacao.assinaturaMinima.indice

    private fun poassuiNivelRequerido(aluno: Aluno, formacao: Formacao) = aluno.nivel.indice >= formacao.nivel.indice
    fun podeSeMatricular(aluno: Aluno, formacao: Formacao) =
        poassuiAssinaturaRequerida(aluno, formacao) && poassuiNivelRequerido(aluno, formacao)
}