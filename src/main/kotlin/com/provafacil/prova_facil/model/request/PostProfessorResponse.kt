package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Usuario
import com.provafacil.prova_facil.model.enums.UsuarioTipo
import com.provafacil.prova_facil.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostProfessorResponse(
    @field:NotEmpty(message = "Nome deve ser preenchido")
    val nome: String,

    @field:NotEmpty
    @field:Email(message = "Email não pode ser vazio e nem invalido")
    @EmailAvailable
    val email: String,

    @field:NotEmpty(message = "senha deve ser informada")
    val senha: String,

    val disciplina: List<Long>,

    val serie: List<Long>,

    val escola: String,

    val tipo: UsuarioTipo
) {
    fun toProfessorModel(): Usuario {
        if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
            throw IllegalArgumentException("Nome, email e senha são obrigatórios.")
        }
        return Usuario (
            nome = this.nome,
            email = this.email,
            senha = this.senha,
            tipo = this.tipo
        )
    }
}
