package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Disciplina

data class PutAssuntoRequest(
    val nome: String,
    val disciplinaId: Long
) {
    fun toAssuntoModel(assunto: Assunto, disciplina: Disciplina): Assunto {
        return Assunto(
            id = assunto.id,
            nome = this.nome ?: assunto.nome,
            disciplina = disciplina
        )
    }
}
