package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.AlternativaErrada
import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.model.Questao
import com.provafacil.prova_facil.model.dto.Alternativa
import com.provafacil.prova_facil.model.dto.PerguntaDTO
import com.provafacil.prova_facil.model.dto.ProvaDTO
import com.provafacil.prova_facil.model.enums.NivelDificuldade
import com.provafacil.prova_facil.model.request.PerguntasRequest
import com.provafacil.prova_facil.model.request.ProvaRequest
import com.provafacil.prova_facil.repository.PerguntaRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProvaService(private val repository: PerguntaRepository) {

    fun sortearPerguntasPorProfessor(
        userId: Int,
        request: ProvaRequest
    ): List<PerguntasRequest> {

        val faceis = embaralharAlternativas(
            repository.findByProfessorIdAndNivel(userId, NivelDificuldade.FACIL)
                .shuffled()
                .take(request.facil)
        )
        val medias = embaralharAlternativas(
            repository.findByProfessorIdAndNivel(userId, NivelDificuldade.MEDIO)
                .shuffled()
                .take(request.medio)
        )
        val dificieis = embaralharAlternativas(
            repository.findByProfessorIdAndNivel(userId, NivelDificuldade.DIFICIL)
                .shuffled()
                .take(request.dificil)
        )
        return (faceis + medias + dificieis)
            .shuffled()
            .map { PerguntasRequest(it) }
    }

    fun embaralharAlternativas(perguntas: List<Pergunta>): List<Pergunta> {
        return perguntas.map { pergunta ->
            val todasAlternativas = mutableListOf<String>()
            todasAlternativas.add(pergunta.respostaCorreta ?: "")
            todasAlternativas.addAll(pergunta.alternativasErradas.map { it.texto })

            val alternativasEmbaralhadas = todasAlternativas.shuffled().map { texto ->
                AlternativaErrada(texto = texto, pergunta = pergunta)
            }.toMutableList()

            pergunta.copy(alternativasErradas = alternativasEmbaralhadas)
        }
    }

    fun gerarProva(userId: Int, request: ProvaRequest): ProvaDTO {
        val perguntas = sortearPerguntasPorProfessor(userId, request)

        return ProvaDTO(
            serie = perguntas.firstOrNull()?.serie?.nome ?: "",
            professor = perguntas.firstOrNull()?.professor?.nome?.uppercase() ?: "",
            disciplina = perguntas.firstOrNull()?.assunto?.nome?.uppercase() ?: "",
            perguntas = perguntas.map {
                PerguntaDTO(
                    enunciado = it.enunciado,
                    respostaCorreta = it.respostaCorreta,
                    alternativas = it.alternativasErradas.map { alt -> Alternativa(alt.texto) }
                )
            }
        )
    }
}