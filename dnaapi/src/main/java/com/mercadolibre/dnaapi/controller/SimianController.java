package com.mercadolibre.dnaapi.controller;

import javax.validation.Valid;

import com.mercadolibre.dnaapi.forms.DnaForm;
import com.mercadolibre.dnaapi.forms.DnaResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsavel por orquestrar as regras de negocio da API.
 * @@author Brunno Silva
 * @since 23/11/2019
 */
@RestController
public class SimianController{

    /**
     * 
     * @param input Dados de entrada da API
     * @return um {@code boolean} informando se o DNA é de um <b>SIMIO</b> ou não.
     */
    @PostMapping(path = "simian")
    public ResponseEntity<DnaResponse> verificarDNA(@RequestBody @Valid final DnaForm input){
        DnaResponse response = new DnaResponse();
        response.setIs_simian(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}