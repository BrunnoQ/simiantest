package com.mercadolibre.dnaapi.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe responsavel por encapsular todas as regras inerentes a definicao de
 * DNA expostas no enunciado do exercicio hora informado.
 * 
 * @author Brunno Silva
 * @since 21/11/2019
 */
public class DefineSimianDnaBusiness {

    private static final Logger LOGGER = LogManager.getLogger(DefineSimianDnaBusiness.class);

    /**
     * 
     * @param dna              {@code Array} no qual o valor delimitante deve ser
     *                         obtido.
     * @param numberOfSequence Quantidade de elementos a serem considerados uma
     *                         <b>repetição</b>.
     * @param totalMatchSearch Total de repetições a serem buscadas.
     * @return {@code True} se o <b>DNA</b> for de um <b>SIMIO</b>.
     */
    public static boolean isSimian(final char[][] dna, final int numberOfSequence, int totalMatchSearch) {

        LOGGER.info("##### Método iniciado #####");
        long inicioProcessamento = System.currentTimeMillis();
        boolean isSimian = false;
        int totalOfMatch = 0;

        // 1° chamada
        totalOfMatch = searchAllDnasVertical(dna, numberOfSequence, totalMatchSearch);

        // Nao varrer todo o array se o resultado ja tiver sido obtido!!!
        if (totalOfMatch >= 2) {

            isSimian = true;

        } else {
            
            totalOfMatch += searchAllDnasHorizontal(dna, numberOfSequence, totalMatchSearch);

            // Buscas mais custosas!!
            if (totalOfMatch >= 2) {
                totalOfMatch += searchAllDnasDiagonalRight(dna, numberOfSequence, totalMatchSearch);
                isSimian = true;
            } else {
                totalOfMatch += searchAllDnasDiagonalLeft(dna, numberOfSequence, totalMatchSearch);
                if (totalOfMatch >= 2) {
                    isSimian = true;
                }
            }
        }

        LOGGER.debug("totalOfMatch: "+totalOfMatch);
        LOGGER.info("Tempo total de processamento: " + (System.currentTimeMillis() - inicioProcessamento) + " ms");
        LOGGER.info("##### Método finalizado #####");
        return isSimian;
    }

    /**
     * Obtem a <b>posicao minima</b> onde o ponteiro do inicio de pesquisa deve ser
     * posionado.
     * 
     * @param dna              {@code Array} no qual o valor delimitante deve ser
     *                         obtido.
     * @param numberOfSequence Quantidade de elementos a serem considerados uma
     *                         <b>repetição</b>.
     * @return Retorna a posicao onde a pesquisa deve ser iniciada no eixo Y da
     *         matriz.
     */
    public static int getMinimumPointSearch(final char[][] dna, final int numberOfSequence) {

        int minimumPointSearch = dna.length - numberOfSequence;

        return minimumPointSearch;
    }

    /**
     * Realiza uma busca do total de conjuntos de elementos repetidos na <b>DIAGONAL
     * a esquerda</b> da matriz.
     * 
     * @param dna              <b>Sequencia de caracteres</b> onde a busca de
     *                         elementos repetidos sera retornado
     * @param numberOfSequence Quantidade de elementos a serem considerados uma
     *                         <b>repetição</b>.
     * @param totalMatchSearch Total de repetições a serem buscadas.
     * @return Retorna o total de <b>repetições encontradas</b>.
     */
    public static int searchAllDnasDiagonalLeft(final char[][] dna, final int numberOfSequence,
            final int totalMatchSearch) {

        LOGGER.info("##### Método iniciado #####");
        int linha = getMinimumPointSearch(dna, numberOfSequence);
        int totalOfSequences = 0;
        int coluna = dna.length - 1;

        while (coluna >= 0) {
            totalOfSequences += searchDnaDiagonalLeft(linha, coluna, dna, numberOfSequence, totalMatchSearch);
            if (linha == 0) {
                coluna--;
            } else {
                linha--;
            }
        }

        LOGGER.debug("Retorno:" + totalOfSequences);
        LOGGER.info("##### Método finalizado #####");

        return totalOfSequences;
    }

