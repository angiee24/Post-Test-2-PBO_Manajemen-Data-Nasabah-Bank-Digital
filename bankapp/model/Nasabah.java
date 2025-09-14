package com.mycompany.bankapp.model;

import java.util.ArrayList;

public class Nasabah {
    private String nomorRekening;
    private String nama;
    private double saldo;
    private ArrayList<String> mutasiRekening;

    public Nasabah(String nomorRekening, String nama, double saldo) {
        this.nomorRekening = nomorRekening;
        this.nama = nama;
        this.saldo = saldo;
        this.mutasiRekening = new ArrayList<>();
    }

    public String getNomorRekening() { return nomorRekening; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public ArrayList<String> getMutasiRekening() { return mutasiRekening; }

    @Override
    public String toString() {
        return "No. Rek: " + nomorRekening +
               ", Nama: " + nama +
               ", Saldo: Rp" + String.format("%,.2f", saldo);
    }
}
