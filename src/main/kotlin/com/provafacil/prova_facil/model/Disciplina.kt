package com.provafacil.prova_facil.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "disciplina", schema = "provas_db")
data class Disciplina(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false, length = 255)
    val nome: String,

    @OneToMany(mappedBy = "disciplina", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JsonManagedReference
    val assuntos: List<Assunto> = emptyList(),

    @ManyToMany(mappedBy = "disciplinas")
    val professores: MutableSet<Usuario> = mutableSetOf()

) : Serializable