    /**
     * Realiza uma busca do total de conjuntos de elementos repetidos na <b>DIAGONAL
     * a direita</b> da matriz.
     * 
     * @param dna              <b>Sequencia de caracteres</b> onde a busca de
     *                         elementos repetidos sera retornado
     * @param numberOfSequence Quantidade de elementos a serem considerados uma
     *                         <b>repetição</b>.
     * @param totalMatchSearch Total de repetições a serem buscadas.
     * @return Retorna o total de <b>repetições encontradas</b>.
     */
    public static int searchAllDnasDiagonalRight(final char[][] dna, final int numberOfSequence,
            final int totalMatchSearch) {

        LOGGER.info("##### Método iniciado #####");
        int linha = getMinimumPointSearch(dna, numberOfSequence);
        int totalOfSequences = 0;
        int coluna = 0;

        while (coluna < dna.length) {
            totalOfSequences += searchDnaDiagonalRight(linha, coluna, dna, numberOfSequence, totalMatchSearch);
            if (linha == 0) {
                coluna++;
            } else {
                linha--;
            }
        }

        LOGGER.debug("Retorno:" + totalOfSequences);
        LOGGER.info("##### Método finalizado #####");

        return totalOfSequences;
    }

    /**
     * Realiza uma busca do total de conjuntos de elementos repetidos na
     * <b>horizontal</b> da matriz.
     * 
     * @param dna              <b>Sequencia de caracteres</b> onde a busca de
     *                         elementos repetidos sera retornado
     * @param numberOfSequence Quantidade de elementos a serem considerados uma
     *                         <b>repetição</b>.
     * @param totalMatchSearch Total de repetições a serem buscadas.
     * @return
     */
    public static int searchAllDnasHorizontal(final char[][] dna, final int numberOfSequence,
            final int totalMatchSearch) {

        LOGGER.info("##### Método iniciado #####");
        int linha = 0;
        int totalOfSequences = 0;
        int coluna = 0;

        while (linha < dna.length) {
            totalOfSequences += searchDnaHorizontal(linha, coluna, dna, numberOfSequence, totalMatchSearch);
            linha++;
        }

        LOGGER.debug("Retorno:" + totalOfSequences);
        LOGGER.info("##### Método finalizado #####");

        return totalOfSequences;
    }

    /**
     * Realiza uma busca do total de conjuntos de elementos repetidos na
     * <b>vertical</b> da matriz.
     * 
     * @param dna              <b>Sequencia de caracteres</b> onde a busca de
     *                         elementos repetidos sera retornado
     * @param numberOfSequence Quantidade de elementos a serem considerados uma
     *                         <b>repetição</b>.
     * @param totalMatchSearch Total de repetições a serem buscadas.
     * @return
     */
    public static int searchAllDnasVertical(final char[][] dna, final int numberOfSequence,
            final int totalMatchSearch) {

        LOGGER.info("##### Método iniciado #####");
        int linha = 0;
        int totalOfSequences = 0;
        int coluna = 0;

        while (coluna < dna.length) {
            totalOfSequences += searchDnaVertical(linha, coluna, dna, numberOfSequence, totalMatchSearch);
            coluna++;
        }

        LOGGER.debug("Retorno:" + totalOfSequences);
        LOGGER.info("##### Método finalizado #####");

        return totalOfSequences;
    }

