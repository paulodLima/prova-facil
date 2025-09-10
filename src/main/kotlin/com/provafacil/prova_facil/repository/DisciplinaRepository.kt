package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Disciplina
import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.model.Serie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DisciplinaRepository : JpaRepository<Disciplina, Long> {
}