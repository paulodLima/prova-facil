package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Disciplina
import com.provafacil.prova_facil.model.Professor

data class PutProfessorRequest(
    val nome: String?,
    val email: String?,
    val senha: String?,
    val disciplina: String?,
    val disciplinaId: Disciplina
) {
    fun toProfessorModel(professor: Professor, disciplina: Disciplina): Professor {
        return Professor(
            id = professor.id,
            nome = this.nome ?: professor.nome,
            email = this.email ?: professor.email,
            senha = this.senha ?: professor.senha,
            disciplinaDesc = this.disciplina ?:professor.disciplinaDesc,
            disciplina = this.disciplinaId ?:professor.disciplina,
        )
    }
}
