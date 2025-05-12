package com.provafacil.prova_facil.model

import jakarta.persistence.*

@Entity
@Table(name = "assunto", schema = "provas_db")
data class Assunto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nome: String
)