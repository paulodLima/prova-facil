package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Escola
import com.provafacil.prova_facil.model.request.PostEscolaRequest
import com.provafacil.prova_facil.repository.EscolaRepository
import com.provafacil.prova_facil.util.ProfessorRelacionamentoUtil
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class EscolaService(
    private val repository: EscolaRepository,
    private val relacionamentoUtil: ProfessorRelacionamentoUtil
) {
    fun buscarTodasEscolas(): List<Escola> = repository.findAll().sortedBy { it.id };

    fun buscarEscolaPorId(id: Int): Escola = repository.findById(id).get();

    fun atualizarEscola(escola: Escola) {
        repository.save(escola);
    }

    fun criarEscola(request: PostEscolaRequest, imagens: List<MultipartFile>?) {
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

        repository.save(escola)
    }
}