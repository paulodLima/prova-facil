package com.provafacil.prova_facil.model.response

import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.model.enums.Roles

data class ProfessorResponse(
    val nome: String,
    val email: String,
    val disciplina: String,
    val roles: Set<Roles>?
) {
    constructor(professor: Professor) : this(
        nome = professor.nome,
        email = professor.email,
        roles = professor.roles,
        disciplina = professor.disciplina
    )
}
