package com.provafacil.prova_facil.validation

import com.provafacil.prova_facil.service.ProfessorService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValidator(private var professorService: ProfessorService): ConstraintValidator<EmailAvailable, String> {
    override fun isValid(value: String?, p1: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()) return false

        return professorService.emailAvailable(value.toString());
    }

}
