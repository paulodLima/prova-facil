package com.provafacil.prova_facil.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.provafacil.prova_facil.exceptions.AuthenticationException
import com.provafacil.prova_facil.model.request.CustomUserDetails
import com.provafacil.prova_facil.model.request.LoginRequest
import com.provafacil.prova_facil.repository.UsuarioRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val repository: UsuarioRepository,
    private val jwtUtil: JwtUtil
) : UsernamePasswordAuthenticationFilter() {
    init {
        this.authenticationManager = authenticationManager
    }
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val login = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
            val professor = repository.findByEmailEqualsIgnoreCase(login.email).orElseThrow { AuthenticationException("Usuário não encontrado", "98") }
            val userDetails = CustomUserDetails(
                id = professor.id,
                nome = professor.nome,
                email = professor.email,
                senha = professor.senha,
                roles = professor.roles
            )

            val authToken = UsernamePasswordAuthenticationToken(userDetails.id, login.password, userDetails.authorities)
            return authenticationManager.authenticate(authToken)

        } catch (ex: Exception) {
            throw AuthenticationException("Falha ao authentication","99")
        }
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: org.springframework.security.core.AuthenticationException?
    ) {
        response?.status = HttpServletResponse.SC_UNAUTHORIZED
        response?.contentType = "application/json;charset=UTF-8"

        val errorResponse = mapOf(
            "error" to "Falha de autenticação",
            "message" to (failed?.cause?.message ?: failed?.message ?: "Erro inesperado"),
            "code" to "99"
        )

        val mapper = jacksonObjectMapper()
        response?.writer?.write(mapper.writeValueAsString(errorResponse))
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user = authResult?.principal as UserCustomDetails
        val token = jwtUtil.generateToken(user.id)

        response?.addHeader("Authorization", "Bearer $token")

        val body = mapOf(
            "id" to user.id,
            "nome" to user.usuarioModel.nome,
            "email" to user.usuarioModel.email,
            "roles" to user.usuarioModel.roles.map { it.name },
            "token" to token
        )

        response?.contentType = "application/json"
        response?.writer?.write(jacksonObjectMapper().writeValueAsString(body))
        response?.writer?.flush()
    }
}