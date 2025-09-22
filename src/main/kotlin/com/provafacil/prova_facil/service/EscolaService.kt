package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Escola
import com.provafacil.prova_facil.model.Usuario
import com.provafacil.prova_facil.model.enums.Roles
import com.provafacil.prova_facil.model.enums.UsuarioTipo
import com.provafacil.prova_facil.model.request.PostEscolaRequest
import com.provafacil.prova_facil.repository.EscolaRepository
import com.provafacil.prova_facil.repository.UsuarioRepository
import com.provafacil.prova_facil.util.ProfessorRelacionamentoUtil
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class EscolaService(
    private val repository: EscolaRepository,
    private val relacionamentoUtil: ProfessorRelacionamentoUtil,
    private val usuarioRepository: UsuarioRepository,
    private val emailService: EmailService,
) {
    fun buscarTodasEscolas(): List<Escola> = repository.findAll().sortedBy { it.id };

    fun buscarEscolaPorId(id: Int): Escola {
        val usuario = usuarioRepository.findById(id)
        return repository.findById(usuario.get().escola?.id ?: 0).get();
    }

    fun atualizarEscola(id: Int, request: PostEscolaRequest, imagens: List<MultipartFile>?): Escola {
        val escola = repository.findById(id).orElseThrow {
            IllegalArgumentException("Escola com id $id não encontrada.")
        }

        if (request.nome.isNotBlank() && request.nome != escola.nome) {
            escola.nome = request.nome
        }

        if (request.email.isNotBlank() && request.email != escola.email) {
            escola.email = request.email
        }

        if (request.estado.isNotBlank() && request.estado != escola.estado) {
            escola.estado = request.estado
        }

        imagens?.forEach { imagem ->
            imagem.originalFilename?.let {
                val bytes = imagem.bytes

                if (it.contains("logoEscola", ignoreCase = true)) {
                    if (bytes.isNotEmpty() && !bytes.contentEquals(escola.logoEscola)) {
                        escola.logoEscola = bytes
                    }
                } else if (it.contains("logoSecretaria", ignoreCase = true)) {
                    if (bytes.isNotEmpty() && !bytes.contentEquals(escola.logoSecretaria)) {
                        escola.logoSecretaria = bytes
                    }
                }
            }
        }

        val response = repository.save(escola)

        val usuarioExistente = usuarioRepository.findByEscolaId(response.id)
        usuarioExistente?.apply {
            nome = response.nome
            email = response.email
        }?.let { usuarioRepository.save(it) }

        return response
    }

    fun criarEscola(request: PostEscolaRequest, imagens: List<MultipartFile>?): Escola {
        val escola = request.toEscolaModel()

        imagens?.forEach { imagem ->
            imagem.originalFilename?.let {
                if (it.contains("logoEscola", ignoreCase = true)) {
                    escola.logoEscola = imagem.bytes
                } else {
                    escola.logoSecretaria = imagem.bytes
                }
            }
        }
        var response = repository.save(escola)

        val usuarioEscola = Usuario(
            nome = response.nome,
            email = response.email,
            senha = "Temporario",
            roles = mutableSetOf(Roles.ESCOLA),
            tipo = UsuarioTipo.ESCOLA,
            escola = response
        )
        val usuario = usuarioRepository.save(usuarioEscola)
        emailService.enviarMensagemParaCriarSenha(
            usuario.id,
            usuario.email,
            usuario.nome,
            false
        )
        return response
    }

}