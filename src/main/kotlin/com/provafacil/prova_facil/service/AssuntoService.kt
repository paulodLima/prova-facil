package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.repository.AssuntoRepository
import com.provafacil.prova_facil.repository.SerieRepository
import org.springframework.stereotype.Service

@Service
class AssuntoService(
    private val repository: AssuntoRepository
) {
    fun buscarTodosAssuntos(): List<Assunto> = repository.findAll().sortedBy { it.id };

    fun buscarAssuntoPorId(id: Long): Assunto = repository.findById(id).get();

    fun atualizarAssunto(assunto: Assunto) {
        repository.save(assunto);
    }
    fun adicionarAssunto(assunto: Assunto) {
        repository.save(assunto);
    }

    fun excluirAssunto(id: Long) {
        repository.deleteById(id);
    }

}