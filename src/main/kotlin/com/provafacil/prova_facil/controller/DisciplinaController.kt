package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.response.DisciplinaResponse
import com.provafacil.prova_facil.service.DisciplinaService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/disciplina")
class DisciplinaController (
    private val service: DisciplinaService
){
    @GetMapping
    fun buscarTodasSerie(): List<DisciplinaResponse> {
        return service.buscarTodasDisciplinas().map { disciplina -> DisciplinaResponse(disciplina) }
    }

    @GetMapping("/professor")
    fun buscarTodasSerie(principal: Principal):List<DisciplinaResponse>{
        val profId = principal.name.toInt()
        return service.buscarTodasDisciplinasPorProfessor(profId).map {disciplina -> DisciplinaResponse(disciplina) };
    }
}