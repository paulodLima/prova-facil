package com.provafacil.prova_facil.model.request

data class ProvaRequest(
    val totalQuestoes: Int,
    val facil: Int,
    val medio: Int,
    val dificil: Int
)