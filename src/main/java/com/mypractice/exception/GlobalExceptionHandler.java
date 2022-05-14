package com.mypractice.exception;

import com.mypractice.iban.IbanException;
import com.mypractice.model.error.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.Locale;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Qualifier
    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(final MessageSource messageSource) {
        super();
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> notFoundException(final NotFoundException notFoundException, final Locale locale) {
        return new ResponseEntity<>(ErrorDetails.builder().errorCode(HttpStatus.NOT_FOUND.value()).errorMessage(messageSource.getMessage(notFoundException.getMessage(), null, locale)).date(new Date()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorDetails> genericException(final GenericException genericException, final Locale locale) {
        return new ResponseEntity<>(ErrorDetails.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(genericException.getMessage()).date(new Date()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IbanException.class)
    public ResponseEntity<ErrorDetails> genericException(final IbanException ibanException) {
        return new ResponseEntity<>(ErrorDetails.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(ibanException.getMessage()).date(new Date()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