    /**
     * Realiza uma busca de elementos repetidos que estejam em sequencia <b>DIAGONAL
     * a direita.</b>
     * 
     * @param linhaStart       {@code Linha} inicial da pesquisa a ser realizada
     * @param colunaStart      {@code Coluna} inicial da pesquisa a ser realizada
     * @param dna              <b>Sequencia de caracteres</b> onde a busca de
     *                         elemenstos repetidos sera retornado
     * @param numberOfSequence Quantidade de elementos a serem considerados uma
     *                         <b>repetição</b>.
     * @param totalMatchSearch Total de repetições a serem buscadas.
     * @return Retorna o total de <b>repetições encontradas</b>.
     */
    public static int searchDnaDiagonalRight(final int linhaStart, final int colunaStart, final char[][] dna,
            final int numberOfSequence, final int totalMatchSearch) {

        LOGGER.info("##### Método iniciado #####");
        LOGGER.debug("Parâmetros recebidos: ");
        LOGGER.debug("1- linhaStart: " + linhaStart);
        LOGGER.debug("2- colunaStart: " + colunaStart);
        LOGGER.debug("3- numberOfSequence: " + colunaStart);
        LOGGER.debug("4- numberOfSequence:" + totalMatchSearch);
        // Variaveis para manipulacao local
        int linha = linhaStart;
        int coluna = colunaStart;
        char currentNucleotido;
        int countSequence = 1;
        int totalOfSequences = 0;

        // Percorrer ate o penultimo elemento para comparar!
        while (linha < dna.length - 1 & coluna < dna.length - 1 & (totalOfSequences != totalMatchSearch)) {

            currentNucleotido = dna[linha][coluna];

            /**
             * Comparar o nucleotido corrente com o proximo.
             */
            if (currentNucleotido == dna[linha + 1][coluna + 1]) {
                countSequence++;

                if (countSequence == numberOfSequence) {
                    totalOfSequences++;
                    countSequence = 0;// zera a contagem
                }
            } else {
                countSequence = 1; // "Zera" a contagem
            }

            // Pular linha e coluna na diagonal a direita
            linha++;
            coluna++;
        }

        LOGGER.debug("Retorno:" + totalOfSequences);
        LOGGER.info("##### Método finalizado #####");

        return totalOfSequences;
    }

    /**
     * Realiza uma busca de elementos repetidos que estejam em sequencia <b>DIAGONAL
     * a esquerda.</b>
     * 
     * @param linhaStart       {@code Linha} inicial da pesquisa a ser realizada
     * @param colunaStart      {@code Coluna} inicial da pesquisa a ser realizada
     * @param dna              <b>Sequencia de caracteres</b> onde a busca de
     *                         elemenstos repetidos sera retornado
     * @param numberOfSequence Quantidade de elementos a serem considerados uma
     *                         <b>repetição</b>.
     * @param totalMatchSearch Total de repetições a serem buscadas.
     * @return Retorna o total de <b>repetições encontradas</b>.
     */
    public static int searchDnaDiagonalLeft(final int linhaStart, final int colunaStart, final char[][] dna,
            final int numberOfSequence, final int totalMatchSearch) {

        LOGGER.info("##### Método iniciado #####");
        LOGGER.debug("Parâmetros recebidos: ");
        LOGGER.debug("1- linhaStart: " + linhaStart);
        LOGGER.debug("2- colunaStart: " + colunaStart);
        LOGGER.debug("3- numberOfSequence: " + colunaStart);
        LOGGER.debug("4- numberOfSequence:" + totalMatchSearch);
        // Variaveis para manipulacao local
        int linha = linhaStart;
        int coluna = colunaStart;
        char currentNucleotido;
        int countSequence = 1;
        int totalOfSequences = 0;

        // Percorrer ate o penultimo elemento para comparar!
        while (linha < dna.length - 1 & coluna > 1 & (totalOfSequences != totalMatchSearch)) {

            currentNucleotido = dna[linha][coluna];

            /**
             * Comparar o nucleotido corrente com o proximo.
             */
            if (currentNucleotido == dna[linha + 1][coluna - 1]) {
                countSequence++;

                if (countSequence == numberOfSequence) {
                    totalOfSequences++;
                    countSequence = 0;// zera a contagem
                }
            } else {
                countSequence = 1; // "Zera" a contagem
            }

            // Pular linha e coluna na diagonal a esquerda
            linha++;
            coluna--;
        }

        LOGGER.debug("Retorno:" + totalOfSequences);
        LOGGER.info("##### Método finalizado #####");

        return totalOfSequences;

    }

