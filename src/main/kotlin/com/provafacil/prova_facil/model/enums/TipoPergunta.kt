package com.provafacil.prova_facil.model.enums

enum class TipoPergunta(val descricao: String, val codigo: Long) {
    DISSERTATIVA("Dissertativa", 2),
    MULTIPLAESCOLHA("Múltipla Escolha", 1);

    companion object {
        fun fromCodigo(codigo: Long): TipoPergunta {
            return entries.firstOrNull { it.codigo == codigo }
                ?: throw IllegalArgumentException("TipoPergunta inválido: $codigo")
        }
    }
}