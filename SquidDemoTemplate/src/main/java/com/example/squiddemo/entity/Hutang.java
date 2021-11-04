package com.example.squiddemo.entity;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public class Hutang {
    private int id;
    private String pemeberiUtang;
    private double jumlah;
    private Player player;

    public Hutang() {
    }

    public Hutang(int id, String pemeberiUtang, double jumlah, Player player) {
        this.id = id;
        this.pemeberiUtang = pemeberiUtang;
        this.jumlah = jumlah;
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPemeberiUtang() {
        return pemeberiUtang;
    }

    public void setPemeberiUtang(String pemeberiUtang) {
        this.pemeberiUtang = pemeberiUtang;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