    /**
     * Realiza uma busca de elementos repetidos que estejam em sequencia
     * <b>HORIZONTAL a direita.</b>
     * 
     * @param linhaStart       {@code Linha} inicial da pesquisa a ser realizada
     * @param colunaStart      {@code Coluna} inicial da pesquisa a ser realizada
     * @param dna              <b>Sequencia de caracteres</b> onde a busca de
     *                         elemenstos repetidos sera retornado
     * @param numberOfSequence Quantidade de elementos a serem considerados uma
     *                         <b>repetição</b>.
     * @param totalMatchSearch Total de repetições a serem buscadas.
     * @return Retorna o total de <b>repetições encontradas</b>.
     */
    public static int searchDnaHorizontal(final int linhaStart, final int colunaStart, final char[][] dna,
            final int numberOfSequence, final int totalMatchSearch) {

        LOGGER.info("##### Método iniciado #####");
        LOGGER.debug("Parâmetros recebidos: ");
        LOGGER.debug("1- linhaStart: " + linhaStart);
        LOGGER.debug("2- colunaStart: " + colunaStart);
        LOGGER.debug("3- numberOfSequence: " + colunaStart);
        LOGGER.debug("4- numberOfSequence:" + totalMatchSearch);
        // Variaveis para manipulacao local
        int linha = linhaStart;
        int coluna = colunaStart;
        char currentNucleotido;
        int countSequence = 1;
        int totalOfSequences = 0;

        // Percorrer ate o penultimo elemento para comparar!
        while (coluna < dna.length - 1 & (totalOfSequences != totalMatchSearch)) {

            currentNucleotido = dna[linha][coluna];

            /**
             * Comparar o nucleotido corrente com o proximo.
             */
            if (currentNucleotido == dna[linha][coluna + 1]) {
                countSequence++;

                if (countSequence == numberOfSequence) {
                    totalOfSequences++;
                    countSequence = 0;// zera a contagem
                }
            } else {
                countSequence = 1; // "Zera" a contagem
            }

            // Pular coluna a direita
            coluna++;
        }

        LOGGER.debug("Retorno:" + totalOfSequences);
        LOGGER.info("##### Método finalizado #####");

        return totalOfSequences;
    }

    /**
     * Realiza uma busca de elementos repetidos que estejam em sequencia <b>VERTICAL
     * de cima para baixo.</b>
     * 
     * @param linhaStart       {@code Linha} inicial da pesquisa a ser realizada
     * @param colunaStart      {@code Coluna} inicial da pesquisa a ser realizada
     * @param dna              <b>Sequencia de caracteres</b> onde a busca de
     *                         elemenstos repetidos sera retornado
     * @param numberOfSequence Quantidade de elementos a serem considerados uma
     *                         <b>repetição</b>.
     * @param totalMatchSearch Total de repetições a serem buscadas.
     * @return Retorna o total de <b>repetições encontradas</b>.
     */
    public static int searchDnaVertical(final int linhaStart, final int colunaStart, final char[][] dna,
            final int numberOfSequence, final int totalMatchSearch) {

        LOGGER.info("##### Método iniciado #####");
        LOGGER.debug("Parâmetros recebidos: ");
        LOGGER.debug("1- linhaStart: " + linhaStart);
        LOGGER.debug("2- colunaStart: " + colunaStart);
        LOGGER.debug("3- numberOfSequence: " + colunaStart);
        LOGGER.debug("4- numberOfSequence:" + totalMatchSearch);
        // Variaveis para manipulacao local
        int linha = linhaStart;
        int coluna = colunaStart;
        char currentNucleotido;
        int countSequence = 1;
        int totalOfSequences = 0;

        // Percorrer ate o penultimo elemento para comparar!
        while (linha < dna.length - 1 & (totalOfSequences != totalMatchSearch)) {

            currentNucleotido = dna[linha][coluna];

            /**
             * Comparar o nucleotido corrente com o proximo.
             */
            if (currentNucleotido == dna[linha + 1][coluna]) {
                countSequence++;

                if (countSequence == numberOfSequence) {
                    totalOfSequences++;
                    countSequence = 0;// zera a contagem
                }
            } else {
                countSequence = 1; // "Zera" a contagem
            }

            // Pular linha
            linha++;
        }

        LOGGER.debug("Retorno:" + totalOfSequences);
        LOGGER.info("##### Método finalizado #####");

        return totalOfSequences;
    }

}