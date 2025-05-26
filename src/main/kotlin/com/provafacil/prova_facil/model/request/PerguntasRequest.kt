package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.model.enums.TipoPergunta
import com.provafacil.prova_facil.model.response.AlternativaErradaResponse
import com.provafacil.prova_facil.model.response.ProfessorResponse
import java.time.LocalDateTime

data class PerguntasRequest (
    val id: Long = 0,

    val enunciado: String,

    val tipo: String,

    val nivel: String,

    val respostaCorreta: String? = null,

    val serie: Serie,

    val assunto: Assunto,

    val professor: ProfessorResponse,

    val dataCriacao: LocalDateTime,

    val alternativasErradas: List<AlternativaErradaResponse> = emptyList()
){
    constructor(pergunta: Pergunta) : this(
        id = pergunta.id,
        enunciado = pergunta.enunciado,
        tipo = pergunta.tipo.name,
        serie = pergunta.serie,
        assunto = pergunta.assunto,
        dataCriacao = pergunta.dataCriacao,
        nivel = pergunta.nivel.toString(),
        professor = ProfessorResponse(pergunta.professor),
        respostaCorreta = pergunta.respostaCorreta,
        alternativasErradas = AlternativaErradaResponse.fromList(pergunta.alternativasErradas.sortedBy { p -> p.id })
    )
}