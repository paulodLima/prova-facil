package com.provafacil.prova_facil.controller

import com.provafacil.prova_facil.model.request.PostProfessorResponse
import com.provafacil.prova_facil.model.request.PutProfessorRequest
import com.provafacil.prova_facil.model.request.ResetSenhaRequest
import com.provafacil.prova_facil.model.response.ProfessorResponse
import com.provafacil.prova_facil.security.JwtUtil
import com.provafacil.prova_facil.service.DisciplinaService
import com.provafacil.prova_facil.service.UsuarioService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/professor", produces = [MediaType.APPLICATION_JSON_VALUE])
class UsuarioController(val service: UsuarioService, val disciplinaService: DisciplinaService,
                        private val passwordEncoder: PasswordEncoder, private val jwtService: JwtUtil) {

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun buscarProfessorPorEmail(@PathVariable email: String): ProfessorResponse? {
        val response = service.listarProfessorByEmail(email);
        return ProfessorResponse(nome = response!!.nome, email = response.email, roles = response.roles);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listarTodosProfessores(): List<ProfessorResponse> =
        service.listarTodos().map { professor ->
            ProfessorResponse(
                nome = professor.nome,
                email = professor.email,
                roles = professor.roles,
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
    fun adicionarProfessor(@RequestBody @Valid professor: PostProfessorResponse) {
        service.adicionarUsuario(professor);
    }

    @PostMapping("/reset/{token}")
    @ResponseStatus(HttpStatus.OK)
    fun resetarSenha(
        @PathVariable token: String,
        @RequestBody @Valid reset: ResetSenhaRequest
    ) {
        val userId = jwtService.getSubject(token).toInt()
        val professor = service.findById(userId).orElseThrow {
            IllegalArgumentException("Professor não encontrado")
        }
        professor.senha = passwordEncoder.encode(reset.senha)
        service.atualizar(professor)
    }

    @GetMapping("/reset/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun recuperSenha(
       @PathVariable email: String,
    ) {
        service.recuperarSenha(email)
    }
}