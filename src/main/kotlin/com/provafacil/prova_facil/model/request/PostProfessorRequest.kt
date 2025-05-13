package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.model.Serie

data class PostProfessorRequest(
    val nome: String,
    val email: String,
    val senha: String
) {
    fun toProfessorModel(): Professor {
        if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
            throw IllegalArgumentException("Nome, email e senha são obrigatórios.")
        }
        return Professor (
            nome = this.nome,
            email = this.email,
            senha = this.senha
        )
    }
}
