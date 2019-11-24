package com.mercadolibre.dnaapi.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.dnaapi.dto.ValidacaoErroDTO;

/**
 * Encapsula e manipulacao erros de validacao da API.
 */
@RestControllerAdvice
public class ValidacaoErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidacaoErroDTO> handler(MethodArgumentNotValidException exception){

        List<ValidacaoErroDTO> erros = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidacaoErroDTO erro = new ValidacaoErroDTO(e.getField(),mensagem);
            erros.add(erro);
        });

        return erros; 
    }

}