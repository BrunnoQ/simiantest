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
        long startProcessing = System.currentTimeMillis();
        boolean isSimian = false;
        int totalOfMatch;

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
        LOGGER.info("Tempo total de processamento: " + (System.currentTimeMillis() - startProcessing) + " ms");
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
    private static int getMinimumPointSearch(final char[][] dna, final int numberOfSequence) {
        return dna.length - numberOfSequence;
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
        int line = getMinimumPointSearch(dna, numberOfSequence);
        int totalOfSequences = 0;
        int column = dna.length - 1;

        while (column >= 0) {
            totalOfSequences += searchDnaDiagonalLeft(line, column, dna, numberOfSequence, totalMatchSearch);
            if (line == 0) {
                column--;
            } else {
                line--;
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
        int line = getMinimumPointSearch(dna, numberOfSequence);
        int totalOfSequences = 0;
        int column = 0;

        while (column < dna.length) {
            totalOfSequences += searchDnaDiagonalRight(line, column, dna, numberOfSequence, totalMatchSearch);
            if (line == 0) {
                column++;
            } else {
                line--;
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
     * @return total de elementos encontrados.
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
     * @return total de elementos encontrados.
     */
    public static int searchAllDnasVertical(final char[][] dna, final int numberOfSequence,
            final int totalMatchSearch) {

        LOGGER.info("##### Método iniciado #####");
        int line = 0;
        int totalOfSequences = 0;
        int column = 0;

        while (column < dna.length) {
            totalOfSequences += searchDnaVertical(line, column, dna, numberOfSequence, totalMatchSearch);
            column++;
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
        int line = linhaStart;
        int column = colunaStart;
        char currentNucleotido;
        int countSequence = 1;
        int totalOfSequences = 0;

        // Percorrer ate o penultimo elemento para comparar!
        while (line < dna.length - 1 & column < dna.length - 1 & (totalOfSequences != totalMatchSearch)) {

            currentNucleotido = dna[line][column];

            /*
             * Comparar o nucleotido corrente com o proximo.
             */
            if (currentNucleotido == dna[line + 1][column + 1]) {
                countSequence++;

                if (countSequence == numberOfSequence) {
                    totalOfSequences++;
                    countSequence = 0;// zera a contagem
                }
            } else {
                countSequence = 1; // "Zera" a contagem
            }

            // Pular linha e coluna na diagonal a direita
            line++;
            column++;
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
        int line = linhaStart;
        int column = colunaStart;
        char currentNucleotido;
        int countSequence = 1;
        int totalOfSequences = 0;

        // Percorrer ate o penultimo elemento para comparar!
        while (line < dna.length - 1 & column > 1 & (totalOfSequences != totalMatchSearch)) {

            currentNucleotido = dna[line][column];

            /*
             * Comparar o nucleotido corrente com o proximo.
             */
            if (currentNucleotido == dna[line + 1][column - 1]) {
                countSequence++;

                if (countSequence == numberOfSequence) {
                    totalOfSequences++;
                    countSequence = 0;// zera a contagem
                }
            } else {
                countSequence = 1; // "Zera" a contagem
            }

            // Pular linha e coluna na diagonal a esquerda
            line++;
            column--;
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
        int column = colunaStart;
        char currentNucleotido;
        int countSequence = 1;
        int totalOfSequences = 0;

        // Percorrer ate o penultimo elemento para comparar!
        while (column < dna.length - 1 & (totalOfSequences != totalMatchSearch)) {

            currentNucleotido = dna[linhaStart][column];

            /*
             * Comparar o nucleotido corrente com o proximo.
             */
            if (currentNucleotido == dna[linhaStart][column + 1]) {
                countSequence++;

                if (countSequence == numberOfSequence) {
                    totalOfSequences++;
                    countSequence = 0;// zera a contagem
                }
            } else {
                countSequence = 1; // "Zera" a contagem
            }

            // Pular coluna a direita
            column++;
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
        int line = linhaStart;
        char currentNucleotido;
        int countSequence = 1;
        int totalOfSequences = 0;

        // Percorrer ate o penultimo elemento para comparar!
        while (line < dna.length - 1 & (totalOfSequences != totalMatchSearch)) {

            currentNucleotido = dna[line][colunaStart];

            /*
             * Comparar o nucleotido corrente com o proximo.
             */
            if (currentNucleotido == dna[line + 1][colunaStart]) {
                countSequence++;

                if (countSequence == numberOfSequence) {
                    totalOfSequences++;
                    countSequence = 0;// zera a contagem
                }
            } else {
                countSequence = 1; // "Zera" a contagem
            }

            // Pular linha
            line++;
        }

        LOGGER.debug("Retorno:" + totalOfSequences);
        LOGGER.info("##### Método finalizado #####");

        return totalOfSequences;
    }

}