package com.provafacil.prova_facil.model

import com.provafacil.prova_facil.model.enums.NivelDificuldade
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

    var enunciado: String,

    @Enumerated(EnumType.STRING)
    var tipo: TipoPergunta,

    var respostaCorreta: String? = null,

    @Column(name = "imagem", columnDefinition = "bytea")
    val imagem: ByteArray? = null,

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
    val usuario: Usuario,

    @CreationTimestamp
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "pergunta", cascade = [CascadeType.ALL], orphanRemoval = true)
    val alternativasErradas: MutableList<AlternativaErrada> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pergunta

        if (id != other.id) return false
        if (enunciado != other.enunciado) return false
        if (tipo != other.tipo) return false
        if (respostaCorreta != other.respostaCorreta) return false
        if (!imagem.contentEquals(other.imagem)) return false
        if (nivel != other.nivel) return false
        if (serie != other.serie) return false
        if (assunto != other.assunto) return false
        if (usuario != other.usuario) return false
        if (dataCriacao != other.dataCriacao) return false
        if (alternativasErradas != other.alternativasErradas) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + enunciado.hashCode()
        result = 31 * result + tipo.hashCode()
        result = 31 * result + (respostaCorreta?.hashCode() ?: 0)
        result = 31 * result + (imagem?.contentHashCode() ?: 0)
        result = 31 * result + nivel.hashCode()
        result = 31 * result + serie.hashCode()
        result = 31 * result + assunto.hashCode()
        result = 31 * result + usuario.hashCode()
        result = 31 * result + dataCriacao.hashCode()
        result = 31 * result + alternativasErradas.hashCode()
        return result
    }
}

