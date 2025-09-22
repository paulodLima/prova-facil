package com.provafacil.prova_facil.model.response

import com.provafacil.prova_facil.model.Escola
import jakarta.persistence.Column

data class EscolaResponse(
    val nome: String,

    val id: Int,

    val email: String,

    val estado: String,

    @Column(name = "logoEscola", columnDefinition = "bytea")
    var logoEscola: ByteArray? = null,

    @Column(name = "logoSecretaria", columnDefinition = "bytea")
    var logoSecretaria: ByteArray? = null
) {
    constructor(escola: Escola) : this(
        nome = escola.nome.lowercase().replaceFirstChar { it.uppercase() },
        id = escola.id,
        email = escola.email.lowercase(),
        estado = escola.estado,
        logoEscola = escola.logoEscola,
        logoSecretaria = escola.logoSecretaria,
    )
}
