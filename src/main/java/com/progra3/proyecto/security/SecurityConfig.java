package com.progra3.proyecto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	// clase q configura la SEGURIDAD de la aplicacion

	// FILTRO de AUTENTICACION JWT
	private final JwtAuthenticationFilter jwtAuthenticactionFilter;
	
	// constructor
	public SecurityConfig (JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticactionFilter = jwtAuthenticationFilter;
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		// cadena de FILTROS de la aplicacion
		
		// opciones de seguridad
		http.csrf(csrf -> csrf.disable())          // desactiva la protección CSRF (Cross-Site Request Forgery)
		                                           // dado q usamos cabecera Authorization
			.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/v1/project/auth/**").permitAll() // acceso libre
														 .anyRequest().authenticated())                     // requiere autenticacion
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))   // SIN estado
			.addFilterBefore(jwtAuthenticactionFilter, UsernamePasswordAuthenticationFilter.class);         // filtro JWT antes de procesar las credenciales de usuario
		
		return http.build();   // CADENA de FILTROS configurada (construida)
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration authConfig) throws Exception {
		// AUTENTICACION de los USUARIOS
		return authConfig.getAuthenticationManager();   // configuracion necesaria para la autenticacion
	}
	
	
	// definicion de USUARIOS en memoria
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails admin = User.withUsername("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("ADMIN")
				.build();
		
		UserDetails user = User.withUsername("user")
				.password(passwordEncoder().encode("123456"))
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(admin, user);
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();       // encriptador de contraseña
	}
	
}
