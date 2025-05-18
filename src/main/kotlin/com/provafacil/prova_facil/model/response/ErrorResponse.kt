package com.provafacil.prova_facil.model.response

data class ErrorResponse (
    var httpCode: Int,
    var message: String,
    var internalCode: String,
    var erros: List<FieldErrorResponse>?
)