package com.provafacil.prova_facil.exceptions

class NotFoundException(override val message: String, val errorCode: String): Exception() {
}