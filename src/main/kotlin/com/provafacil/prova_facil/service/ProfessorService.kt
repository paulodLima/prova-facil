package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.repository.ProfessorRepository
import org.springframework.stereotype.Service

@Service
class ProfessorService (
    private val repository: ProfessorRepository
){
    fun listarProfessorByEmail(email: String): Professor? {
        return repository.findByEmail(email);
    }
}