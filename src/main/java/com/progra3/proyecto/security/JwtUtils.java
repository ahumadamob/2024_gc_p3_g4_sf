package com.progra3.proyecto.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtils {
	
	// declaro y genero una CLAVE secreta usando una funcion hash con el algoritmo HS512
	private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	// TIEMPO de EXPIRACION del TOKEN en milisegundos
	private int jwtExpirationMs = 60000000;
	
	
	// metodo q genera un TOKEN para un USUARIO Autenticado
	public String generateJwtToken (Authentication authentication) {
		
		// objeto q representa al USUARIO (nombre, constraseña y rol) q ha iniciado sesion
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))                           // valor q identifica al usuario
				.setIssuedAt(new Date())                                             // fecha y hora en q se emite el token
				.setExpiration(new Date((new Date().getTime() + jwtExpirationMs)))   // fecha de expiracion del token
				.signWith(secretKey, SignatureAlgorithm.HS512)                       // firma q garantiza al token
				.compact();                                                          // token compactado en formato String
		
	}
	
	
	// obtener NOMBRE de USUARIO del JWT
	public String getUserNameFromJstToken (String token) {
		return Jwts.parser()                // permite extraer info del token
				.setSigningKey(secretKey)   // establece la clave q se uso para firmar el token
				.parseClaimsJws(token)      // devuelve un objeto con los reclamos 'claims' (x ej.: expiracion) del token
				.getBody().getSubject();    // obtengo el cuerpo del token y su asunto (nombre de usuario)
	}
	
	
	// validar JWT
	public boolean validateJwtToken (String authToken) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);   // analiza si el token es valido
			return true;
		} catch (JwtException e) {
			System.out.println("INVALID token: " + e.getMessage());   // token NO valido o caducado, firma incorrecta
		}
		return false;
	}
	
	
}
