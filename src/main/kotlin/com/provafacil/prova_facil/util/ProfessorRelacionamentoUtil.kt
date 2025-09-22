package com.provafacil.prova_facil.util

import com.provafacil.prova_facil.model.Assunto
import com.provafacil.prova_facil.model.Disciplina
import com.provafacil.prova_facil.repository.DisciplinaRepository
import com.provafacil.prova_facil.repository.UsuarioRepository
import com.provafacil.prova_facil.repository.SerieRepository
import org.springframework.stereotype.Component

@Component
class ProfessorRelacionamentoUtil(
    private val usuarioRepository: UsuarioRepository,
    private val disciplinaRepository: DisciplinaRepository,
    private val serieRepository: SerieRepository
) {

    fun atribuirDisciplinas(professorId: Int, disciplinaIds: List<Long>) {
        val professor = usuarioRepository.findById(professorId).orElseThrow()
        disciplinaIds.forEach { id ->
            val disciplina = disciplinaRepository.findById(id).orElseThrow()
            professor.disciplinas.add(disciplina)
        }
        usuarioRepository.save(professor)
    }

    fun atribuirSeries(professorId: Int, serieIds: List<Long>) {
        val professor = usuarioRepository.findById(professorId).orElseThrow()
        serieIds.forEach { id ->
            val serie = serieRepository.findById(id).orElseThrow()
            professor.series.add(serie)
        }
        usuarioRepository.save(professor)
    }

    fun atribuirSeriesComProfessor(professorId: Int, serieIds: List<Int>) {
        val professor = usuarioRepository.findById(professorId).orElseThrow()

        val seriesSelecionadas = serieIds.map { id ->
            serieRepository.findById(id.toLong()).orElseThrow()
        }.toSet()

        professor.series.clear()
        professor.series.addAll(seriesSelecionadas)

        usuarioRepository.save(professor)
    }
    fun atribuirAssuntoComProfessor(professorId: Int, serie: Assunto, disciplina: Disciplina) {
        val professor = usuarioRepository.findById(professorId).orElseThrow()
        professor.disciplinas.add(disciplina)
        usuarioRepository.save(professor)
    }
}