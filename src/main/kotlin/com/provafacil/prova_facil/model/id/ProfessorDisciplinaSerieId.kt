package com.provafacil.prova_facil.model.id

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class ProfessorDisciplinaSerieId(
    var professorId: Int? = 0,
    var disciplinaId: Long = 0,
    var serieId: Long = 0
) : Serializable