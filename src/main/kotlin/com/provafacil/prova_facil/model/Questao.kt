package com.provafacil.prova_facil.model

import com.provafacil.prova_facil.model.enums.NivelDificuldade
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "questao", schema = "provas_db")
data class Questao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val enunciado: String,

    val respostaCorreta: String,

    @Enumerated(EnumType.STRING)
    val nivel: NivelDificuldade,

    val serie: String,
)
