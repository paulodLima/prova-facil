package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.response.AlternativaErradaResponse
import com.provafacil.prova_facil.model.response.PostAlternativaErradaResponse
import java.time.LocalDateTime

data class PostPerguntaRequest (
    val enunciado: String,

    val tipo: Long,

    val dificuldade: String,

    val respostaCorreta: String,

    val serie: Long,

    val assunto: Long,

    var professor: Int,

    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    val alternativasErradas: List<PostAlternativaErradaResponse> = emptyList()
)