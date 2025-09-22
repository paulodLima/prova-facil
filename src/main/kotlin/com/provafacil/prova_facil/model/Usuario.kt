package com.provafacil.prova_facil.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.provafacil.prova_facil.model.enums.Roles
import com.provafacil.prova_facil.model.enums.UsuarioTipo
import jakarta.persistence.*

@Entity
@Table(name = "usuario", schema = "provas_db")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    var nome: String,

    @Column(unique = true)
    var email: String,

    var senha: String,

    @ElementCollection(targetClass = Roles::class, fetch = FetchType.EAGER)
    @CollectionTable(
        name = "usuario_roles", schema = "provas_db",
        joinColumns = [JoinColumn(name = "usuario_id")]
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    val roles: MutableSet<Roles> = mutableSetOf(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipo: UsuarioTipo,

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
    var escola: Escola? = null
)