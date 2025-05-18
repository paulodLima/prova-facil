package com.provafacil.prova_facil.security

import com.provafacil.prova_facil.repository.ProfessorRepository
import com.provafacil.prova_facil.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SecurityConfig(
    @Lazy private val userDetails: UserDetailsCustomService,
    private val professorRepository: ProfessorRepository,
    private val jwtUtil: JwtUtil
) {
    private val PUBLIC_MATHCHERS = arrayOf<String>(
    )

    private val PUBLIC_POST_MATHCHERS = arrayOf(
        "/api/professor",
        "/login"
    )

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .exposedHeaders("Authorization")
                    .allowCredentials(true)
            }
        }
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity, authConfig: AuthenticationConfiguration): SecurityFilterChain {
        val authManager = authConfig.authenticationManager

        val authFilter = AuthenticationFilter(authManager, professorRepository,jwtUtil)

        http
            .csrf { it.disable() }
            .cors { }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers(*PUBLIC_MATHCHERS).permitAll()
                    .requestMatchers(*PUBLIC_POST_MATHCHERS).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilter(AuthorizationFilter(authManager,jwtUtil,userDetails))
            .httpBasic {}

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetails)
        provider.setPasswordEncoder(passwordEncoder())
        return provider
    }
}