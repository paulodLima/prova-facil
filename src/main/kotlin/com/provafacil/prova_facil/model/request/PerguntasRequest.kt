package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.model.enums.TipoPergunta
import com.provafacil.prova_facil.model.response.AlternativaErradaResponse
import com.provafacil.prova_facil.model.response.AssuntoResponse
import com.provafacil.prova_facil.model.response.DisciplinaResponse
import com.provafacil.prova_facil.model.response.ProfessorResponse
import java.time.LocalDateTime

data class PerguntasRequest (
    val id: Long = 0,

    val enunciado: String,

    val tipo: String,

    val nivel: String,

    val nomeEscola: String,

    val estado: String,

    val respostaCorreta: String? = null,

    val imagem: ByteArray? = null,

    val serie: Serie,

    val assunto: AssuntoResponse,

    val disciplina: DisciplinaResponse,

    val professor: ProfessorResponse,

    var logoEscola: ByteArray? = null,

    var logoSecretaria: ByteArray? = null,

    val dataCriacao: LocalDateTime,


    val alternativasErradas: List<AlternativaErradaResponse> = emptyList()
){
    constructor(pergunta: Pergunta) : this(
        id = pergunta.id,
        enunciado = pergunta.enunciado,
        tipo = pergunta.tipo.name,
        serie = pergunta.serie,
        assunto = AssuntoResponse(pergunta.assunto),
        disciplina = DisciplinaResponse(pergunta.assunto.disciplina),
        dataCriacao = pergunta.dataCriacao,
        nivel = pergunta.nivel.toString(),
        imagem = pergunta.imagem,
        professor = ProfessorResponse(pergunta.professor),
        logoSecretaria = pergunta.professor.escola?.logoSecretaria,
        logoEscola = pergunta.professor.escola?.logoEscola,
        nomeEscola = pergunta.professor.escola?.nome.toString(),
        estado = pergunta.professor.escola?.estado.toString(),
        respostaCorreta = pergunta.respostaCorreta,
        alternativasErradas = AlternativaErradaResponse.fromList(pergunta.alternativasErradas.sortedBy { p -> p.id })
    )
}