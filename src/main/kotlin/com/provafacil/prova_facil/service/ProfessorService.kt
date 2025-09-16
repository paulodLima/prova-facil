package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.exceptions.NotFoundException
import com.provafacil.prova_facil.model.Professor
import com.provafacil.prova_facil.model.enums.Roles
import com.provafacil.prova_facil.model.request.PostProfessorRequest
import com.provafacil.prova_facil.repository.ProfessorRepository
import com.provafacil.prova_facil.util.ProfessorRelacionamentoUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProfessorService(
    private val repository: ProfessorRepository,
    private val disciplinaService: DisciplinaService,
    private val serieService: SerieService,
    private val passwordEncoder: PasswordEncoder,
    private val emailService: EmailService,
    private val relacionamentoUtil: ProfessorRelacionamentoUtil
) {
    fun listarProfessorByEmail(email: String): Professor? =
        repository.findByEmailEqualsIgnoreCase(email).orElseThrow { NotFoundException("O email [$email] não existe", "0001") }

    fun listarTodos(): List<Professor> = repository.findAll().sortedBy { it.nome }

    fun atualizar(professor: Professor) {
        repository.save(professor)
    }

    fun buscarPorId(id: Int): Professor =
        repository.findById(id).orElseThrow { NotFoundException("O professor de [$id] não existe", "0001") }

    fun deletarProfessor(id: Int) {
        repository.deleteById(id);
    }

    fun emailAvailable(email: String): Boolean {
        return !repository.existsByEmail(email);
    }

    fun findById(id: Int): Optional<Professor> {
        return repository.findById(id);
    }

    fun adicionarProfessor(professor: PostProfessorRequest) {
        val professorComRoleUser = professor.toProfessorModel().copy(
            roles = mutableSetOf(Roles.USER)
        )

        val professorSalvo = repository.save(professorComRoleUser)

        relacionamentoUtil.atribuirDisciplinas(professorSalvo.id, professor.disciplina)
        relacionamentoUtil.atribuirSeries(professorSalvo.id, professor.serie)

        emailService.enviarMennsagemParaCriarSenha(
            professorSalvo.id,
            professor.email,
            professor.nome
        )
    }

}