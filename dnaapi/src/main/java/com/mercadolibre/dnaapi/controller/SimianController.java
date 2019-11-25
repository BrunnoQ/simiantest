package com.mercadolibre.dnaapi.controller;

import javax.validation.Valid;
import com.mercadolibre.dnaapi.business.DefineSimianDnaBusiness;
import com.mercadolibre.dnaapi.exception.NoEqualLengthException;
import com.mercadolibre.dnaapi.forms.DnaForm;
import com.mercadolibre.dnaapi.forms.DnaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsavel por orquestrar as regras de negocio da API.
 * 
 * @@author Brunno Silva
 * @since 23/11/2019
 */
@RestController
public class SimianController {

    /**
     * 
     * @param input Dados de entrada da API
     * @return um {@code boolean} informando se o DNA é de um <b>SIMIO</b> ou não.
     */
    @PostMapping(path = "simian", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DnaResponse> verificarDNA(@RequestBody @Valid final DnaForm input) {
        DnaResponse response = new DnaResponse();
        char[][] dna = convertInputRequestToArray2D(input);
        response.setIs_simian(DefineSimianDnaBusiness.isSimian(dna, 4, 2));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Realiza a conversao dos dados enviado para a API.
     * 
     * @param input Dados de entrada da API
     * @return {@code Array} 2D contendo os dados enviados.
     */
    private char[][] convertInputRequestToArray2D(DnaForm input) throws NoEqualLengthException {

        char[][] dna = new char[input.getDna().size()][input.getDna().size()];
        int l = 0;

        for (String s : input.getDna()) {

            char[] linha = s.toCharArray();
            if (linha.length != input.getDna().size()) {
                throw new NoEqualLengthException("TODOS os conjutos de nucleotidos devem possuir o MESMO TAMANHO!!!!!");
            } else {

                for (int c = 0; c < linha.length; c++) {
                    dna[l][c] = linha[c];
                }
            }

            l++;

        }

        return dna;
    }

}