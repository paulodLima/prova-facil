package com.provafacil.prova_facil.model

import com.provafacil.prova_facil.model.enums.NivelDificuldade
import com.provafacil.prova_facil.model.enums.TipoPergunta
import com.provafacil.prova_facil.model.request.PostPerguntaRequest
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "pergunta", schema = "provas_db")
data class Pergunta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var enunciado: String,

    @Enumerated(EnumType.STRING)
    var tipo: TipoPergunta,

    var respostaCorreta: String? = null,

    @Enumerated(EnumType.STRING)
    var nivel: NivelDificuldade,

    @ManyToOne
    @JoinColumn(name = "serie_id")
    var serie: Serie,

    @ManyToOne
    @JoinColumn(name = "assunto_id")
    var assunto: Assunto,

    @ManyToOne
    @JoinColumn(name = "professor_id")
    val professor: Professor,

    @CreationTimestamp
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "pergunta", cascade = [CascadeType.ALL], orphanRemoval = true)
    val alternativasErradas: MutableList<AlternativaErrada> = mutableListOf()
)

