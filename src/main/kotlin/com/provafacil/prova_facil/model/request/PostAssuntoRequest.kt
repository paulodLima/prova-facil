package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Disciplina

data class PostAssuntoRequest(
    val nome: String,
    val disciplina: Long
) {
    fun toAssuntoModel(disciplina: Disciplina): Assunto {
        return Assunto(
            nome = this.nome,
            disciplina = disciplina
        )
    }
}
