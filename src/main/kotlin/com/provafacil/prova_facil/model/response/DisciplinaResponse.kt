package com.provafacil.prova_facil.model.response

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Disciplina

class DisciplinaResponse(
    val codigo: Long,
    val descricao: String,
    val assuntos: List<AssuntoResponse>
) {
    constructor(disciplina: Disciplina) : this(
        codigo = disciplina.id,
        descricao = disciplina.nome,
        assuntos = disciplina.assuntos.map { AssuntoResponse(it) }
    )
}