package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.repository.ProfessorRepository
import org.springframework.stereotype.Service

@Service
class ProfessorService (
    private val repository: ProfessorRepository
){
    fun listarProfessorByEmail(email: String): Professor? =  repository.findByEmail(email);

    fun listarTodos(): List<Professor>  = repository.findAll().sortedBy { it.nome }

    fun atualizar(professor: Professor) {
        repository.save(professor)
    }

    fun buscarPorId(id: Int): Professor = repository.findById(id).get();

    fun deletarProfessor(id: Int) {
        repository.deleteById(id);
    }

    fun adicionarProfessor(professor: Professor) {
        repository.save(professor)
    }
}