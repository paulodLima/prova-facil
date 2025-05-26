package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.AlternativaErrada
import com.provafacil.prova_facil.model.request.PostAlternativaErradaRequest
import com.provafacil.prova_facil.repository.AlternativaErradaRepository
import com.provafacil.prova_facil.repository.PerguntaRepository
import org.springframework.stereotype.Service

@Service
class AlternativaErradaService(
    private val repository: AlternativaErradaRepository,
    private val perguntaRepository: PerguntaRepository
) {

    fun buscarAlternaticaErradaPorId(id: Int): AlternativaErrada = repository.findById(id).get();

    fun atualizarAlternaticaErrada(alternativa: AlternativaErrada) {
        repository.save(alternativa);
    }
    fun adicionarAlternaticaErrada(alternativaRequest: PostAlternativaErradaRequest) {
        val pergunta = perguntaRepository.findById(alternativaRequest.idPergunta).get()

        val alternativa = AlternativaErrada(
            texto = alternativaRequest.texto,
            pergunta = pergunta
        )
        repository.save(alternativa);
    }

    fun excluirAlternaticaErrada(id: Int) {
        repository.deleteById(id);
    }

}