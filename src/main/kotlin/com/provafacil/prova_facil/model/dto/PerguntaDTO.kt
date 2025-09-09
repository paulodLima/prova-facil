package com.provafacil.prova_facil.model.dto

data class PerguntaDTO (val enunciado: String,
                        val respostaCorreta: String?,
                        val alternativas: List<Alternativa>)