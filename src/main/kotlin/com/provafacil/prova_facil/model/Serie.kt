package com.provafacil.prova_facil.model

import jakarta.persistence.*

@Entity
@Table(name = "serie", schema = "provas_db")
data class  Serie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nome: String
)