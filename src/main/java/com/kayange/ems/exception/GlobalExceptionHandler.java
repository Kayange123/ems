package com.kayange.ems.exception;

import com.kayange.ems.dto.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            @NonNull HttpRequestMethodNotSupportedException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
    ) {
         super.handleHttpRequestMethodNotSupported(ex, headers, status, request);

         return ResponseEntity.status(status)
                 .body(
                         ApiResponse.builder()
                                 .code(status.value())
                                 .status(HttpStatus.valueOf(status.value()).name())
                                 .error("Method Request Not supported")
                                 .build()
                 );

    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
           @NonNull MissingPathVariableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
    ) {
         super.handleMissingPathVariable(ex, headers, status, request);

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.builder()
                                .code(status.value())
                                .status(HttpStatus.valueOf(status.value()).name())
                                .error("Path Variable is Missing")
                                .build()
                );
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            @NonNull MissingServletRequestParameterException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
            ) {
         super.handleMissingServletRequestParameter(ex, headers, status, request);

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.builder()
                                .code(status.value())
                                .status(status.toString())
                                .success(false)
                                .error(ex.getMessage())
                                .build()
                );
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            @NonNull MissingServletRequestPartException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
            ) {
         super.handleMissingServletRequestPart(ex, headers, status, request);

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.builder()
                                .code(status.value())
                                .status(status.toString())
                                .success(false)
                                .error(ex.getMessage())
                                .build()
                );
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            @NonNull ServletRequestBindingException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
            ) {
         super.handleServletRequestBindingException(ex, headers, status, request);

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.builder()
                                .code(status.value())
                                .status(status.toString())
                                .success(false)
                                .error(ex.getMessage())
                                .build()
                );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
           @NonNull MethodArgumentNotValidException ex,
           @NonNull HttpHeaders headers,
           @NonNull HttpStatusCode status,
           @NonNull WebRequest request
    ) {
         super.handleMethodArgumentNotValid(ex, headers, status, request);
        Set<String> errors = new HashSet<>();
        for (var error : ex.getBindingResult().getAllErrors()){
            errors.add(error.getDefaultMessage());
        }

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.builder()
                                .status(status.toString())
                                .code(status.value())
                                .error("Validation failed")
                                .success(false)
                                .errors(errors)
                                .build()
                );

    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(
            @NonNull HandlerMethodValidationException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
            ) {
         super.handleHandlerMethodValidationException(ex, headers, status, request);

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.builder()
                                .code(status.value())
                                .status(status.toString())
                                .success(false)
                                .error(ex.getMessage())
                                .build()
                );
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            @NonNull NoHandlerFoundException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
            ) {
         super.handleNoHandlerFoundException(ex, headers, status, request);

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.builder()
                                .code(status.value())
                                .status(status.toString())
                                .success(false)
                                .error(ex.getMessage())
                                .build()
                );
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(
            @NonNull NoResourceFoundException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
            ) {
         super.handleNoResourceFoundException(ex, headers, status, request);

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.builder()
                                .code(status.value())
                                .status(status.toString())
                                .success(false)
                                .error("Credentials are invalid")
                                .build()
                );
    }

    @Override
    protected ResponseEntity<Object> handleErrorResponseException(
            @NonNull ErrorResponseException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
            ) {
         super.handleErrorResponseException(ex, headers, status, request);

        return ResponseEntity.status(status)
                .body(
                        ApiResponse.builder()
                                .code(status.value())
                                .status(HttpStatus.valueOf(status.value()).name())
                                .success(false)
                                .error(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<?>> badCredentialsExceptionHandler(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ApiResponse.builder()
                                .code(HttpStatus.UNAUTHORIZED.value())
                                .status(HttpStatus.UNAUTHORIZED.name())
                                .success(false)
                                .error("Credentials are invalid")
                                .build()
                );
    }

    @ExceptionHandler({AuthenticationException.class, AccessDeniedException.class})
    public ResponseEntity<ApiResponse<?>> authenticationExceptionHandler(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ApiResponse.builder()
                                .code(HttpStatus.UNAUTHORIZED.value())
                                .status(HttpStatus.UNAUTHORIZED.name())
                                .success(false)
                                .error("You are not authenticated")
                                .build()
                );
    }



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponse.builder()
                                .code(HttpStatus.NOT_FOUND.value())
                                .status(HttpStatus.NOT_FOUND.name())
                                .success(false)
                                .error(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<?>> badRequestFoundExceptionHandler(BadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiResponse.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .success(false)
                                .status(HttpStatus.BAD_REQUEST.name())
                                .error(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> exceptionHandler(Exception exp){
        exp.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiResponse.builder()
                                .success(false)
                                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                                .success(false)
                                .error("Oops! Something went wrong!")
                                .build()
                );
    }


}
