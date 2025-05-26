package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.request.PerguntasRequest
import com.provafacil.prova_facil.model.request.PostPerguntaRequest
import com.provafacil.prova_facil.model.response.PerguntasResponse
import com.provafacil.prova_facil.service.PerguntaService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("api/perguntas", produces = [MediaType.APPLICATION_JSON_VALUE])
class PerguntasController(val service: PerguntaService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun buscarTodasPerguntas(@PageableDefault(page = 0, size = 10,) pegeable: Pageable,principal: Principal): Page<PerguntasRequest>? {
        val userId = principal.name.toInt();
        return service.litarTodasPerguntasPorProfessor(pegeable,userId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun buscarPerguntasPorId(@PathVariable id: Long): PerguntasRequest {
        return service.buscarPorId(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun atualizarPergunta(@RequestBody post : PerguntasResponse){
        service.atualizarPergunta(post);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun excluirPergunta(@PathVariable id: Long){
        service.excluirPergunta(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun criarPergunta(@RequestBody request: PostPerguntaRequest,principal: Principal) {
        request.professor =  principal.name.toInt();
        service.criarPergunta(request);
    }
}