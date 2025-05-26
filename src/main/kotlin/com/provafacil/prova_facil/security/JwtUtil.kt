package com.provafacil.prova_facil.security

import com.provafacil.prova_facil.exceptions.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil {
    val key = Keys.secretKeyFor(SignatureAlgorithm.HS512)

    @Value("\${jwt.secret}")
    private val secret: String? = null

    @Value("\${jwt.expiration}")
    private val expiration: Long = 0

    private fun getSigningKey(): SecretKey = Keys.hmacShaKeyFor(secret!!.toByteArray())

    fun generateToken(id: Int): String {
        val expiry = Date(System.currentTimeMillis() + expiration)
        return Jwts.builder()
            .setSubject(id.toString())
            .setExpiration(expiry)
            .signWith(getSigningKey(), SignatureAlgorithm.HS512)
            .compact()
    }

    fun getClaims(token: String): Claims {
        return try {
            Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .body
        } catch (ex: Exception) {
            throw AuthenticationException("Token inválido: ${ex.message}", "999")
        }
    }

    fun isTokenExpiringSoon(token: String): Boolean {
        val expiration = getClaims(token).expiration.time
        val remaining = expiration - System.currentTimeMillis()
        return remaining < 60_000
    }

    fun getSubject(token: String): String = getClaims(token).subject
}