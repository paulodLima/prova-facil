package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.model.Disciplina
import com.provafacil.prova_facil.repository.DisciplinaRepository
import org.springframework.stereotype.Service

@Service
class DisciplinaService(
    private val repository: DisciplinaRepository
) {
    fun buscarTodasDisciplinas(): List<Disciplina> = repository.findAll().sortedBy { it.id };

    fun buscarTodasDisciplinasPorProfessor(profId: Int): List<Disciplina> = repository.buscarTodasDisciplinasPorProfessor(profId).sortedBy { it.id };

    fun buscarDisciplinaPorId(id: Long): Disciplina = repository.findById(id).get();

    fun atualizarDisciplina(disciplina: Disciplina) {
        repository.save(disciplina);
    }
    fun adicionarDisciplina(disciplina: Disciplina) {
        repository.save(disciplina);
    }

    fun excluirDisciplina(id: Long) {
        repository.deleteById(id);
    }

}