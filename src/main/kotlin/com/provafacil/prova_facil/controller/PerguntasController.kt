package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.service.PerguntaService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/perguntas", produces = [MediaType.APPLICATION_JSON_VALUE])
class PerguntasController(val service: PerguntaService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun buscarProfessorPorEmail(@PageableDefault(page = 0, size = 1) pegeable: Pageable): Page<Pergunta>? {
        return service.litarTodasPerguntas(pegeable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun buscarProfessorPorEmail(@PathVariable id: Int): Pergunta? {
        return service.buscarPorId(id);
    }
}