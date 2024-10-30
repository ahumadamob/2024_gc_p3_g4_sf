package com.progra3.proyecto.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	// FILTRO de AUTENTICACION de JWT
	// que se ejecuta una vez por cada solicitud HTTP antes de procesarla
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request,     // solicitud del cliente
									HttpServletResponse response,   // respuesta q se envia al cliente
									FilterChain filterChain)        // cadena de filtros
			throws ServletException, IOException {
		
		try {
			String jwt = parseJwt(request);                         // extraccion del TOKEN recibido en la solicitud HTTP
			
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {    // validacion del TOKEN
				
				// NOMBRE de USUARIO contenido en el TOKEN
				String username = jwtUtils.getUserNameFromJstToken(jwt);
				
				// OBJETO AUTENTICADO con los DETALLES de USUARIO (nombre, contraseña, roles)
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
				
				// establezco DETALLES de AUTENTICACION
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				// establezco el objeto autenticado dentro del contexto de seguridad => USUARIO AUTENTICADO
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
			
		} catch (Exception e) {
			System.out.println("CANNOT set user authentication: " + e);
		}
		
		
		// permite que la solicitud continúe a través de otros filtros y llegue al controlador correspondiente
		filterChain.doFilter(request, response);
		
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
