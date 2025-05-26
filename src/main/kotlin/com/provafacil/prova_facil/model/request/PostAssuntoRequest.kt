package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Assunto

data class PostAssuntoRequest(
    val nome: String
) {
    fun toAssuntoModel(): Assunto {
        return Assunto(
            nome = this.nome
        )
    }
}
