import java.util.Scanner;

public class RekapNilai {

    static final int SELESAI = -1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double total = 0;   // akumulator jumlah nilai sah
        int jumlah = 0;     // banyaknya nilai sah
        int nilai;

        System.out.println("===== REKAP NILAI KELAS =====");
        System.out.println("Ketik -1 kalau sudah selesai.");

        // do-while dipakai karena nilai pertama harus diminta dulu sebelum bisa dinilai.
        do {
            System.out.print("Nilai ke-" + (jumlah + 1) + " : ");
            nilai = input.nextInt();

            if (nilai == SELESAI) {
                continue; // lompat ke kondisi while -> loop berhenti
            }

            if (nilai < 0 || nilai > 100) {
                System.out.println("  ditolak — nilai harus 0..100");
                continue; // pencacah (jumlah) tidak naik
            }

            // Ladder if / else if / else sesuai tabel.
// EKSPERIMEN (langkah 3): urutan dibalik, >= 60 ditaruh paling atas.
// Hasil untuk nilai 85: Grade D (Kurang), harusnya B.
// Penyebab: ladder dicek dari atas dan berhenti di kondisi pertama yang benar.
// Urutan dikembalikan: kondisi paling ketat (>= 90) ditaruh paling atas.
            char grade;
            if (nilai >= 90) {
                grade = 'A';
            } else if (nilai >= 80) {
                grade = 'B';
            } else if (nilai >= 70) {
                grade = 'C';
            } else if (nilai >= 60) {
                grade = 'D';
            } else {
                grade = 'E';
            }

            String keterangan = switch (grade) {
                case 'A' -> "Sangat Baik";
                case 'B' -> "Baik";
                case 'C' -> "Cukup";
                case 'D' -> "Kurang";
                default -> "Tidak Lulus";
            };

            System.out.println("  Grade " + grade + " — " + keterangan);

            total += nilai;
            jumlah++;
        } while (nilai != SELESAI);

        System.out.println();

        // Jaga kasus tidak ada nilai sah (hindari pembagian dengan nol).
        if (jumlah == 0) {
            System.out.println("Tidak ada nilai sah yang dimasukkan.");
        } else {
            double rata = total / jumlah;
            String status = rata >= 60 ? "LULUS" : "TIDAK LULUS";

            System.out.println("Nilai sah   : " + jumlah);
            System.out.println("Rata-rata   : " + String.format("%.2f", rata));
            System.out.println("Status      : " + status);
        }

        input.close();
    }
}