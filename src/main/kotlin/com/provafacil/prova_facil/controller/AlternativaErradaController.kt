package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.request.*
import com.provafacil.prova_facil.service.AlternativaErradaService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/alternativa-errada")
class AlternativaErradaController (
    private val service: AlternativaErradaService,
){

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun atualizarAlternaticaErrada(@PathVariable id: Int, @RequestBody put : PutAlternativaErradaRequest){
        val alternativa = service.buscarAlternaticaErradaPorId(id);
        alternativa.texto = put.texto;
        service.atualizarAlternaticaErrada(alternativa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun excluirAlternaticaErrada(@PathVariable id: Int){
        service.excluirAlternaticaErrada(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun criarAlternaticaErrada(@RequestBody post : PostAlternativaErradaRequest){
        service.adicionarAlternaticaErrada(post);
    }
}