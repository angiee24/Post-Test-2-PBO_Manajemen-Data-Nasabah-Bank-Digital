package com.mycompany.bankapp.service;

import com.mycompany.bankapp.model.Nasabah;
import java.util.ArrayList;

public class BankService {
    private ArrayList<Nasabah> daftarNasabah = new ArrayList<>();
    private long noRekCounter = 2025001;
    private static final double SALDO_MINIMUM = 50000.0;
    private static final double BIAYA_TRANSFER = 2500.0;

    public void buatDataAwal() {
        tambahNasabah("Budi Santoso", 500000);
        tambahNasabah("Citra Lestari", 1200000);
    }

    public void tambahNasabah(String nama, double saldoAwal) {
        String noRekBaru = "REK" + noRekCounter++;
        Nasabah nasabah = new Nasabah(noRekBaru, nama, saldoAwal);
        nasabah.getMutasiRekening().add("Setoran Awal: +Rp" + saldoAwal);
        daftarNasabah.add(nasabah);
        System.out.println("Nasabah baru: " + nasabah);
    }

    public void tampilkanSemuaNasabah() {
        if (daftarNasabah.isEmpty()) {
            System.out.println("Belum ada data nasabah.");
        } else {
            for (int i = 0; i < daftarNasabah.size(); i++) {
                System.out.println((i + 1) + ". " + daftarNasabah.get(i));
            }
        }
    }

    public void ubahNamaNasabah(String noRek, String namaBaru) {
        Nasabah nasabah = cariNasabahByNoRek(noRek);
        if (nasabah != null) {
            nasabah.setNama(namaBaru);
            System.out.println("Nama berhasil diubah.");
        } else {
            System.out.println("Rekening tidak ditemukan.");
        }
    }

    public void hapusNasabah(String noRek) {
        Nasabah nasabah = cariNasabahByNoRek(noRek);
        if (nasabah != null) {
            daftarNasabah.remove(nasabah);
            System.out.println("Nasabah berhasil dihapus.");
        } else {
            System.out.println("Rekening tidak ditemukan.");
        }
    }

    public void setorTunai(String noRek, double jumlah) {
        Nasabah nasabah = cariNasabahByNoRek(noRek);
        if (nasabah != null) {
            if (jumlah < 10000) {
                System.out.println("Minimal setor Rp10.000");
                return;
            }
            nasabah.setSaldo(nasabah.getSaldo() + jumlah);
            nasabah.getMutasiRekening().add("Setor: +Rp" + jumlah);
            System.out.println("Saldo baru: Rp" + nasabah.getSaldo());
        } else {
            System.out.println("Rekening tidak ditemukan.");
        }
    }

    public void tarikTunai(String noRek, double jumlah) {
        Nasabah nasabah = cariNasabahByNoRek(noRek);
        if (nasabah != null) {
            if ((nasabah.getSaldo() - jumlah) >= SALDO_MINIMUM) {
                nasabah.setSaldo(nasabah.getSaldo() - jumlah);
                nasabah.getMutasiRekening().add("Tarik: -Rp" + jumlah);
                System.out.println("Saldo tersisa: Rp" + nasabah.getSaldo());
            } else {
                System.out.println("Saldo tidak cukup / kurang dari minimum.");
            }
        } else {
            System.out.println("Rekening tidak ditemukan.");
        }
    }

    public void transferDana(String noRekPengirim, String noRekPenerima, double jumlah) {
        Nasabah pengirim = cariNasabahByNoRek(noRekPengirim);
        Nasabah penerima = cariNasabahByNoRek(noRekPenerima);

        if (pengirim == null || penerima == null) {
            System.out.println("Rekening pengirim/penerima tidak ditemukan.");
            return;
        }
        if (noRekPengirim.equals(noRekPenerima)) {
            System.out.println("Tidak bisa transfer ke rekening sendiri.");
            return;
        }
        if (pengirim.getSaldo() >= (jumlah + BIAYA_TRANSFER)) {
            pengirim.setSaldo(pengirim.getSaldo() - (jumlah + BIAYA_TRANSFER));
            penerima.setSaldo(penerima.getSaldo() + jumlah);

            pengirim.getMutasiRekening().add("Transfer keluar: -Rp" + jumlah);
            penerima.getMutasiRekening().add("Transfer masuk: +Rp" + jumlah);

            System.out.println("Transfer berhasil.");
        } else {
            System.out.println("Saldo tidak cukup.");
        }
    }

    public void cariNasabah(String keyword) {
        boolean ditemukan = false;
        for (Nasabah nasabah : daftarNasabah) {
            if (nasabah.getNomorRekening().equalsIgnoreCase(keyword) ||
                nasabah.getNama().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(nasabah);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Nasabah tidak ditemukan.");
        }
    }

    public void lihatMutasiRekening(String noRek) {
        Nasabah nasabah = cariNasabahByNoRek(noRek);
        if (nasabah != null) {
            System.out.println("\nMutasi Rekening untuk: " + nasabah.getNama());
            System.out.println("---------------------------------------------");
            if (nasabah.getMutasiRekening().isEmpty()) {
                System.out.println("Belum ada mutasi.");
            } else {
                for (String mutasi : nasabah.getMutasiRekening()) {
                    System.out.println("- " + mutasi);
                }
            }
            System.out.println("---------------------------------------------");
        } else {
            System.out.println("Rekening tidak ditemukan.");
        }
    }

    private Nasabah cariNasabahByNoRek(String noRek) {
        for (Nasabah nasabah : daftarNasabah) {
            if (nasabah.getNomorRekening().equals(noRek)) {
                return nasabah;
            }
        }
        return null;
    }
}
