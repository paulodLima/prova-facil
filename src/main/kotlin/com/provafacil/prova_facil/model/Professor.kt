package com.provafacil.prova_facil.model

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

    val senha: String,

    val disciplina: String,

    @ElementCollection(targetClass = Roles::class, fetch = FetchType.EAGER)
    @CollectionTable(
        name = "professor_roles",
        joinColumns = [JoinColumn(name = "professor_id")]
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    val roles: Set<Roles> = setOf()

)