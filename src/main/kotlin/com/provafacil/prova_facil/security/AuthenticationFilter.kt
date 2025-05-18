package com.provafacil.prova_facil.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.provafacil.prova_facil.exceptions.AuthenticationException
import com.provafacil.prova_facil.model.request.LoginRequest
import com.provafacil.prova_facil.repository.ProfessorRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val repository: ProfessorRepository,
    private val jwtUtil: JwtUtil
) : UsernamePasswordAuthenticationFilter() {
    init {
        this.authenticationManager = authenticationManager
    }
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val login = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
            val id = repository.findByEmail(login.email).get().id

            val authToken = UsernamePasswordAuthenticationToken(id, login.password)
            return authenticationManager.authenticate(authToken);
        } catch (ex: Exception) {
            throw AuthenticationException("Falha ao authentication","99")
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val id = (authResult?.principal as UserCustomDetails).id
        val token = jwtUtil.generateToken(id);
        response?.addHeader("Authorization", "Bearer $token")
    }
}