package com.mercadolibre.dnaapi.handler;

import com.mercadolibre.dnaapi.dto.ValidacaoErroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Encapsula e manipula erros de validação da API.
 */
@RestControllerAdvice
public class ValidacaoErrorHandler {

    private final MessageSource messageSource;

    @Autowired
    public ValidacaoErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidacaoErroDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return fieldErrors.stream()
                .map(this::mapFieldErrorToValidacaoErroDTO)
                .collect(Collectors.toList());
    }

    private ValidacaoErroDTO mapFieldErrorToValidacaoErroDTO(FieldError fieldError) {
        String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
        return new ValidacaoErroDTO(fieldError.getField(), mensagem);
    }
}
