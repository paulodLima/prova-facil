package com.provafacil.prova_facil.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "alternativas_errada", schema = "provas_db")
data class AlternativaErrada(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var texto: String,

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pergunta_id")
    val pergunta: Pergunta
)