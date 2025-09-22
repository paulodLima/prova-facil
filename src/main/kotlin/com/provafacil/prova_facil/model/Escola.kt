package com.provafacil.prova_facil.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "escola", schema = "provas_db")
data class Escola(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(unique = true)
    var nome: String,

    @Column(unique = true)
    var email: String,

    var estado: String,

    @Column(name = "logoEscola", columnDefinition = "bytea")
    var logoEscola: ByteArray? = null,

    @Column(name = "logoSecretaria", columnDefinition = "bytea")
    var logoSecretaria: ByteArray? = null,

    @OneToMany(mappedBy = "escola", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val professores: MutableList<Usuario> = mutableListOf()
)