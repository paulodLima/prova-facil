package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.model.enums.NivelDificuldade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PerguntaRepository : JpaRepository<Pergunta, Long> {
    fun findByProfessorId(professorId: Int, pageable: Pageable): Page<Pergunta>
    fun findByProfessorIdAndNivel(professorId: Int, nivel: NivelDificuldade): List<Pergunta>
}