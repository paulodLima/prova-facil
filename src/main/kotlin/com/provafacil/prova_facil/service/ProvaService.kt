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
import com.provafacil.prova_facil.util.ImgUtil
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperExportManager
import net.sf.jasperreports.engine.JasperFillManager
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.io.File

@Service
class ProvaService(
    private val repository: PerguntaRepository,
    private val serieService: SerieService,
    private val assuntoService: AssuntoService,
    val imageUtil: ImgUtil,
    private val disciplinaService: DisciplinaService
) {

    fun sortearPerguntasPorProfessor(
        userId: Int,
        request: ProvaRequest
    ): List<PerguntasRequest> {
        val perguntasPorProfessor: MutableList<Pergunta> = mutableListOf()
        request.assunto.forEach { a ->
            val assunto = assuntoService.buscarAssuntoPorId(a)

            val serie = serieService.buscarSeriePorId(request.serie)

            perguntasPorProfessor.addAll(
                embaralharAlternativas(
                    repository.findByUsuarioIdAndNivelAndSerieAndAssunto(
                        userId,
                        NivelDificuldade.FACIL,
                        serie,
                        assunto
                    )
                        .shuffled()
                        .take(request.facil)
                )
            )
            perguntasPorProfessor.addAll(
                embaralharAlternativas(
                    repository.findByUsuarioIdAndNivelAndSerieAndAssunto(
                        userId,
                        NivelDificuldade.MEDIO,
                        serie,
                        assunto
                    )
                        .shuffled()
                        .take(request.medio)
                )
            )
            perguntasPorProfessor.addAll(
                embaralharAlternativas(
                    repository.findByUsuarioIdAndNivelAndSerieAndAssunto(
                        userId,
                        NivelDificuldade.DIFICIL,
                        serie,
                        assunto
                    )
                        .shuffled()
                        .take(request.dificil)
                )
            )
        }
        return perguntasPorProfessor
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
        val disciplina = disciplinaService.buscarDisciplinaPorId(request.disciplina)
        return ProvaDTO(
            serie = perguntas.firstOrNull()?.serie?.nome ?: "",
            professor = perguntas.firstOrNull()?.professor?.nome
                ?.lowercase()
                ?.replaceFirstChar { it.uppercase() }
                ?: "",
            disciplina = disciplina.nome.lowercase()
                .replaceFirstChar { it.uppercase() }
                ?: "",
            logoSecretaria = perguntas.firstOrNull()?.logoSecretaria,
            logoEscola = perguntas.firstOrNull()?.logoEscola,
            nomeEscola = perguntas.firstOrNull()?.nomeEscola?.uppercase() ?: "",
            estado = perguntas.firstOrNull()?.estado?.uppercase() ?: "",
            perguntas = perguntas.map {
                PerguntaDTO(
                    enunciado = it.enunciado,
                    respostaCorreta = it.respostaCorreta,
                    tipo = it.tipo,
                    alternativas = it.alternativasErradas.map { alt -> Alternativa(alt.texto) },
                    imagem = it.imagem
                )
            }
        )
    }
}