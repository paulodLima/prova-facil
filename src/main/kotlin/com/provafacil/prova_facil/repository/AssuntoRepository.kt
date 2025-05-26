package com.provafacil.prova_facil.repository

import com.provafacil.prova_facil.model.Assunto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AssuntoRepository : JpaRepository<Assunto, Long> {
}