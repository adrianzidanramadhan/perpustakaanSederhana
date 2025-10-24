import java.util.ArrayList;

class Buku {
    String isbn;
    String judul;
    String pengarang;
    boolean statusDipinjam;

    public Buku(String isbn, String judul, String pengarang) {
        this.isbn = isbn;
        this.judul = judul;
        this.pengarang = pengarang;
        this.statusDipinjam = false;
    }
}

class Anggota {
    String idAnggota;
    String nama;

    public Anggota(String idAnggota, String nama) {
        this.idAnggota = idAnggota;
        this.nama = nama;
    }
}

class Perpustakaan {
    String nama;
    ArrayList<Buku> koleksiBuku;
    ArrayList<String> daftarPeminjaman;

    public Perpustakaan(String nama) {
        this.nama = nama;
        this.koleksiBuku = new ArrayList<>();
        this.daftarPeminjaman = new ArrayList<>();
    }

    public void tambahBuku(Buku buku) {
        koleksiBuku.add(buku);
    }

    public Buku cariBuku(String isbn) {
        for (Buku b : koleksiBuku) {
            if (b.isbn.equals(isbn)) {
                return b;
            }
        }
        return null;
    }

    public void pinjamBuku(String idAnggota, String isbn) {
        Buku buku = cariBuku(isbn);
        if (buku == null) {
            System.out.println("Buku tidak ditemukan.");
            return;
        }
        if (buku.statusDipinjam) {
            System.out.println("Buku sedang dipinjam.");
            return;
        }

        buku.statusDipinjam = true;
        daftarPeminjaman.add(idAnggota + " - " + buku.judul);
        System.out.println("Buku '" + buku.judul + "' berhasil dipinjam oleh anggota " + idAnggota);
    }

    public void tampilkanKoleksiBuku() {
        System.out.println("\n=== Koleksi Buku di " + nama + " ===");
        for (Buku b : koleksiBuku) {
            String status = b.statusDipinjam ? "Dipinjam" : "Tersedia";
            System.out.println("ISBN: " + b.isbn + " | Judul: " + b.judul + " | Pengarang: " + b.pengarang + " | Status: " + status);
        }
    }

    public void tampilkanLogPeminjaman() {
        System.out.println("\n=== Log Peminjaman ===");
        if (daftarPeminjaman.isEmpty()) {
            System.out.println("(Belum ada peminjaman)");
        } else {
            for (String log : daftarPeminjaman) {
                System.out.println(log);
            }
        }
    }
}

public class mainPerpustakaan {
    public static void main(String[] args) {

        Perpustakaan perpus = new Perpustakaan("Perpustakaan Kampus");

        perpus.tambahBuku(new Buku("001", "Pemrograman Java", "Andi"));
        perpus.tambahBuku(new Buku("002", "Struktur Data", "Budi"));
        perpus.tambahBuku(new Buku("003", "Algoritma Dasar", "Citra"));

        perpus.tampilkanKoleksiBuku();

        System.out.println("\n--- Proses Peminjaman ---");
        perpus.pinjamBuku("A001", "001");
        perpus.pinjamBuku("A002", "002");
        perpus.pinjamBuku("A003", "004");
        perpus.pinjamBuku("A004", "001");

        perpus.tampilkanKoleksiBuku();

        perpus.tampilkanLogPeminjaman();
    }
}
