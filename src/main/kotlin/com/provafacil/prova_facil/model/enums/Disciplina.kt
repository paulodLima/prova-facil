package com.provafacil.prova_facil.model.enums

enum class Disciplina(val descricao: String) {
    LINGUA_PORTUGUESA("Língua Portuguesa"),
    MATEMATICA("Matemática"),
    CIENCIAS("Ciências"),
    FISICA("Física"),
    QUIMICA("Química"),
    BIOLOGIA("Biologia"),
    HISTORIA("História"),
    GEOGRAFIA("Geografia"),
    FILOSOFIA("Filosofia"),
    SOCIOLOGIA("Sociologia"),
    INGLES("Inglês"),
    ESPANHOL("Espanhol"),
    EDUCACAO_FISICA("Educação Física"),
    ARTES("Artes"),
    TECNOLOGIA("Tecnologia"),
    ENSINO_RELIGIOSO("Ensino Religioso");

    override fun toString(): String {
        return descricao
    }
}