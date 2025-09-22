package com.provafacil.prova_facil.model.request

import com.provafacil.prova_facil.model.enums.Roles
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class CustomUserDetails(
    val id: Int,
    val nome: String,
    val email: String,
    private val senha: String,
    private val roles: Set<Roles>
) : UserDetails {
    override fun getAuthorities() =
        roles.map { SimpleGrantedAuthority("ROLE_${it.name}") }

    override fun getPassword() = senha
    override fun getUsername() = email
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}