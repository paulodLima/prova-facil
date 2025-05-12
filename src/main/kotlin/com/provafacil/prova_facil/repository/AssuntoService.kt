package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Assunto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AssuntoService : JpaRepository<Assunto, Int> {
}