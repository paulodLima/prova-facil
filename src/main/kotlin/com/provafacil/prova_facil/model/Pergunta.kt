package com.provafacil.prova_facil.model

import com.provafacil.prova_facil.model.enums.TipoPergunta
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "pergunta", schema = "provas_db")
data class Pergunta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val enunciado: String,

    @Enumerated(EnumType.STRING)
    val tipo: TipoPergunta,

    val respostaCorreta: String? = null,

    @ManyToOne
    @JoinColumn(name = "serie_id")
    val serie: Serie,

    @ManyToOne
    @JoinColumn(name = "assunto_id")
    val assunto: Assunto,

    @ManyToOne
    @JoinColumn(name = "professor_id")
    val professor: Professor,

    @CreationTimestamp
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "pergunta", cascade = [CascadeType.ALL], orphanRemoval = true)
    val alternativasErradas: List<AlternativaErrada> = emptyList()
)

