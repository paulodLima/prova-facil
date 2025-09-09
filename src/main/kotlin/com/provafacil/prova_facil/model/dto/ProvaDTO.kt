package com.provafacil.prova_facil.model.dto

data class ProvaDTO(
    val serie: String,
    val professor: String,
    val disciplina: String,
    val perguntas: List<PerguntaDTO>
)
