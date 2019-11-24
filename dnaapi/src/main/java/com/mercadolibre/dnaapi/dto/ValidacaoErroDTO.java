package com.mercadolibre.dnaapi.dto;

/**
 * Erro padronizado.
 * @@author Brunno Silva
 */
public class ValidacaoErroDTO {

    private String campo;
    private String mensagemErro;

    /**
     * Construtor da classe
     * @param campo do erro
     * @param mensagemErro do erro
     */
    public ValidacaoErroDTO(String campo, String mensagemErro) {
        this.campo = campo;
        this.mensagemErro = mensagemErro;
    }    

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

}