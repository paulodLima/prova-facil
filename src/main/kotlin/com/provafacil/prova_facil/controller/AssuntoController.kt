package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.model.request.PostAssuntoRequest
import com.provafacil.prova_facil.model.request.PostSerieRequest
import com.provafacil.prova_facil.model.request.PutAssuntoRequest
import com.provafacil.prova_facil.model.request.PutSerieRequest
import com.provafacil.prova_facil.model.response.AssuntoResponse
import com.provafacil.prova_facil.model.response.SerieResponse
import com.provafacil.prova_facil.repository.AssuntoRepository
import com.provafacil.prova_facil.service.AssuntoService
import com.provafacil.prova_facil.service.DisciplinaService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/assunto")
class AssuntoController (
    private val service: AssuntoService,
    private val disciplinaService: DisciplinaService
){
    @GetMapping
    fun buscarTodasSerie(principal: Principal):List<Assunto>{
        val userId = principal.name.toLong();
        return service.buscarTodosAssuntos(userId);
    }

    @GetMapping("/{id}")
    fun buscarSerie(@PathVariable id: Long): Assunto {
        return service.buscarAssuntoPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun atualizarSerie(@PathVariable id: Long, @RequestBody put : PutAssuntoRequest){
        val serie = service.buscarAssuntoPorId(id);
        val disciplina = disciplinaService.buscarDisciplinaPorId(put.disciplinaId);
        service.atualizarAssunto(put.toAssuntoModel(serie, disciplina));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun excluirSerie(@PathVariable id: Long){
        service.excluirAssunto(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun criarSerie(@RequestBody post : PostAssuntoRequest){
        val disciplina = disciplinaService.buscarDisciplinaPorId(post.disciplina);
        service.adicionarAssunto(post.toAssuntoModel(disciplina));
    }

    @PostMapping("/professor")
    @ResponseStatus(HttpStatus.CREATED)
    fun criarSerie(@RequestBody post : PostAssuntoRequest, principal: Principal){
        service.adicionarAssuntoComProfessor(post,principal.name.toInt());
    }
}