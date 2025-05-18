package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.Pergunta
import com.provafacil.prova_facil.model.response.PerguntasResponse
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
import java.security.Principal

@RestController
@RequestMapping("api/perguntas", produces = [MediaType.APPLICATION_JSON_VALUE])
class PerguntasController(val service: PerguntaService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun buscarTodasPerguntas(@PageableDefault(page = 0, size = 10,) pegeable: Pageable,principal: Principal): Page<PerguntasResponse>? {
        val userId = principal.name.toInt();
        return service.litarTodasPerguntasPorProfessor(pegeable,userId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun buscarPerguntasPorId(@PathVariable id: Int): Pergunta? {
        return service.buscarPorId(id);
    }
}