package com.mycompany.bankapp.view;

import com.mycompany.bankapp.service.BankService;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static int inputMenu(int min, int max) {
        while (true) {
            System.out.print("Pilih menu (" + min + "-" + max + "): ");
            String input = scanner.nextLine();
            try {
                int pilihan = Integer.parseInt(input);
                if (pilihan >= min && pilihan <= max) {
                    return pilihan;
                } else {
                    System.out.println("Pilihan harus antara " + min + " dan " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, masukkan angka!");
            }
        }
    }

    public static double inputDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, masukkan angka!");
            }
        }
    }

    public static void main(String[] args) {
        BankService service = new BankService();
        service.buatDataAwal();

        while (true) {
            System.out.println("\n--- Program Bank Digital ---");
            System.out.println("1. Tambah Nasabah");
            System.out.println("2. Tampilkan Nasabah");
            System.out.println("3. Ubah Nama Nasabah");
            System.out.println("4. Hapus Nasabah");
            System.out.println("5. Lakukan Transaksi");
            System.out.println("6. Lihat Mutasi Rekening");
            System.out.println("7. Cari Nasabah (Nama/No. Rek)");
            System.out.println("8. Keluar");

            int pilihan = inputMenu(1, 8);

            switch (pilihan) {
                case 1:
                    System.out.print("Nama: ");
                    String nama = scanner.nextLine();
                    double saldo = inputDouble("Setoran awal: ");
                    service.tambahNasabah(nama, saldo);
                    break;
                case 2:
                    service.tampilkanSemuaNasabah();
                    break;
                case 3:
                    System.out.print("No.Rek: ");
                    String noRekUbah = scanner.nextLine();
                    System.out.print("Nama baru: ");
                    String namaBaru = scanner.nextLine();
                    service.ubahNamaNasabah(noRekUbah, namaBaru);
                    break;
                case 4:
                    System.out.print("No.Rek: ");
                    String noRekHapus = scanner.nextLine();
                    service.hapusNasabah(noRekHapus);
                    break;
                case 5:
                    while (true) {
                        System.out.println("\n--- Menu Transaksi ---");
                        System.out.println("1. Setor Tunai");
                        System.out.println("2. Tarik Tunai");
                        System.out.println("3. Transfer Dana");
                        System.out.println("4. Kembali ke Menu Utama");
                        int pilihTransaksi = inputMenu(1, 4);

                        switch (pilihTransaksi) {
                            case 1:
                                System.out.print("No.Rek: ");
                                String noRekSetor = scanner.nextLine();
                                double jSetor = inputDouble("Jumlah: ");
                                service.setorTunai(noRekSetor, jSetor);
                                break;
                            case 2:
                                System.out.print("No.Rek: ");
                                String noRekTarik = scanner.nextLine();
                                double jTarik = inputDouble("Jumlah: ");
                                service.tarikTunai(noRekTarik, jTarik);
                                break;
                            case 3:
                                System.out.print("No.Rek Pengirim: ");
                                String pengirim = scanner.nextLine();
                                System.out.print("No.Rek Penerima: ");
                                String penerima = scanner.nextLine();
                                double jTransfer = inputDouble("Jumlah: ");
                                service.transferDana(pengirim, penerima, jTransfer);
                                break;
                            case 4:
                                break;
                        }
                        if (pilihTransaksi == 4) break;
                    }
                    break;
                case 6:
                    System.out.print("No.Rek: ");
                    String noRekMutasi = scanner.nextLine();
                    service.lihatMutasiRekening(noRekMutasi);
                    break;
                case 7:
                    System.out.print("Nama/No.Rek: ");
                    String keyword = scanner.nextLine();
                    service.cariNasabah(keyword);
                    break;
                case 8:
                    System.out.println("Terima kasih!");
                    return;
            }
        }
    }
}
