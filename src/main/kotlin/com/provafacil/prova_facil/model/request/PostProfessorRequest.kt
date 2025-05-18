package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostProfessorRequest(
    @field:NotEmpty(message = "Nome deve ser preenchido")
    val nome: String,

    @field:NotEmpty
    @field:Email(message = "Email não pode ser vazio e nem invalido")
    @EmailAvailable
    val email: String,

    @field:NotEmpty(message = "senha deve ser informada")
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
