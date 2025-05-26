package com.provafacil.prova_facil.model.response

import com.provafacil.prova_facil.model.AlternativaErrada

data class PostAlternativaErradaResponse(
    val texto: String,
)
{
    companion object {
        fun fromList(alternativas: List<AlternativaErrada>): List<PostAlternativaErradaResponse> {
            return alternativas.map { PostAlternativaErradaResponse(it.texto) }
        }
    }
}