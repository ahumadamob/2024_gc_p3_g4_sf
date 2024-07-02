package com.progra3.proyecto.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class ResponseUtil {
	
	// constructor privado para evitar instanciacion
	private ResponseUtil() {}
	
	
	// metodos
	public static <T> ResponseEntity<APIResponse<T>> success (T data) {
		APIResponse<T> response = new APIResponse<T>(HttpStatus.OK.value(), null, data);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	public static <T> ResponseEntity<APIResponse<T>> notFound (String message) {
		APIResponse<T> response = new APIResponse<T>(HttpStatus.NOT_FOUND.value(), message(message), null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	
	public static <T> ResponseEntity<APIResponse<T>> badRequest (String message) {
		APIResponse<T> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), addSingleMessage(message), null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	
	public static <T> ResponseEntity<APIResponse<T>> successDeleted (String message, Integer id) {
		APIResponse<T> response = new APIResponse<T>(HttpStatus.OK.value(), message(message.replace("{0}", id.toString())), null);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	private static List<String> message (String message) {
		List<String> messages = new ArrayList<>();
		messages.add(message);
		return messages;
	}
	
	
	private static List<String> addSingleMessage (String message) {
		List<String> messages = new ArrayList<>();
		messages.add(message);
		return messages;
	}
	
}
