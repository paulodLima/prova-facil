package com.provafacil.prova_facil.model.response


data class PerguntasResponse (
    val id: Long = 0,

    val enunciado: String,

    val tipo: Long,

    val nivel: String,

    val respostaCorreta: String? = null,

    val serie: Long,

    val assunto: Long,

)