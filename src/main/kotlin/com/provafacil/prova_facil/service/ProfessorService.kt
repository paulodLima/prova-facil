package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.exceptions.NotFoundException
import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.model.enums.Roles
import com.provafacil.prova_facil.repository.ProfessorRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProfessorService (
    private val repository: ProfessorRepository,
    private val passwordEncoder: PasswordEncoder
){
    fun listarProfessorByEmail(email: String): Professor? =  repository.findByEmail(email).orElseThrow { NotFoundException("O email [$email] não existe", "0001") }

    fun listarTodos(): List<Professor>  = repository.findAll().sortedBy { it.nome }

    fun atualizar(professor: Professor) {
        repository.save(professor)
    }

    fun buscarPorId(id: Int): Professor = repository.findById(id).orElseThrow { NotFoundException("O professor de [$id] não existe", "0001") }

    fun deletarProfessor(id: Int) {
        repository.deleteById(id);
    }

    fun adicionarProfessor(professor: Professor) {
        val copy = professor.copy(
            roles = setOf(Roles.USER),
            senha = passwordEncoder.encode(professor.senha)
        )
        repository.save(copy)
    }

    fun emailAvailable(email: String): Boolean {
        return !repository.existsByEmail(email);
    }

    fun findById(id: Int): Optional<Professor> {
        return repository.findById(id);
    }
}