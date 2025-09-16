package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Disciplina
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DisciplinaRepository : JpaRepository<Disciplina, Long> {

    @Query("""
        select distinct a
        from Disciplina a
        join a.professores p
        where p.id = :professorId
    """)
    fun buscarTodasDisciplinasPorProfessor(@Param("professorId") professorId: Int): List<Disciplina>
}