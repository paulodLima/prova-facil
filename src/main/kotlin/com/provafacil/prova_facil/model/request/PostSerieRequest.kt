package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Serie

data class PostSerieRequest(
    val nome: String
) {
    fun toSerieModel(): Serie {
        return Serie(
            nome = this.nome
        )
    }
}
