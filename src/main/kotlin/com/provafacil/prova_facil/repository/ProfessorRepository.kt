package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Professor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProfessorRepository : JpaRepository<Professor, Int> {
    fun findByEmailEqualsIgnoreCase(email: String): Optional<Professor>
    fun existsByEmail(email: String): Boolean
}