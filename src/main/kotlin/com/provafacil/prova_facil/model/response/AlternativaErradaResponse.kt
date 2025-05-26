package com.provafacil.prova_facil.model.response

import com.provafacil.prova_facil.model.AlternativaErrada

data class AlternativaErradaResponse(
    val texto: String,
    val id: Long,
    val idPergunta: Long
)
{
    companion object {
        fun fromList(alternativas: List<AlternativaErrada>): List<AlternativaErradaResponse> {
            return alternativas.map { AlternativaErradaResponse(it.texto, it.id, it.pergunta.id) }
        }
    }
}