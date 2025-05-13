package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.repository.SerieRepository
import org.springframework.stereotype.Service

@Service
class SerieService(
    private val repository: SerieRepository
) {
    fun buscarTodasSeries(): List<Serie> = repository.findAll().sortedBy { it.id };

    fun buscarSeriePorId(id: Int): Serie = repository.findById(id).get();

    fun atualizarSerie(serie: Serie) {
        repository.save(serie);
    }
    fun adicionarSerie(serie: Serie) {
        repository.save(serie);
    }

    fun excluirSerie(id: Int) {
        repository.deleteById(id);
    }

}