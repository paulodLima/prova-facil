package com.provafacil.prova_facil.model

import jakarta.persistence.*

@Entity
@Table(name = "professor", schema = "provas_db")
data class Professor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val nome: String,

    @Column(unique = true)
    val email: String,

    val senha: String
)