package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.repository.PerguntaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PerguntaService (
    private val repository: PerguntaRepository
){
    fun litarTodasPerguntas(pegeable: Pageable): Page<Pergunta>? {
        return repository.findAll(pegeable);
    }

    fun buscarPorId(id: Int): Pergunta? {
        return repository.findById(id).orElse(null);
    }
}