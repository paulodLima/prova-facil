package com.provafacil.prova_facil.exceptions

class AuthenticationException(override val message: String, val errorCode: String): Exception() {
}