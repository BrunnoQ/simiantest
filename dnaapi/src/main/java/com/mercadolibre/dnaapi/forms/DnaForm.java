package com.mercadolibre.dnaapi.forms;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

/**
 * Classe que representa o input da API.
 * 
 * @@author Brunno Silva
 * @@since 24/11/2019
 */
public class DnaForm {

    @NotNull
    @Size(min = 4,max=10000)
    private List<@NotEmpty @NotNull @Length(min = 4, max= 10000) 
    @Pattern(regexp = "[^bBdefDEFh-sH-Su-zU-Z\\d\\sáéíóúàèìòùâêîôûãõç!@#$%^&*(),.?\\\":{}|<>]+") String> dna;

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dna == null) ? 0 : dna.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DnaForm other = (DnaForm) obj;
        if (dna == null) {
            if (other.dna != null)
                return false;
        } else if (!dna.equals(other.dna))
            return false;
        return true;
    }

}