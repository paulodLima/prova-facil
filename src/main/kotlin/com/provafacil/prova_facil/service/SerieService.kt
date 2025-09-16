package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.model.request.PostSerieProfessorRequest
import com.provafacil.prova_facil.model.request.PostSerieRequest
import com.provafacil.prova_facil.repository.SerieRepository
import com.provafacil.prova_facil.util.ProfessorRelacionamentoUtil
import org.springframework.stereotype.Service

@Service
class SerieService(
    private val repository: SerieRepository,
    private val relacionamentoUtil: ProfessorRelacionamentoUtil
) {
    fun buscarTodasSeries(): List<Serie> = repository.findAll().sortedBy { it.id };

    fun buscarSeriesPorProfessor(professorId: Int): List<Serie> =
        repository.findAllByProfessorId(professorId).sortedBy { it.id };

    fun buscarSeriePorId(id: Long): Serie = repository.findById(id).get();

    fun atualizarSerie(serie: Serie) {
        repository.save(serie);
    }

    fun adicionarSerie(serie: PostSerieRequest) {
        repository.save(serie.toSerieModel());
    }

    fun adicionarSerieComProfessor(serie: PostSerieProfessorRequest, profeId: Int) {
        relacionamentoUtil.atribuirSeriesComProfessor(profeId, serie.serie)
    }

    fun excluirSerie(id: Long) {
        repository.deleteById(id);
    }

}