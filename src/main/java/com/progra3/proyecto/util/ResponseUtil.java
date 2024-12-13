package com.progra3.proyecto.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.progra3.proyecto.entity.Pedido;
import com.progra3.proyecto.entity.Repartidor;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class ResponseUtil {
    
    // constructor privado para evitar instanciacion
    private ResponseUtil() {}
    
    // Métodos para manejar respuestas exitosas y de error
    public static <T> ResponseEntity<APIResponse<T>> success(T data) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.OK.value(), null, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    public static <T> ResponseEntity<APIResponse<T>> notFound(String message) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.NOT_FOUND.value(), message(message), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    public static <T> ResponseEntity<APIResponse<T>> badRequest(String message) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), addSingleMessage(message), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    public static <T> ResponseEntity<APIResponse<T>> successDeleted(String message) {
        APIResponse<T> response = new APIResponse<>(HttpStatus.OK.value(), message(message), null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    // Método para manejar ConstraintViolationException
    public static <T> ResponseEntity<APIResponse<T>> handleConstraintException(ConstraintViolationException ex) {
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            messages.add(violation.getMessage());
        }
        APIResponse<T> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), messages, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private static List<String> message(String message) {
        List<String> messages = new ArrayList<>();
        messages.add(message);
        return messages;
    }
    
    private static List<String> addSingleMessage(String message) {
        List<String> messages = new ArrayList<>();
        messages.add(message);
        return messages;
    }

	public static ResponseEntity<APIResponse<Repartidor>> success(Repartidor savedRepartidor, String string) {
		// TODO Auto-generated method stub
		return null;
	}


}
