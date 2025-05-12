package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Pergunta
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PerguntaRepository : JpaRepository<Pergunta, Int> {
}