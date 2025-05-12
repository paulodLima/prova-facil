package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Professor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : JpaRepository<Professor, Int> {
    fun findByEmail(email: String): Professor
}