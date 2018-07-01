package CorpFood.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<ValidationError> offerNotFoundError(final OfferNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getMessage());
    }


    private ResponseEntity<ValidationError> error(final Exception exception, final HttpStatus httpStatus, final String logref) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new ValidationError(logref, message), httpStatus);
    }
}
