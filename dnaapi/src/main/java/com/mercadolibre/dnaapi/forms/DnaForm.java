package com.mercadolibre.dnaapi.forms;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa o input da API.
 *
 * @author Brunno Silva
 * @since 24/11/2019
 */
public class DnaForm {

    @NotNull
    @Size(min = 4, max = 10000)
    @Valid
    private List<@NotEmpty @NotNull @Pattern(regexp = "^[CTGA]+$") String> dna;

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dna);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        DnaForm other = (DnaForm) obj;
        return Objects.equals(dna, other.dna);
    }

}