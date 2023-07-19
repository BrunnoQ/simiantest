package com.mercadolibre.dnaapi.forms;

public class StatsResponse {

    private int countSimianDna;
    private int countHumanDna;
    private double ratio;

    public StatsResponse(int countSimianDna, int countHumanDna, double ratio) {
        this.countSimianDna = countSimianDna;
        this.countHumanDna = countHumanDna;
        this.ratio = ratio;
    }

    public int getCountSimianDna() {
        return countSimianDna;
    }

    public void setCountSimianDna(int countSimianDna) {
        this.countSimianDna = countSimianDna;
    }

    public int getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(int countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}