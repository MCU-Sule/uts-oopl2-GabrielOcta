package com.example.squiddemo.entity;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public class Player {
    private int id;
    private String nama;
    private int umur;
    private String keahlian;

    @Override
    public String toString() {
        return nama ;
    }

    public Player() {
    }
    public Player(int id, String nama, int umur, String keahlian) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
        this.keahlian = keahlian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }
}
