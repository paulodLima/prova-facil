package com.provafacil.prova_facil.service

import com.provafacil.prova_facil.exceptions.AuthenticationException
import com.provafacil.prova_facil.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val professorService: ProfessorService
) : UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val result =
            professorService.findById(id.toInt())
                .orElseThrow { AuthenticationException("Usuario não encontrado", "999") }
        return UserCustomDetails(result)
    }
}