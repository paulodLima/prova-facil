package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.AlternativaErrada
import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Pergunta

data class PostAlternativaErradaRequest(
    val texto: String,
    val idPergunta: Long
) {
    fun toAlternativaErradaModel(alternativa: AlternativaErrada, pergunta: Pergunta): AlternativaErrada {
        return AlternativaErrada(
            texto = this.texto ?: alternativa.texto,
            pergunta = pergunta,
        )
    }
}
