package com.provafacil.prova_facil.model.response

import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.model.enums.TipoPergunta
import java.time.LocalDateTime

data class PerguntasResponse (
    val id: Long = 0,

    val enunciado: String,

    val tipo: String,

    val respostaCorreta: String? = null,

    val serie: SerieResponse,

    val assunto: AssuntoResponse,

    val professor: ProfessorResponse,

    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    val alternativasErradas: List<AlternativaErradaResponse> = emptyList()
){
    constructor(pergunta: Pergunta) : this(
        id = pergunta.id,
        enunciado = pergunta.enunciado,
        tipo = pergunta.tipo.descricao,
        serie = SerieResponse(pergunta.serie),
        assunto = AssuntoResponse(pergunta.assunto),
        professor = ProfessorResponse(pergunta.professor),
        respostaCorreta = pergunta.respostaCorreta,
        alternativasErradas = AlternativaErradaResponse.fromList(pergunta.alternativasErradas.sortedBy { p -> p.id })
    )
}