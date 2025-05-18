package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.model.response.PerguntasResponse
import com.provafacil.prova_facil.repository.PerguntaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PerguntaService (
    private val repository: PerguntaRepository
){
    fun litarTodasPerguntas(pegeable: Pageable, userId: Long): Page<PerguntasResponse>? {
        return repository.findAll(pegeable).map { PerguntasResponse(it) }
    }
    fun litarTodasPerguntasPorProfessor(pegeable: Pageable, userId: Int): Page<PerguntasResponse>? {
        //return repository.findAll(pegeable).map { PerguntasResponse(it) }
        return repository.findByProfessorId(userId,pegeable).map { PerguntasResponse(it) }
    }

    fun buscarPorId(id: Int): Pergunta? {
        return repository.findById(id).orElse(null);
    }
}