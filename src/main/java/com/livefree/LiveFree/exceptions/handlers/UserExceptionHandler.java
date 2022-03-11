package com.livefree.LiveFree.exceptions.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.livefree.LiveFree.exceptions.ModelError;
import com.livefree.LiveFree.exceptions.models.EmailExsitException;
import com.livefree.LiveFree.exceptions.models.EmailNotExistException;
import com.livefree.LiveFree.exceptions.models.WrongPasswordException;

@ControllerAdvice
public class UserExceptionHandler {
	
	// Tentar Criar uma Model de exception para retorno

	@ExceptionHandler(EmailExsitException.class)
	public ResponseEntity<ModelError> existEmailExceptionHandler(EmailExsitException exception){
		return ResponseEntity.status(401).body(new ModelError(401L, exception.getMessage(), "Tente outro Email!"));
	}
	
	@ExceptionHandler(EmailNotExistException.class)
	public ResponseEntity<ModelError> emailNotExistExceptionHandler(EmailNotExistException exception){
		return ResponseEntity.status(404).body(new ModelError(404L, exception.getMessage(), "Cadastre o Email!"));
	}
	
	@ExceptionHandler(WrongPasswordException.class)
	public ResponseEntity<ModelError> wrongPasswordExceptionHandler(WrongPasswordException exception){
		return ResponseEntity.status(422).body(new ModelError(422L, exception.getMessage(), "Tente com outra senha!"));
	}
}
