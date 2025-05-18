package com.provafacil.prova_facil.security

import com.provafacil.prova_facil.exceptions.AuthenticationException
import com.provafacil.prova_facil.service.UserDetailsCustomService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val userCustomDetails: UserDetailsCustomService
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)

            val subject = jwtUtil.getSubject(token)
            val userDetails = userCustomDetails.loadUserByUsername(subject)

            val authentication = UsernamePasswordAuthenticationToken(subject, null, userDetails.authorities)
            SecurityContextHolder.getContext().authentication = authentication

            if (jwtUtil.isTokenExpiringSoon(token)) {
                val newToken = jwtUtil.generateToken(subject.toInt())
                response.setHeader("Authorization", "Bearer $newToken")
            }
        }

        chain.doFilter(request, response)
    }
}