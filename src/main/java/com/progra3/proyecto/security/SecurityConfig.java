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

	private final JwtAuthenticationFilter jwtAuthenticactionFilter;
	
	public SecurityConfig (JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticactionFilter = jwtAuthenticationFilter;
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())   // desactiva la protección CSRF (Cross-Site Request Forgery)
			.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/v1/project/auth/**").permitAll() // acceso libre
														 .anyRequest().authenticated())                     // requiere autenticacion
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))   // SIN estado
			.addFilterBefore(jwtAuthenticactionFilter, UsernamePasswordAuthenticationFilter.class);         // filtro JWT
		
		return http.build();   // CADENA de FILTROS configurada
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();   // configuracion necesaria para la autenticacion
	}
	
	
	// definicion de USUARIOS en memoria
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails admin = User.withUsername("admin")
				.password(passwordEncoder().encode("admin"))
				.build();
		
		UserDetails user = User.withUsername("user")
				.password(passwordEncoder().encode("123456"))
				.build();
		
		return new InMemoryUserDetailsManager(admin, user);
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();       // encriptador de contraseña
	}
	
}
