package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.model.enums.NivelDificuldade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PerguntaRepository : JpaRepository<Pergunta, Long> {
    fun findByUsuarioId(professorId: Int): List<Pergunta>
    fun findByUsuarioIdAndNivel(professorId: Int, nivel: NivelDificuldade): List<Pergunta>
    fun findByUsuarioIdAndNivelAndSerieAndAssunto(professorId: Int, nivel: NivelDificuldade, serie: Serie, assunto: Assunto): List<Pergunta>
}