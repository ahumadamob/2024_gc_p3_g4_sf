package com.progra3.proyecto.dto;

public class JwtResponse {
	
	private String token;

	
	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	
	public String getToken() {
		return token;
	}
		

}
