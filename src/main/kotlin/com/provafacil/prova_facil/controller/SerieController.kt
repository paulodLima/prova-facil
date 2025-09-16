package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.model.request.PostSerieProfessorRequest
import com.provafacil.prova_facil.model.request.PostSerieRequest
import com.provafacil.prova_facil.model.request.PutSerieRequest
import com.provafacil.prova_facil.model.response.SerieResponse
import com.provafacil.prova_facil.service.SerieService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/serie")
class SerieController (
    private val service: SerieService
){
    @GetMapping
    fun buscarTodasSerie():List<Serie>{
        return service.buscarTodasSeries()
    }

    @GetMapping("/professor")
    fun buscarSeriesPorProfessor(principal: Principal):List<SerieResponse>{
        val professorId = principal.name.toInt()
        val series = service.buscarSeriesPorProfessor(professorId)
        return series.map { serie ->
            SerieResponse(
                id = serie.id,
                nome = serie.nome
            )
        }
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
    fun criarSerie(@RequestBody post: PostSerieRequest) {
        service.adicionarSerie(post)
    }

    @PostMapping("/professor")
    @ResponseStatus(HttpStatus.CREATED)
    fun criarSerie(@RequestBody post: PostSerieProfessorRequest, principal: Principal) {
        val profId = principal.name.toInt()
        service.adicionarSerieComProfessor(post,profId)
    }
}