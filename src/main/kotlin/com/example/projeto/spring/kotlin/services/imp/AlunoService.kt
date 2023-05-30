package com.example.projeto.spring.kotlin.services.imp

import com.example.projeto.spring.kotlin.entities.Aluno
import com.example.projeto.spring.kotlin.repositories.AlunoRepository
import com.example.projeto.spring.kotlin.services.IAlunoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AlunoService : IAlunoService {

    @Autowired
    private lateinit var alunoRepository: AlunoRepository

    override fun save(aluno: Aluno): Aluno = alunoRepository.save(aluno)

    override fun findAll(): List<Aluno> = alunoRepository.findAll()

    override fun findById(alunoId: Long): Aluno {
        return alunoRepository.findByIdOrNull(alunoId) ?: throw Exception("Aluno $alunoId não encontrado")
    }
}