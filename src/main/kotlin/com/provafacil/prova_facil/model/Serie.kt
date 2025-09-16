package com.provafacil.prova_facil.model

import jakarta.persistence.*

@Entity
@Table(name = "serie", schema = "provas_db")
data class  Serie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nome: String,

    @ManyToMany(mappedBy = "series")
    val professores: MutableSet<Professor> = mutableSetOf()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Serie) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}