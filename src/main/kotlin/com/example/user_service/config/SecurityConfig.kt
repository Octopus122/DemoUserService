package com.example.user_service.config

//import org.springframework.boot.webservices.client.WebServiceMessageSenderFactory.http
import org.springframework.security.config.web.server.ServerHttpSecurity.http

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun userDetailsService(): UserDetailsService{
        val commonUser = User.builder()
            .username("common")
            .password("{noop}common_password")
            .roles("COMMON")
            .build()
        val admin = User.builder()
            .username("admin")
            .password("{noop}administrator_)")
            .roles("COMMON", "ADMIN")
            .build()
        return InMemoryUserDetailsManager(commonUser, admin)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf{it.disable()}
            .authorizeHttpRequests {
                it.requestMatchers("/welcome/authorized").authenticated()
                it.requestMatchers("/welcome/unauthorized").not().authenticated()
                it.requestMatchers("/welcome/admin").hasRole("ADMIN")
                it.requestMatchers("/admin/**").hasRole("ADMIN")
                it.anyRequest().permitAll()
            }
            .httpBasic{}
        return http.build()
    }
}