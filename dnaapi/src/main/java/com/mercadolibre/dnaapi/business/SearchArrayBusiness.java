package com.mercadolibre.dnaapi.business;

/**
 * Classe responsavel por encapsular 
 * todas as regras inerentes a pesquisa de dados dentro
 * da matriz passada como parametro na API.
 * Todas as buscas estao aqui dentro.
 * @since 20/11/2019
 * @author Brunno Silva
 */
public class SearchArrayBusiness {

    /**
     * Obtem os elementos contidos em <b>diagonal a DIREITA de cima 
     * para baixo </b>
     * no {@code Array} de {@code character}, passado como 
     * parametro.
     * @param linhaStart {@code Linha} inicial da pesquisa a ser realizada
     * @param colunaStart {@code Coluna} inicial da pesquisa a ser realizada
     * @param matriz contendo os dados a serem pesquisados.
     * @return Retorna o {@code Array} de caracteres, obtida da pesquisa.
     */
    public static char[] getDiagonalRight(int linhaStart, int colunaStart, char[][] matriz) {

        // Array contendo somente os elementos para comparacao
        char[] arrayDiagonalRight = new char[matriz.length];

        // Variaveis para manipulacao local
        int linha = linhaStart;
        int coluna = colunaStart;

        // Condicao de parada e a linha nao ser maior que o length da matriz
        for (int i = 0; linha < matriz.length; i++) {

            arrayDiagonalRight[i] = matriz[linha][coluna];

            // Pular linha e coluna na diagonal a direita
            linha++;
            coluna++;
        }

        return arrayDiagonalRight;
    }

    /**
     * Obtem os elementos contidos em <b>diagonal a ESQUERDA de cima 
     * para baixo </b> de elementos contidos
     * no {@code Array} de {@code character}, passado como 
     * parametro.
     * @param linhaStart {@code Linha} inicial da pesquisa a ser realizada
     * @param colunaStart {@code Coluna} inicial da pesquisa a ser realizada
     * @param matriz contendo os dados a serem pesquisados.
     * @return Retorna o {@code Array} de caracteres, obtida da pesquisa.
     */
    public static char[] getDiagonalLeft(int linhaStart, int colunaStart, char[][] matriz){

        char [] arrayDiagonalLeft = new char[matriz.length];

        // Variaveis para manipulacao local
        int linha = linhaStart;
        int coluna = colunaStart;

        // Condicao de parada e a linha nao ser maior que o length da matriz
        for (int i = 0; linha < matriz.length; i++) {

            arrayDiagonalLeft[i] = matriz[linha][coluna];

            // Pular linha e coluna na diagonal a esquerda
            linha++;
            coluna--;
        }

        return arrayDiagonalLeft;

    }

    /**
     * Obtem os elementos contidos em <b>linha </b>
     * no {@code Array} de {@code character}, passado como 
     * parametro.
     * @param linhaStart {@code Linha} inicial da pesquisa a ser realizada
     * @param matriz contendo os dados a serem pesquisados.
     * @return Retorna o {@code Array} de caracteres, obtida da pesquisa.
     */
    public static char[] gethHorizontal(int linhaStart, char[][] matriz){

        char [] arrayHorizontal = matriz[linhaStart];

        return arrayHorizontal;
    }

    /**
     * Obtem os elementos contidos em <b>linha </b>
     * no {@code Array} de {@code character}, passado como 
     * parametro.
     * @param linhaStart {@code Linha} inicial da pesquisa a ser realizada
     * @param matriz contendo os dados a serem pesquisados.
     * @return Retorna o {@code Array} de caracteres, obtida da pesquisa.
     */
    public static char[] getVertical(int coluna, char[][] matriz){

        char [] arrayVertical = new char [matriz.length];

        for(int linha = 0; linha < matriz.length; linha++){
            arrayVertical[linha] = matriz[linha][coluna];
        }

        return arrayVertical;
    }

}