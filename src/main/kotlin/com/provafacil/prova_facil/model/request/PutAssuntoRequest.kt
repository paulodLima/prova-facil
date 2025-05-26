package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Assunto

data class PutAssuntoRequest(
    val nome: String
) {
    fun toAssuntoModel(assunto: Assunto): Assunto {
        return Assunto(
            id = assunto.id,
            nome = this.nome ?: assunto.nome,
        )
    }
}
