package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.service.ProfessorService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/professor", produces = [MediaType.APPLICATION_JSON_VALUE])
class ProfessorController(val service: ProfessorService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun buscarProfessorPorEmail(@RequestParam email: String): Professor? {
        return service.listarProfessorByEmail(email);
    }
}