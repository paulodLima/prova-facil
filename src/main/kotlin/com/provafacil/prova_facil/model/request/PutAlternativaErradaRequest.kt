package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.AlternativaErrada
import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.model.response.PerguntasResponse

data class PutAlternativaErradaRequest(
    val texto: String,
    val id: Long,
    val idPergunta: Int
)
