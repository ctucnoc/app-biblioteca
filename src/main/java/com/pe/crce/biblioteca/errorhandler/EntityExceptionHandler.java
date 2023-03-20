package com.pe.crce.biblioteca.errorhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pe.crce.biblioteca.constant.BibliotecaConstant;

@RestControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(HttpStatusCodeException.class)
	protected ResponseEntity<Object> handleHttpRestClient(HttpStatusCodeException ex) {
		ErrorDTO sysceError = null;
		if (ex.getStatusCode().is4xxClientError()) {
			sysceError = new ErrorDTO(ex.getStatusCode(), BibliotecaConstant.PREFIX_CLIENT_ERROR);
		} else if (ex.getStatusCode().is5xxServerError()) {
			sysceError = new ErrorDTO(ex.getStatusCode(), BibliotecaConstant.PREFIX_SERVER_ERROR);
		}
		sysceError.setMessage(ex.getStatusText());
		return buildResponseEntity(sysceError);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		ErrorDTO sysceError = new ErrorDTO(HttpStatus.NOT_FOUND,
				BibliotecaConstant.PREFIX_CLIENT_ERROR + BibliotecaConstant.NOT_FOUND);
		sysceError.setMessage(ex.getMessage());
		return buildResponseEntity(sysceError);
	}

	@ExceptionHandler(EntityUnprocessableException.class)
	protected ResponseEntity<Object> handleConflict(EntityUnprocessableException ex) {
		ErrorDTO sysceError = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY,
				BibliotecaConstant.PREFIX_CLIENT_ERROR + BibliotecaConstant.UNPROCESSABLE_ENTITY);
		sysceError.setMessage(ex.getMessage());
		return buildResponseEntity(sysceError);
	}

	@ExceptionHandler(EntityGenericClientException.class)
	protected ResponseEntity<Object> handleGenericClientException(EntityGenericClientException ex) {
		ErrorDTO sysceError = new ErrorDTO(ex.getHttpStatus(), BibliotecaConstant.PREFIX_CLIENT_ERROR);
		sysceError.setMessage(ex.getMessage());
		sysceError.setSubErrors(ex.getSubErros());
		return buildResponseEntity(sysceError);
	}

	@ExceptionHandler(EntityUnauthorizedException.class)
	protected ResponseEntity<Object> handleEntityUnauthorized(EntityUnauthorizedException ex) {
		ErrorDTO sysceError = new ErrorDTO(HttpStatus.UNAUTHORIZED,
				BibliotecaConstant.PREFIX_CLIENT_ERROR + BibliotecaConstant.UNAUTHORIZED);
		sysceError.setMessage(ex.getMessage());
		return buildResponseEntity(sysceError);
	}

	@ExceptionHandler(EntityGenericServerException.class)
	protected ResponseEntity<Object> handleGenericServerException(EntityGenericServerException ex) {
		ErrorDTO sysceError = null;
		if (ex.getCode() != null) {
			sysceError = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getCode());
		} else {
			sysceError = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR,
					BibliotecaConstant.PREFIX_SERVER_ERROR + BibliotecaConstant.INTERNAL_SERVER_ERROR);
		}
		sysceError.setMessage(ex.getMessage());
		return buildResponseEntity(sysceError);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleError(Exception ex) {
		ErrorDTO sysceError = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR,
				BibliotecaConstant.PREFIX_SERVER_ERROR + BibliotecaConstant.INTERNAL_SERVER_ERROR);
		sysceError.setMessage("Error generico de servidor " + ex.getMessage());
		return buildResponseEntity(sysceError);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ErrorDTO(HttpStatus.BAD_REQUEST,
				BibliotecaConstant.PREFIX_CLIENT_ERROR + BibliotecaConstant.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";

		return buildResponseEntity(new ErrorDTO(HttpStatus.BAD_REQUEST,
				BibliotecaConstant.PREFIX_CLIENT_ERROR + BibliotecaConstant.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST,
				BibliotecaConstant.PREFIX_CLIENT_ERROR + BibliotecaConstant.BAD_REQUEST);
		errorDTO.setMessage(ex.getBindingResult().getFieldError().toString());
		errorDTO.setSubErrors(fillValidationErrorsFrom(ex));
		return buildResponseEntity(errorDTO);
	}

	protected List<SubError> fillValidationErrorsFrom(MethodArgumentNotValidException argumentNotValid) {
		List<SubError> subErrorCollection = new ArrayList<>();
		argumentNotValid.getBindingResult().getFieldErrors().get(0).getRejectedValue();
		argumentNotValid.getBindingResult().getFieldErrors().stream().forEach((objError) -> {
			SubError sysceSubError = new ValidationError(objError.getObjectName(), objError.getField(),
					objError.getRejectedValue(), objError.getDefaultMessage());
			subErrorCollection.add(sysceSubError);
		});
		return subErrorCollection;
	}

	private ResponseEntity<Object> buildResponseEntity(ErrorDTO errorDTO) {
		return new ResponseEntity<>(errorDTO, errorDTO.getHttpStatus());
	}
}
