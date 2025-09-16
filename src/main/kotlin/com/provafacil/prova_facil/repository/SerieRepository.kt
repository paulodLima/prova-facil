package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.model.Serie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SerieRepository : JpaRepository<Serie, Long> {

    @Query("""
    select distinct s
    from Serie s
    join s.professores p
    where p.id = :professorId
""")
    fun findAllByProfessorId(@Param("professorId") professorId: Int): List<Serie>

}