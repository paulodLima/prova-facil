package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.Escola
import com.provafacil.prova_facil.model.Serie
import com.provafacil.prova_facil.model.request.*
import com.provafacil.prova_facil.model.response.SerieResponse
import com.provafacil.prova_facil.service.EscolaService
import com.provafacil.prova_facil.service.SerieService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.security.Principal

@RestController
@RequestMapping("/api/escola")
class EscolaController (
    private val service: EscolaService
){
    @GetMapping
    fun buscarTodasEscolas():List<Escola>{
        return service.buscarTodasEscolas()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun criarPergunta(
        @RequestPart("request") request: PostEscolaRequest,
        @RequestPart("arquivos", required = false) arquivos: List<MultipartFile>?,
        principal: Principal
    ) {
        service.criarEscola(request, arquivos)
    }
}