package io.github.zam0k.cardsapi.exception.handler;

import io.github.zam0k.cardsapi.exception.ApiError;
import io.github.zam0k.cardsapi.exception.BusinessRuleException;
import io.github.zam0k.cardsapi.exception.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(Exception ex, HttpServletRequest request) {
        ApiError error = new ApiError(
                OffsetDateTime.now(),
                ex.getMessage(),
                INTERNAL_SERVER_ERROR.value(),
                request.getRequestURI(),
                ex.getClass().getSimpleName()
        );
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        ApiError error = new ApiError(
                OffsetDateTime.now(),
                ex.getMessage(),
                NOT_FOUND.value(),
                request.getRequestURI(),
                ex.getClass().getSimpleName()
        );
        return ResponseEntity.status(NOT_FOUND).body(error);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiError> handleBusinessRuleException(BusinessRuleException ex, HttpServletRequest request) {
        ApiError error = new ApiError(
                OffsetDateTime.now(),
                ex.getMessage(),
                BAD_REQUEST.value(),
                request.getRequestURI(),
                ex.getClass().getSimpleName()
        );
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<String> messages = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        ApiError error = new ApiError(
                OffsetDateTime.now(),
                messages,
                BAD_REQUEST.value(),
                request.getContextPath(),
                "Validation Error"
        );

        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        ApiError error = new ApiError(
                OffsetDateTime.now(),
                ex.getMostSpecificCause().getMessage(),
                BAD_REQUEST.value(),
                request.getContextPath(),
                "Malformed Json Request"
        );

        return ResponseEntity.status(BAD_REQUEST).body(error);
    }


}
