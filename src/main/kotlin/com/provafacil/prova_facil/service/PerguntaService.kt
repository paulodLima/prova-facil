package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.AlternativaErrada
import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.model.enums.NivelDificuldade
import com.provafacil.prova_facil.model.enums.TipoPergunta
import com.provafacil.prova_facil.model.request.PerguntasRequest
import com.provafacil.prova_facil.model.request.PostPerguntaRequest
import com.provafacil.prova_facil.model.response.PerguntasResponse
import com.provafacil.prova_facil.repository.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PerguntaService (
    private val repository: PerguntaRepository,
    private val serieRepository: SerieRepository,
    private val assuntoRepository: AssuntoRepository,
    private val usuarioRepository: UsuarioRepository,
    private val alternativaErradaRepository: AlternativaErradaRepository
){
    fun litarTodasPerguntas(pegeable: Pageable, userId: Long): Page<PerguntasRequest>? {
        return repository.findAll(pegeable).map { PerguntasRequest(it) }
    }
    fun litarTodasPerguntasPorProfessor(userId: Int): List<PerguntasRequest>? {
        return repository.findByUsuarioId(userId).map { PerguntasRequest(it) }
    }

    fun buscarPorId(id: Long): PerguntasRequest {
        val response =  repository.findById(id).orElse(null);
        return PerguntasRequest(response);
    }

    fun atualizarPergunta(put: PerguntasResponse) {
        val pergunta = repository.findById(put.id)
            .orElseThrow { RuntimeException("Pergunta com ID ${put.id} não encontrada") }

        pergunta.enunciado = put.enunciado
        pergunta.tipo = put.tipo.let { TipoPergunta.fromCodigo(it) }
        pergunta.nivel = put.nivel.let { NivelDificuldade.valueOf(it) }
        pergunta.respostaCorreta = put.respostaCorreta ?: pergunta.respostaCorreta

        put.serie.let {
            pergunta.serie = serieRepository.findById(it)
                .orElseThrow { RuntimeException("Série com ID $it não encontrada") }
        }

        put.assunto.let {
            pergunta.assunto = assuntoRepository.findById(it)
                .orElseThrow { RuntimeException("Assunto com ID $it não encontrado") }
        }

        if(put.tipo == TipoPergunta.DISSERTATIVA.codigo){
            alternativaErradaRepository.findByPerguntaId(put.id).forEach {
                alternativaErradaRepository.delete(it)
            }
        }
        repository.save(pergunta)
    }

    fun criarPergunta(dto: PostPerguntaRequest, imagem: ByteArray?) {
        val serie = serieRepository.findById(dto.serie)
            .orElseThrow { RuntimeException("Série não encontrada") }

        val assunto = assuntoRepository.findById(dto.assunto)
            .orElseThrow { RuntimeException("Assunto não encontrado") }

        val professor = usuarioRepository.findById(dto.professor)
            .orElseThrow { RuntimeException("Professor não encontrado") }


        val pergunta = Pergunta (
            enunciado = dto.enunciado,
            tipo = when (dto.tipo) {
                1L -> TipoPergunta.MULTIPLAESCOLHA
                2L -> TipoPergunta.DISSERTATIVA
                else -> throw IllegalArgumentException("Tipo inválido")
            },
            respostaCorreta = dto.respostaCorreta,
            nivel = NivelDificuldade.valueOf(dto.dificuldade),
            serie = serie,
            assunto = assunto,
            usuario = professor,
            imagem = imagem
        )

        dto.alternativasErradas.forEach {
            pergunta.alternativasErradas.add(
                AlternativaErrada(
                    texto = it.texto,
                    pergunta = pergunta
                )
            )
        }
        repository.save(pergunta)
    }

    fun excluirPergunta(id: Long) {
        repository.deleteById(id)
    }
}