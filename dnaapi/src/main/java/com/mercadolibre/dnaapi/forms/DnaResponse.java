package com.mercadolibre.dnaapi.forms;

/**
 * Resposta de API para denificao de DNA.
 * @author Brunno Silva
 * @since 23/11/2019
 */
public class DnaResponse{

    private boolean is_simian;

    public boolean isIs_simian() {
        return is_simian;
    }

    public void setIs_simian(boolean is_simian) {
        this.is_simian = is_simian;
    }

}