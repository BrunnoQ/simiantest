package com.mercadolibre.dnaapi.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DefineSimianDnaBusinessTest {

    private static char[][] criarArrayHorinzontal(int quantidadeLinhas, int quantidadeColunas) {

        char[][] dna = new char[quantidadeLinhas][quantidadeColunas];
        char[] carga = { 'A','G','A','A','A','A','A','A','G','A', 
                         'G','G','G','A','G','G','G','T','C','A',
                         'A','G','C','G','C','T','A','G','C','C', 
                         'C','A','C','C','T','C','C','C','C','T',
                         'C','A','C','C','C','C','C','C','C','A',
                         'C','A','T','C','C','C','C','C','C','G',  
                         'C','A','C','C','C','C','C','C','C','C',
                         'C','A','C','C','C','C','C','A','C','C',
                         'C','A','C','G','C','C','C','C','C','C',
                         'C','A','C','C','C','A','C','C','C','T',};
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

    /**
     * Gabarito: no size
     */
    @Test
    public void searchDnaDiagonalRight() {
        int totalOfSequences = DefineSimianDnaBusiness.searchDnaDiagonalRight(4, 0, criarArrayHorinzontal(100, 100),4,2);
        assertEquals(Integer.valueOf(2), totalOfSequences);
    }

    /**
     * Gabarito: no size
     */
    @Test
    public void searchDnaDiagonalLeft(){
        int totalOfSequences = DefineSimianDnaBusiness.searchDnaDiagonalLeft(0, 9, criarArrayHorinzontal(100, 100), 4, 2);
        assertEquals(Integer.valueOf(1), totalOfSequences);
    }

    /**
     * Gabarito: no size
     */
    @Test
    public void searchDnaHorizontal(){
        int totalOfSequences = DefineSimianDnaBusiness.searchDnaHorizontal(6, 0, criarArrayHorinzontal(1000, 1000), 4, 100);
        assertEquals(Integer.valueOf(79),totalOfSequences);
    }

    /**
     * Gabarito: 20x20
     * |_A_||_G_||_A_||_A_||_A_||_A_||_A_||_A_||_G_||_A_||_G_||_G_||_G_||_A_||_G_||_G_||_G_||_T_||_C_||_A_|
       |_A_||_G_||_C_||_G_||_C_||_T_||_A_||_G_||_C_||_C_||_C_||_A_||_C_||_C_||_T_||_C_||_C_||_C_||_C_||_T_|
       |_C_||_A_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_A_||_T_||_C_||_C_||_C_||_C_||_C_||_C_||_G_|
       |_C_||_A_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_C_|
       |_C_||_A_||_C_||_G_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_C_||_C_||_A_||_C_||_C_||_C_||_T_|
       |_A_||_A_||_G_||_A_||_A_||_A_||_A_||_A_||_A_||_G_||_A_||_G_||_G_||_G_||_A_||_G_||_G_||_G_||_T_||_C_|
       |_A_||_A_||_G_||_C_||_G_||_C_||_T_||_A_||_G_||_C_||_C_||_C_||_A_||_C_||_C_||_T_||_C_||_C_||_C_||_C_|
       |_T_||_C_||_A_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_A_||_T_||_C_||_C_||_C_||_C_||_C_||_C_|
       |_G_||_C_||_A_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_C_||_C_||_C_||_C_||_A_||_C_|
       |_C_||_C_||_A_||_C_||_G_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_C_||_C_||_A_||_C_||_C_||_C_|
       |_T_||_A_||_A_||_G_||_A_||_A_||_A_||_A_||_A_||_A_||_G_||_A_||_G_||_G_||_G_||_A_||_G_||_G_||_G_||_T_|
       |_C_||_A_||_A_||_G_||_C_||_G_||_C_||_T_||_A_||_G_||_C_||_C_||_C_||_A_||_C_||_C_||_T_||_C_||_C_||_C_|
       |_C_||_T_||_C_||_A_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_A_||_T_||_C_||_C_||_C_||_C_||_C_|
       |_C_||_G_||_C_||_A_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_C_||_C_||_C_||_C_||_A_|
       |_C_||_C_||_C_||_A_||_C_||_G_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_C_||_C_||_A_||_C_||_C_|
       |_C_||_T_||_A_||_A_||_G_||_A_||_A_||_A_||_A_||_A_||_A_||_G_||_A_||_G_||_G_||_G_||_A_||_G_||_G_||_G_|
       |_T_||_C_||_A_||_A_||_G_||_C_||_G_||_C_||_T_||_A_||_G_||_C_||_C_||_C_||_A_||_C_||_C_||_T_||_C_||_C_|
       |_C_||_C_||_T_||_C_||_A_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_A_||_T_||_C_||_C_||_C_||_C_|
       |_C_||_C_||_G_||_C_||_A_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_C_||_C_||_C_||_C_|
       |_A_||_C_||_C_||_C_||_A_||_C_||_G_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_C_||_C_||_A_||_C_|
     */
    @Test
    public void searchVertical(){
        int totalOfSequences = DefineSimianDnaBusiness.searchDnaVertical(0, 0, criarArrayHorinzontal(20, 20), 4, 4);
        assertEquals(Integer.valueOf(1),totalOfSequences);
    }

    /**
     * Gabarito 10x10: 
     * |_A_||_G_||_A_||_A_||_A_||_A_||_A_||_A_||_G_||_A_|
       |_G_||_G_||_G_||_A_||_G_||_G_||_G_||_T_||_C_||_A_|
       |_A_||_G_||_C_||_G_||_C_||_T_||_A_||_G_||_C_||_C_|
       |_C_||_A_||_C_||_C_||_T_||_C_||_C_||_C_||_C_||_T_|
       |_C_||_A_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_A_|
       |_C_||_A_||_T_||_C_||_C_||_C_||_C_||_C_||_C_||_G_|
       |_C_||_A_||_C_||_C_||_C_||_C_||_C_||_C_||_C_||_C_|
       |_C_||_A_||_C_||_C_||_C_||_C_||_C_||_A_||_C_||_C_|
       |_C_||_A_||_C_||_G_||_C_||_C_||_C_||_C_||_C_||_C_|
       |_C_||_A_||_C_||_C_||_C_||_A_||_C_||_C_||_C_||_T_|
     */
    @Test
    public void searchAllDnaDiagonalRight(){
        int totalOfSequences = DefineSimianDnaBusiness.searchAllDnasDiagonalRight(criarArrayHorinzontal(10, 10), 4, 10);
        assertEquals(Integer.valueOf(7), totalOfSequences);

    }
   
}