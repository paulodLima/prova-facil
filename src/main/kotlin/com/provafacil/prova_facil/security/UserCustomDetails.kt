package com.provafacil.prova_facil.security

import com.provafacil.prova_facil.model.Professor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(val professorModel: Professor): UserDetails {
    val id = professorModel.id
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = professorModel.roles.map { SimpleGrantedAuthority(it.descricao) }.toMutableList();
    override fun getPassword(): String = professorModel.senha;
    override fun getUsername(): String = professorModel.id.toString();
}