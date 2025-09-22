package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Int> {
    fun findByEmailEqualsIgnoreCase(email: String): Optional<Usuario>
    fun existsByEmail(email: String): Boolean
    fun findByEmailIgnoreCase(email: String): Usuario?
    fun findByEscolaId(id: Int): Usuario?
}