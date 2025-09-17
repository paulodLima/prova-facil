package com.provafacil.prova_facil.model.dto

import jakarta.persistence.Column

data class ProvaDTO(
    val serie: String,
    val professor: String,
    val disciplina: String,
    val nomeEscola: String,
    val estado: String,
    var logoEscola: ByteArray? = null,
    var logoSecretaria: ByteArray? = null,
    val perguntas: List<PerguntaDTO>
)
