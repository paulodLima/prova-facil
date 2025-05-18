package com.provafacil.prova_facil.model.response

import com.provafacil.prova_facil.model.Serie

data class SerieResponse(val nome: String){
    constructor(serie: Serie) : this(
        nome = serie.nome
    )
}
