package com.PC.PC.ashpazi;

/**
 * Created by PC on 5/23/2017.
 */
// class sheye ghaza
public class Khordani {
    public Khordani() {
    }

    private  String esmeKhordani, mavadeLazem, tarzeTahiye;

    private int id;

    //setterha va getterha
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEsmeKhordani() {
        return esmeKhordani;
    }

    public void setEsmeKhordani(String esmeKhordani) {
        this.esmeKhordani = esmeKhordani;
    }

    public String getMavadeLazem() {
        return mavadeLazem;
    }

    public void setMavadeLazem(String mavadeLazem) {
        this.mavadeLazem = mavadeLazem;
    }

    public String getTarzeTahiye() {
        return tarzeTahiye;
    }

    public void setTarzeTahiye(String tarzeTahiye) {
        this.tarzeTahiye = tarzeTahiye;
    }
    //constructorha

    public Khordani( String esmeKhordani, String mavadeLazem, String tarzeTahiye) {
        this.esmeKhordani = esmeKhordani;
        this.mavadeLazem = mavadeLazem;
        this.tarzeTahiye = tarzeTahiye;
    }

    public Khordani( String esmeKhordani, String mavadeLazem, String tarzeTahiye, int id) {
        this.esmeKhordani = esmeKhordani;
        this.mavadeLazem = mavadeLazem;
        this.tarzeTahiye = tarzeTahiye;
        this.id = id;
    }

}

