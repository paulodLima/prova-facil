package com.provafacil.prova_facil.security

import com.provafacil.prova_facil.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(val usuarioModel: Usuario): UserDetails {
    val id = usuarioModel.id
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = usuarioModel.roles.map { SimpleGrantedAuthority(it.descricao) }.toMutableList();
    override fun getPassword(): String = usuarioModel.senha;
    override fun getUsername(): String = usuarioModel.id.toString();
}