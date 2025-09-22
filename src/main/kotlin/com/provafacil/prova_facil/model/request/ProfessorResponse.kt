package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Usuario
import com.provafacil.prova_facil.model.enums.Roles

data class ProfessorResponse(
    val id: String,

    val nome: String,

    val email: String,

    val roles: MutableSet<Roles> = mutableSetOf(),

){
    constructor(usuario: Usuario) : this(
        id = usuario.id.toString(),
        nome = usuario.nome,
        email = usuario.email,
        roles = usuario.roles
    )
}
