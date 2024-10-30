package com.progra3.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progra3.proyecto.dto.JwtResponse;
import com.progra3.proyecto.dto.LoginRequest;
import com.progra3.proyecto.security.JwtUtils;

@RestController
@RequestMapping("/api/v1/project/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;   // AUTENTICACION de USUARIOS
	
	@Autowired
	private JwtUtils jwtUtils;                             // GENERACION y VALIDACION de TOKENS
	
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser (@RequestBody LoginRequest loginRequest) {
		
		// objeto con las CREDENCIALES de USUARIO (username, password)
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		// se establece el objeto 'authentication' en el SecurityContextHolder => USUARIO AUTENTICADO
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// generacion del TOKEN que representa la SESION del USUARIO AUTENTICADO
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		return ResponseEntity.ok(new JwtResponse(jwt));   // objeto con el JWT a usar en las peticiones a realizar
		
	}
	
	 
}
