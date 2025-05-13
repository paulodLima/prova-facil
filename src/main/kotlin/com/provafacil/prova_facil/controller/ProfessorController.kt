package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.model.request.PostProfessorRequest
import com.provafacil.prova_facil.model.request.PutProfessorRequest
import com.provafacil.prova_facil.model.response.ProfessorResponse
import com.provafacil.prova_facil.service.ProfessorService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/professor", produces = [MediaType.APPLICATION_JSON_VALUE])
class ProfessorController(val service: ProfessorService) {

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    fun buscarProfessorPorEmail(@RequestParam email: String): Professor? {
        return service.listarProfessorByEmail(email);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listarTodosProfessores(): List<ProfessorResponse> =
        service.listarTodos().map { professor ->
            ProfessorResponse(
                nome = professor.nome,
                email = professor.email,
            )
        }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun atualizarProfessor(@PathVariable id: Int,@RequestBody put: PutProfessorRequest) {
        val professor = service.buscarPorId(id);
        service.atualizar(put.toProfessorModel(professor))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletarProfessor(@PathVariable id: Int) {
        service.deletarProfessor(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun adicionarProfessor(@RequestBody professor: PostProfessorRequest) {
        service.adicionarProfessor(professor.toProfessorModel());
    }
}