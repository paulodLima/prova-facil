package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.Questao
import com.provafacil.prova_facil.service.ProvaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/prova")
class ProvaController(val provaService: ProvaService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findQuestao(): Questao {
        return provaService.findQuestao()
    }
}