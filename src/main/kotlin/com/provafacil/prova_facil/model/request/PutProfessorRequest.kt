package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Disciplina
import com.provafacil.prova_facil.model.Usuario
import com.provafacil.prova_facil.model.enums.UsuarioTipo

data class PutProfessorRequest(
    val nome: String?,
    val email: String?,
    val senha: String?,
    val disciplinaId: Disciplina,
    val tipo: UsuarioTipo?,
) {
    fun toProfessorModel(usuario: Usuario): Usuario {
        return Usuario(
            id = usuario.id,
            nome = this.nome ?: usuario.nome,
            email = this.email ?: usuario.email,
            senha = this.senha ?: usuario.senha,
            tipo = this.tipo ?: usuario.tipo,
        )
    }
}
