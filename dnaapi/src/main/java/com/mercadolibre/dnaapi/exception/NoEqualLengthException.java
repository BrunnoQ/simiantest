package com.mercadolibre.dnaapi.exception;

public class NoEqualLengthException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6200366301373192518L;

    /**
     * @param mensagem
     */
    public NoEqualLengthException(final String mensagem) {
        super(mensagem);
    }

}