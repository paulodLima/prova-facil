package com.provafacil.prova_facil.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.provafacil.prova_facil.model.enums.Roles
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

    var senha: String,

    @ElementCollection(targetClass = Roles::class, fetch = FetchType.EAGER)
    @CollectionTable(
        name = "professor_roles", schema = "provas_db",
        joinColumns = [JoinColumn(name = "professor_id")]
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    val roles: MutableSet<Roles> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
        name = "professor_disciplina", schema = "provas_db",
        joinColumns = [JoinColumn(name = "professor_id")],
        inverseJoinColumns = [JoinColumn(name = "disciplina_id")]
    )
    val disciplinas: MutableSet<Disciplina> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
        name = "professor_serie", schema = "provas_db",
        joinColumns = [JoinColumn(name = "professor_id")],
        inverseJoinColumns = [JoinColumn(name = "serie_id")]
    )
    @JsonIgnore
    val series: MutableSet<Serie> = mutableSetOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escola_id")
    @JsonBackReference
    val escola: Escola? = null
)