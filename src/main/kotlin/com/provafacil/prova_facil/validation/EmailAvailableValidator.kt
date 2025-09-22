package com.provafacil.prova_facil.validation

import com.provafacil.prova_facil.service.UsuarioService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValidator(private var usuarioService: UsuarioService): ConstraintValidator<EmailAvailable, String> {
    override fun isValid(value: String?, p1: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()) return false

        return usuarioService.emailAvailable(value.toString());
    }

}
