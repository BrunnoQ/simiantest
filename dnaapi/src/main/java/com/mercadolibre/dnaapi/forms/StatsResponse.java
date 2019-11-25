package com.mercadolibre.dnaapi.forms;

public class StatsResponse {

    private int count_simian_dna;
    private int count_human_dna;
    private double ratio;

    public StatsResponse(int count_simian_dna, int count_human_dna, double ratio) {
        this.count_simian_dna = count_simian_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = ratio;
    }

    public int getCount_simian_dna() {
        return count_simian_dna;
    }

    public void setCount_simian_dna(int count_simian_dna) {
        this.count_simian_dna = count_simian_dna;
    }

    public int getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(int count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

}