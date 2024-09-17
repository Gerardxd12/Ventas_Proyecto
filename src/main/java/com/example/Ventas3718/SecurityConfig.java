/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Ventas3718;

import com.example.Ventas3718.Clases.Usuario;
import com.example.Ventas3718.Componentes.AccesoDenegado;
import com.example.Ventas3718.Servicios.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioService();
    }

    /*
@Bean
public UserDetailsService userDetailsService(PasswordEncoder
encoder)
{
UserDetails admin = User.withUsername("admin")
.password(encoder.encode("123"))
.roles("ADMIN","USER","HR")
.build();
UserDetails pedro = User.withUsername("pedro")
.password(encoder.encode("123"))
.roles("ADMIN")
.build();
UserDetails juan = User.withUsername("juan")
.password(encoder.encode("123"))
.roles("USER")
.build();
return new InMemoryUserDetailsManager(admin,pedro,juan);
}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .and().formLogin().loginProcessingUrl("/login")
                .and().formLogin().defaultSuccessUrl("/cliente/").permitAll()
                .and().logout().logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        return http.build();
    }

    public AccessDeniedHandler accessDeniedHandler() {
        return new AccesoDenegado();
    }

    /*
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http)
throws Exception
{
return http
.csrf().disable()
.authorizeHttpRequests()
.requestMatchers("/cliente/").permitAll()
.and()
.authorizeHttpRequests()
.requestMatchers("/producto/**").authenticated()
.and().formLogin()
.and().build();
}
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
//PasswordEncoder encoder = null;
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
