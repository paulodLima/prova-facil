package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.Escola

data class PostEscolaRequest (
    val nome: String,

    val email: String,

    val estado: String,

    val secretaria: String,
) {
    fun toEscolaModel(): Escola {
        return Escola(
            nome = this.nome,
            email = this.email,
            estado = this.estado,
            secretaria = this.secretaria
        )

    }
}