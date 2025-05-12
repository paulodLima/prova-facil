package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.repository.SerieRepository
import org.springframework.stereotype.Service

@Service
class SerieService(
    private val repository: SerieRepository
) {
    fun buscarTodasSeries(): List<Serie> = repository.findAll();

    fun buscarSeriePorId(id: Int): Serie = repository.findById(id).get();

    fun atualizarSerie(servie: Serie) = repository.save(servie);
}