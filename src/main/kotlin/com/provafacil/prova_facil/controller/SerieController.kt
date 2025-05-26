package com.provafacil.prova_facil.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.model.request.PostSerieRequest
import com.provafacil.prova_facil.model.request.PutSerieRequest
import com.provafacil.prova_facil.model.response.SerieResponse
import com.provafacil.prova_facil.service.SerieService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/serie")
class SerieController (
    private val service: SerieService
){
    @GetMapping
    fun buscarTodasSerie():List<Serie>{
        return service.buscarTodasSeries()
    }

    @GetMapping("/{id}")
    fun buscarSerie(@PathVariable id: Long):Serie{
        return service.buscarSeriePorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun atualizarSerie(@PathVariable id: Long, @RequestBody put : PutSerieRequest){
        val serie = service.buscarSeriePorId(id);
        service.atualizarSerie(put.toSerieModel(serie));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun excluirSerie(@PathVariable id: Long){
        service.excluirSerie(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun criarSerie(@RequestBody post : PostSerieRequest){
        service.adicionarSerie(post.toSerieModel());
    }
}