package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.model.request.PutSerieRequest
import com.provafacil.prova_facil.service.SerieService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/serie")
class SerieController (
    private val service: SerieService
){
    @GetMapping
    fun buscarTodasSerie():List<Serie>{
        return service.buscarTodasSeries();
    }

    @GetMapping("/{id}")
    fun buscarSerie(@PathVariable id: Int):Serie{
        return service.buscarSeriePorId(id);
    }

    @PutMapping("/{id}")
    fun atualizarSerie(@PathVariable id: Int, @RequestBody request : PutSerieRequest):Serie{
        return service.buscarSeriePorId(id);
    }
}