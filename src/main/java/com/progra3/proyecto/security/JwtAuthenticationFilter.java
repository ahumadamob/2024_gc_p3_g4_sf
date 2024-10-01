package com.progra3.proyecto.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request,     // solicitud del cliente
									HttpServletResponse response,   // respuesta enviada al cliente
									FilterChain filterChain)        // cadena de filtos
			throws ServletException, IOException {
		
		try {
			String jwt = parseJwt(request);   // TOKEN recibido en la solicitud HTTP
			
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				
				// NOMBRE de USUARIO asociado al TOKEN
				String username = jwtUtils.getUserNameFromJstToken(jwt);
				
				// objeto autenticado con los DETALLES de USUARIO (nombre, ..., roles)
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
				
				// establezco detalles de autenticacion
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				// establezco el objeto autenticado dentro del contexto de seguridad => USUARIO AUTENTICADO
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
			
		} catch (Exception e) {
			System.out.println("CANNOT set user authentication: " + e);
		}
		
	}

	
	// extraer TOKEN de la solicitud HTTP
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");         // encabezado de la solicitud
		if (headerAuth != null && headerAuth.startsWith("Bearer ")) {   // Bearer: formato estandard p/ enviar tokens
			return headerAuth.substring(7);                             // TOKEN extraido del encabezado
		}
		return null;
	}
	
	

}
