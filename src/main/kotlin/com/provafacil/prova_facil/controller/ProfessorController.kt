package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.model.enums.Disciplina
import com.provafacil.prova_facil.model.request.PostProfessorRequest
import com.provafacil.prova_facil.model.request.PutProfessorRequest
import com.provafacil.prova_facil.model.response.DisciplinaResponse
import com.provafacil.prova_facil.model.response.ProfessorResponse
import com.provafacil.prova_facil.service.DisciplinaService
import com.provafacil.prova_facil.service.ProfessorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/professor", produces = [MediaType.APPLICATION_JSON_VALUE])
class ProfessorController(val service: ProfessorService, val disciplinaService: DisciplinaService) {

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun buscarProfessorPorEmail(@PathVariable email: String): ProfessorResponse? {
        val response = service.listarProfessorByEmail(email);
        return ProfessorResponse(nome = response!!.nome, email = response.email, roles = response.roles, disciplina = response.disciplinaDesc);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listarTodosProfessores(): List<Professor> =
        service.listarTodos();


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun atualizarProfessor(@PathVariable id: Int,@RequestBody put: PutProfessorRequest) {
        val professor = service.buscarPorId(id);
        val disciplina = disciplinaService.buscarDisciplinaPorId(put.disciplinaId.id);
        service.atualizar(put.toProfessorModel(professor,disciplina))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletarProfessor(@PathVariable id: Int) {
        service.deletarProfessor(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun adicionarProfessor(@RequestBody @Valid professor: PostProfessorRequest) {
        val disciplina = disciplinaService.buscarDisciplinaPorId(professor.disciplinaId);
        service.adicionarProfessor(professor.toProfessorModel(disciplina));
    }

    @GetMapping("/materias")
    fun listarMaterias(): List<DisciplinaResponse> {
        return Disciplina.entries.map {
            DisciplinaResponse(
                codigo = it.name,
                descricao = it.descricao
            )
        }
    }
}