package com.provafacil.prova_facil.exceptions

class BadRequestException(override val message: String, val errorCode: String): Exception() {
}