package com.example.projeto.spring.kotlin.entities

import com.example.projeto.spring.kotlin.enums.Assinatura
import com.example.projeto.spring.kotlin.enums.Nivel
import jakarta.persistence.*
import kotlin.reflect.full.memberProperties

@Entity
data class Formacao(
    @Column(nullable = false) val nome: String,
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.REMOVE, CascadeType.PERSIST],
        mappedBy = "formacao"
    ) val conteudos: List<ConteudoEducacional>,
    @Enumerated val nivel: Nivel,
    @Enumerated val assinaturaMinima: Assinatura,
    @ManyToMany(mappedBy = "formacoes") val inscritos: MutableSet<Aluno> = mutableSetOf(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0
) {


//    fun matricular(vararg alunos: Aluno) {
//        for (aluno in alunos) {
//            if (podeSeMatricular(aluno)) {
//                inscritos.add(aluno)
//            } else {
//                println("Não foi possivel cadastrar o aluno: ${aluno.nome} no curso $nome")
//            }
//        }
//        //TODO("Utilize o parâmetro $usuarios para simular uma matrícula (usar a lista de $inscritos).")
//    }

//    private fun poassuiAssinaturaRequerida(aluno: Aluno) = aluno.assinatura.indice >= assinaturaMinima.indice
//    private fun poassuiNivelRequerido(aluno: Aluno) = aluno.nivel.indice >= nivel.indice
//    private fun podeSeMatricular(aluno: Aluno) = poassuiAssinaturaRequerida(aluno) && poassuiNivelRequerido(aluno)

    fun listarInscritos() {
        print("Alunos $nome: ")
//        inscritos.forEachIndexed { index, it ->
//            print("${it.nome}${if (index != inscritos.size - 1) ", " else ""}")
//        }
        imprimirNomes(inscritos)
        println()
    }

    fun listarConteudos() {
        print("Conteudos $nome: ")
//        conteudos.forEachIndexed { index, it ->
//            print("${it.nome}${if (index != conteudos.size - 1) ", " else ""}")
//        }
        imprimirNomes(conteudos)
        println()
    }


    private fun imprimirNomes(elementos: Iterable<Any>) {
        elementos.forEachIndexed { index, elemento ->
            val propriedadeNome = elemento::class.memberProperties.find { it.name == "nome" }
            if (propriedadeNome != null) {
                val nome = propriedadeNome.getter.call(elemento)
                print("${nome}${if (index != conteudos.size - 1) ", " else ""}")
            }
        }
    }

//    fun removerInscrito(aluno: Aluno) = inscritos.remove(aluno)
    private fun estaInscrito(aluno: Aluno): Boolean = inscritos.contains(aluno)

    fun printEstaInscrito(aluno: Aluno) =
        println("${aluno.nome} esta inscrito no $nome? ${if (estaInscrito(aluno)) "Sim" else "Não"}!")

}