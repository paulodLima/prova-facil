package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.AlternativaErrada
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlternativaErradaRepository : JpaRepository<AlternativaErrada, Int> {
    fun findByPerguntaId(perguntaId: Long): List<AlternativaErrada>
}