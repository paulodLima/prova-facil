package com.provafacil.prova_facil.model.response

import com.provafacil.prova_facil.model.Escola

data class EscolaResponse(val nome: String, val id: Int){
    constructor(escola: Escola) : this(
        nome = escola.nome.lowercase().replaceFirstChar { it.uppercase() },
        id = escola.id
    )
}
