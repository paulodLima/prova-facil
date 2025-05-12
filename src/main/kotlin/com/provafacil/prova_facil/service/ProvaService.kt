package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Questao
import com.provafacil.prova_facil.model.enums.NivelDificuldade
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProvaService {

    val questao = Questao(
        enunciado = "Qual é a capital do Brasil?",
        respostaCorreta = "Brasília",
        nivel = NivelDificuldade.FACIL,
        serie = "5º ano"
    )

    fun findQuestao(): Questao {
        return questao;
    }
}