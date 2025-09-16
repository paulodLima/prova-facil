package com.provafacil.prova_facil.model.response

import com.provafacil.prova_facil.model.Assunto

data class AssuntoResponse(val nome: String, val id: Long) {
    constructor(assunto: Assunto) : this(
        nome = assunto.nome,
        id = assunto.id
    )
    fun toAssuntoModel(assunto: Assunto): Assunto {
        return Assunto(
            id = assunto.id,
            nome = this.nome,
            disciplina = assunto.disciplina
        )
    }
}