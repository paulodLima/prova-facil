package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.request.PostAssuntoRequest
import com.provafacil.prova_facil.repository.AssuntoRepository
import com.provafacil.prova_facil.util.ProfessorRelacionamentoUtil
import org.springframework.stereotype.Service

@Service
class AssuntoService(
    private val repository: AssuntoRepository,
    private val util: ProfessorRelacionamentoUtil,
    private val disciplinaService: DisciplinaService
) {
    fun buscarTodosAssuntos(userId: Long): List<Assunto> =
        repository.findAllByProfessorId(userId)//findAllByDisciplina_Professor_Id(userId).sortedBy { it.id };

    fun buscarAssuntoPorId(id: Long): Assunto = repository.findById(id).get();

    fun atualizarAssunto(assunto: Assunto) {
        repository.save(assunto);
    }

    fun adicionarAssunto(assunto: Assunto) {
        repository.save(assunto);
    }

    fun adicionarAssuntoComProfessor(assunto: PostAssuntoRequest, profId: Int) {
        val disciplina = disciplinaService.buscarDisciplinaPorId(assunto.disciplina);
        repository.save(assunto.toAssuntoModel(disciplina));
    }

    fun excluirAssunto(id: Long) {
        repository.deleteById(id);
    }

}