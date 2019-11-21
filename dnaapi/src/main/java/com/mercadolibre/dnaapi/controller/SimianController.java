package com.mercadolibre.dnaapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimianController{

    @GetMapping(path = "simian")
    public String helloSimian(){
        return "LORE VAT ZAND CUSCAZ [SIMIAN]";
    }

}