package com.provafacil.prova_facil.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.provafacil.prova_facil.model.enums.Roles
import jakarta.persistence.*

@Entity
@Table(name = "escola", schema = "provas_db")
data class Escola(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(unique = true)
    val nome: String,

    @Column(unique = true)
    val email: String,

    val estado: String,

    @Column(name = "logoEscola", columnDefinition = "bytea")
    var logoEscola: ByteArray? = null,

    @Column(name = "logoSecretaria", columnDefinition = "bytea")
    var logoSecretaria: ByteArray? = null,

    @OneToMany(mappedBy = "escola", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    val professores: MutableList<Professor> = mutableListOf()
)