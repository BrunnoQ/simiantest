package com.mercadolibre.dnaapi.business;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MatrizBusinessTest {

    private static char[][] criarArrayHorinzontal(int quantidadeLinhas, int quantidadeColunas) {

        char[][] dna = new char[quantidadeLinhas][quantidadeColunas];
        char[] carga = { 'A', 'T', 'C', 'G' };
        int i2 = 0;
        // popular essa porra horizontal
        for (int i = 0; i < quantidadeLinhas; i++) {

            for (int hr = 0; hr < quantidadeColunas; hr++) {

                if (i2 >= carga.length) {
                    i2 = 0;
                    dna[i][hr] = carga[i2];
                } else {
                    dna[i][hr] = carga[i2];
                    i2++;
                }
            }
        }

        return dna;
    }

    @Test
    public void getDiagonalRight() {
        char[] dna = MatrizBusiness.getDiagonalRight(0, 0, criarArrayHorinzontal(10, 10));
        char[] expected = { 'A', 'T', 'C', 'G', 'A', 'A', 'T', 'C', 'G', 'A' }; // Valor obtido da diagonal total
        assertArrayEquals(expected, dna);
    }

    @Test
    public void getDiagonalLeft() {
        char[] dna = MatrizBusiness.getDiagonalLeft(0, 9, criarArrayHorinzontal(10, 10));
        char[] expected = { 'A', 'G', 'C', 'T', 'A', 'A', 'G', 'C', 'T', 'A' }; // Valor obtido da diagonal total
        assertArrayEquals(expected, dna);
    }

    @Test
    public void getHorizontal() {
        char[] dna = MatrizBusiness.getHorizontal(0, criarArrayHorinzontal(10, 10));
        char[] expected = { 'A', 'T', 'C', 'G', 'A', 'A', 'T', 'C', 'G', 'A' };
        assertArrayEquals(expected, dna);
    }

    @Test
    public void getVertical() {
        char[] dna = MatrizBusiness.getVertical(0, criarArrayHorinzontal(10, 10));
        char[] expected = { 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A' };
        assertArrayEquals(expected, dna);
    }

    @Test
    public void getMinimumPointSearch() {
        int minimumPoint = MatrizBusiness.getMinimumPointSearch(criarArrayHorinzontal(50, 50));
        assertEquals(46, minimumPoint);
    }
}