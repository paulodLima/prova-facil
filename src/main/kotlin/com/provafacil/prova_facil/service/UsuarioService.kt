package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.exceptions.NotFoundException
import com.provafacil.prova_facil.model.Usuario
import com.provafacil.prova_facil.model.enums.Roles
import com.provafacil.prova_facil.model.request.PostProfessorResponse
import com.provafacil.prova_facil.repository.UsuarioRepository
import com.provafacil.prova_facil.util.ProfessorRelacionamentoUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(
    private val repository: UsuarioRepository,
    private val disciplinaService: DisciplinaService,
    private val serieService: SerieService,
    private val passwordEncoder: PasswordEncoder,
    private val emailService: EmailService,
    private val escolaService: EscolaService,
    private val relacionamentoUtil: ProfessorRelacionamentoUtil
) {
    fun listarProfessorByEmail(email: String): Usuario? =
        repository.findByEmailEqualsIgnoreCase(email).orElseThrow { NotFoundException("O email [$email] não existe", "0001") }

    fun listarTodos(): List<Usuario> = repository.findAll().sortedBy { it.nome }

    fun atualizar(usuario: Usuario) {
        repository.save(usuario)
    }

    fun buscarPorId(id: Int): Usuario =
        repository.findById(id).orElseThrow { NotFoundException("O professor de [$id] não existe", "0001") }

    fun deletarProfessor(id: Int) {
        repository.deleteById(id);
    }

    fun emailAvailable(email: String): Boolean {
        return !repository.existsByEmail(email);
    }

    fun findById(id: Int): Optional<Usuario> {
        return repository.findById(id);
    }

    fun adicionarUsuario(professor: PostProfessorResponse) {
        val professorComRoleUser = professor.toProfessorModel().copy(
            roles = mutableSetOf(Roles.USER)
        )
        val escola = escolaService.buscarEscolaPorId(professor.escola.toInt())
        professorComRoleUser.escola = escola
        val professorSalvo = repository.save(professorComRoleUser)

        relacionamentoUtil.atribuirDisciplinas(professorSalvo.id, professor.disciplina)
        relacionamentoUtil.atribuirSeries(professorSalvo.id, professor.serie)

        emailService.enviarMensagemParaCriarSenha(
            professorSalvo.id,
            professor.email,
            professor.nome,
            false
        )
    }

    fun recuperarSenha (email: String) {
        val professor = repository.findByEmailIgnoreCase(email)
            ?: throw NotFoundException("E-mail não encontrado","404")


        emailService.enviarMensagemParaCriarSenha(professor.id, professor.email, professor.nome,true)
    }

}