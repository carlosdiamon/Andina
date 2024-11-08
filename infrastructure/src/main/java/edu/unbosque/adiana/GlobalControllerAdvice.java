package edu.unbosque.adiana;

import edu.unbosque.adiana.client.exceptions.AuthException;
import edu.unbosque.adiana.client.exceptions.ClientNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<String> handleClientNotFoundException(final AuthException exception) {
		return ResponseEntity.status(NOT_FOUND).body(exception.getMessage());
	}

	@ExceptionHandler(AuthException.class)
	public ResponseEntity<String> handleAuthException(final AuthException exception) {
		return ResponseEntity.status(BAD_REQUEST)
			       .body(exception.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
		return ResponseEntity.status(BAD_REQUEST).body(exception.getBindingResult().toString());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericError(final Exception exception) {
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exception.getMessage());
	}

}
