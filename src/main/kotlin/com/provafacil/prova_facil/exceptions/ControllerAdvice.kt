package com.provafacil.prova_facil.exceptions

import com.provafacil.prova_facil.model.response.ErrorResponse
import com.provafacil.prova_facil.model.response.FieldErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            e.message ?: e.localizedMessage,
            e.errorCode,
            null
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(e: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            e.message ?: e.localizedMessage,
            e.errorCode,
            null
        )
        println("passando aqui"
                + e.message)
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidExceptionException(e: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "Requisição invalida",
            "422",
            e.bindingResult.fieldErrors.map { FieldErrorResponse(it.defaultMessage ?: "invalid", it.field) }
        )
        println("passando aqui"
                + e.message)
        return ResponseEntity(error, HttpStatus.UNPROCESSABLE_ENTITY)
    }

}