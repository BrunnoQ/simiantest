package com.mercadolibre.dnaapi.handler;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.dnaapi.dto.ValidacaoErroDTO;
import com.mercadolibre.dnaapi.exception.NoEqualLengthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handler de erros nos casos de sequencia de strings com 
 * tamanhos diferentes.
 */
@RestControllerAdvice
public class NoEqualLengthHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoEqualLengthException.class)
    public List<ValidacaoErroDTO> handler(NoEqualLengthException exception) {

        List<ValidacaoErroDTO> erros = new ArrayList<>();

        String mensagem = exception.getMessage();
        ValidacaoErroDTO erro = new ValidacaoErroDTO("dna", mensagem);
        erros.add(erro);

        return erros;
    }

}