package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Escola
import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.model.Serie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EscolaRepository : JpaRepository<Escola, Int> {

    @Query("""
        select e
        from Escola e
        join e.professores p
        where p.id = :professorId
    """)
    fun findByProfessorId(@Param("professorId") professorId: Int): Escola?
}