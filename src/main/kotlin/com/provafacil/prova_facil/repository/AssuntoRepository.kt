package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Assunto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AssuntoRepository : JpaRepository<Assunto, Long> {
    @Query("""
        select distinct a
        from Assunto a
        join a.disciplina s
        join s.professores p
        where p.id = :professorId
    """)
    fun findAllByProfessorId(@Param("professorId") professorId: Long): List<Assunto>
}