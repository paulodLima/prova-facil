package com.provafacil.prova_facil.model.response

import com.provafacil.prova_facil.model.Assunto

data class AssuntoResponse(val nome: String) {
    constructor(assunto: Assunto) : this(
        nome = assunto.nome
    )
}