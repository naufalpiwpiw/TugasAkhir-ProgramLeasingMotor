package tugas;
//NAUFAL NABIL ARUFA
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program {
    static ArrayList<String> namaP = new ArrayList<>();
    static ArrayList<String> nmrP = new ArrayList<>();
    static ArrayList<String> alamatP = new ArrayList<>();
    static ArrayList<String> Motor = new ArrayList<>();
    static ArrayList<Double> harga = new ArrayList<>();
    static ArrayList<Double> cicilan = new ArrayList<>();
    static ArrayList<Double> tenor = new ArrayList<>();
    static ArrayList<Double> uangmuka = new ArrayList<>();
    static boolean isRunning = true;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    static void showMenu() throws IOException {
        System.out.println("\n============ MENU ============");
        System.out.println("1. Isi Data");
        System.out.println("2. Lihat Laporan");
        System.out.println("3. Koreksi / Edit Data");
        System.out.println("4. Hapus Data");
        System.out.println("5. Keluar");
        System.out.print("PILIH MENU > ");
        int Menu = Integer.parseInt(input.readLine());

        switch (Menu) {
            case 1:
                insert();break;
            case 2:
                laporan();break;
            case 3:
                editData();break;
            case 4:
                deleteData();break;
            case 5:
                isRunning = false;
                System.out.println("Program Selesai.");
                break;
            default: System.out.println("Pilihan tidak valid.");
        }    }

    static void alldata() {
    System.out.println("========= SEMUA DATA =========");
    if (namaP.isEmpty()) {
            System.out.println("Belum ada data.");
        } else {
            for (int i = 0; i < namaP.size(); i++) {
                System.out.printf(" %-1d. Nama: %-14s|| Alamat: %-11s|| No.HP: %-11s|| Motor: %-23s|| Harga Rp. %-10.2f|| DP Rp. %-8.2f|| \n",
                        i + 1, 
                        namaP.get(i),
                        alamatP.get(i),
                        nmrP.get(i),
                        Motor.get(i), 
                        harga.get(i), 
                        uangmuka.get(i)
                        );}}}
    static void laporan() throws IOException {
        System.out.println("\n===== LAPORAN LEASING MOTOR =====");
        System.out.println("====== HONDA PEMALANG 2024 ======");
        if (namaP.isEmpty()) {
            System.out.println("Belum ada data.");
        } else {
        int totalData = namaP.size();
        int dataPerHalaman = 3;
        int totalHalaman = (int) Math.ceil((double) totalData / dataPerHalaman);
        double totharga =0,totdp=0,totcicil=0,totbunga=0,totTcicil=0,totwaktu=0;

        for (int halaman = 0; halaman < totalHalaman; halaman++) {
            double subtotharga =0,subtotdp=0,subtotcicil=0,subtotbunga=0,subtotTcicil=0,subtotwaktu=0;
            System.out.printf("\nHalaman %d/%d\n", halaman + 1, totalHalaman);
            System.out.printf("+----+----------------+----------------+---------------+--------------------------+----------------+---------------+------------------------------------------------------+\n");
            System.out.printf("|    |                |                |               |                          |                |               |                       TENOR (Bulan)                  |\n");
            System.out.printf("| No |      Nama      |     Alamat     |   Nomor HP    |          Motor           |  Harga Motor   |   Uang Muka   |------------------------------------------------------|\n");
            System.out.printf("|    |                |                |               |                          |                |               |    Cicilan    | Bunga  |  Total Cicilan |   Waktu    |\n");
            System.out.printf("+----+----------------+----------------+---------------+--------------------------+----------------+---------------+------------------------------------------------------+\n");
            
            for (int i = halaman * dataPerHalaman; i < Math.min((halaman + 1) * dataPerHalaman, totalData); i++) {
                double bunga=2.0*(tenor.get(i)/12.0);
                double totaltenor=cicilan.get(i)*tenor.get(i);
                subtotharga += harga.get(i);
                subtotdp += uangmuka.get(i);
                subtotcicil += cicilan.get(i);
                subtotbunga += bunga;
                subtotTcicil += totaltenor;
                subtotwaktu += tenor.get(i);
                System.out.printf("| %-2d | %-14s | %-14s | %-13s | %-24s | Rp.%-10.2f | Rp.%-8.2f | Rp.%-5.2f | %-4.1f%%  | Rp.%-8.2f | %-2s Bulan |\n",
                        i + 1, 
                        namaP.get(i),
                        alamatP.get(i),
                        nmrP.get(i),
                        Motor.get(i), 
                        harga.get(i), 
                        uangmuka.get(i), 
                        cicilan.get(i),
                        bunga,
                        totaltenor,
                        tenor.get(i));
            }
            System.out.printf("+----+----------------+----------------+---------------+--------------------------+----------------+---------------+------------------------------------------------------+\n");
            System.out.printf("|    |      SUBTOTAL HALAMAN INI                                                  | Rp.%-10.2f | Rp.%-10.2f | Rp.%-8.2f | %-4.1f%%  | Rp.%-10.2f | %-2s Bulan |\n"
                    ,subtotharga,subtotdp,subtotcicil,subtotbunga,subtotTcicil,subtotwaktu);
            System.out.printf("+----+----------------------------------------------------------------------------+----------------+---------------+------------------------------------------------------+\n");
             
            totharga += subtotharga;
            totdp += subtotdp;
            totcicil += subtotcicil;
            totbunga += subtotbunga;
            totTcicil += subtotTcicil;
            totwaktu += subtotwaktu;
            if (halaman < totalHalaman - 1) {
                System.out.print("Tekan ENTER untuk ke halaman berikutnya...");
                String p=input.readLine();
            }}
            System.out.printf("|    |      TOTAL KESELURUHAN                                                     | Rp.%-10.2f | Rp.%-8.2f | Rp.%-5.2f | %-4.1f%%  | Rp.%-10.2f | %-2s Bulan |\n"
            ,totharga,totdp,totcicil,totbunga,totTcicil,totwaktu);
            System.out.printf("+----+----------------------------------------------------------------------------+----------------+---------------+------------------------------------------------------+\n");
            System.out.println("Data sudah Habis.");
        }   }

    static void insert() throws IOException {
        boolean ulang = true;
        while (ulang) {
            System.out.println("======== INPUT DATA ========");
            System.out.print("Masukkan Nama   : ");
            String nama = input.readLine();
            System.out.print("Masukkan Alamat : ");
            String alamat = input.readLine();
            System.out.print("Masukkan No. HP : ");
            String nmr = input.readLine();
            System.out.println("Pilih Motor yang ingin disewa."
                    + "\n1. Honda Beat Street"
                    + "\n2. Honda CBR 150 ABS"
                    + "\n3. Honda Genio CBS Plus"
                    + "\n4. Honda PCX 160 CBS"
                    + "\n5. Honda Scoopy Sporty"
                    + "\n6. Honda Vario 125 CBS Plus"
                    + "\n7. Honda Vario 160 CBS Plus");
              System.out.print("Pilih Motor (1-7) : ");
            int kode = Integer.parseInt(input.readLine());
            String motor = Motor(kode);
            double tunai = harga(kode);

            System.out.println("Nama Motor        : " + motor);
            System.out.printf("Harga Motor       : Rp.%-10.2f\n",tunai);
            System.out.print("Masukkan Uang Muka(DP) : Rp.");
            double muka = Double.parseDouble(input.readLine());
            if (muka >= tunai) {
            System.out.println("Uang muka tidak boleh lebih besar atau sama dengan harga motor.");
            continue;           }
            System.out.print("Masukkan Tenor (Bulan) : ");
            int tenorP = Integer.parseInt(input.readLine());
            double cicil = tenor(kode, tenorP, muka);
            System.out.printf("Cicilan per Bulan      : Rp.%-8.2f\n",cicil);
            double bunga=2.0*(tenorP/12.0);
            System.out.printf("Bunga yang diperoleh   : %-2.1f%%\n",bunga);

            // Menyimpan data ke ArrayList
            namaP.add(nama);
            alamatP.add(alamat);
            nmrP.add(nmr);
            Motor.add(motor);
            uangmuka.add(muka);
            harga.add(tunai);
            tenor.add((double) tenorP);
            cicilan.add(cicil);

            System.out.println("Data berhasil ditambahkan.");
            System.out.print("Tambah Data lagi? [y/t]: ");
            String choice = input.readLine().toLowerCase();
            if (!choice.equals("y")) {
                ulang = false;
            }       }    }

    static void editData() throws IOException {
        boolean ulang = true;
        while (ulang) {
        alldata();
        System.out.print("Pilih nomor data yang ingin di-edit: ");
        int index = Integer.parseInt(input.readLine()) - 1;

        if (index >= 0 && index < namaP.size()) {
            System.out.print("Masukkan Nama Baru  : ");
            namaP.set(index, input.readLine());
            System.out.print("Masukkan Alamat Baru: ");
            alamatP.set(index, input.readLine());
            System.out.print("Masukkan No. HP Baru: ");
            nmrP.set(index, input.readLine());
            System.out.println("Pilih Motor yang ingin disewa."
                    + "\n1. Honda Beat Street"
                    + "\n2. Honda CBR 150 ABS"
                    + "\n3. Honda Genio CBS Plus"
                    + "\n4. Honda PCX 160 CBS"
                    + "\n5. Honda Scoopy Sporty"
                    + "\n6. Honda Vario 125 CBS Plus"
                    + "\n7. Honda Vario 160 CBS Plus");
            System.out.print("Pilih Motor Baru (1-7): ");
            int kode = Integer.parseInt(input.readLine());
            Motor.set(index, Motor(kode));
            harga.set(index, harga(kode));
            String motor = Motor(kode);
            double tunai = harga(kode);
            System.out.println("Nama Motor            : " + motor);
            System.out.printf("Harga Motor           : Rp.%-10.2f\n",tunai);
            System.out.print("Masukkan Uang Muka(DP) Baru  : Rp.");
            double mukabaru=Double.parseDouble(input.readLine());
            uangmuka.set(index,mukabaru);
            System.out.print("Masukkan Tenor (Bulan) Baru  : ");
            int tenorP = Integer.parseInt(input.readLine());
            tenor.set(index, (double) tenorP);
             double cicil = tenor(kode, tenorP, mukabaru);         
            System.out.printf("Cicilan per Bulan            : Rp.%-8.2f\n",cicil);
            cicilan.set(index, cicil);
            double bunga=2.0*(tenorP/12.0);
            System.out.printf("Bunga yang diperoleh         : %-2.1f%%\n",bunga);
            System.out.println("Data berhasil di-edit.");
        } else {
            System.out.println("Data tidak ditemukan."); }
        System.out.print("Edit Data lagi? [y/t]: ");
            String choice = input.readLine().toLowerCase();
            if (!choice.equals("y")) {
                ulang = false;
            }       }    }

    static void deleteData() throws IOException {
        boolean ulang = true;
        while (ulang) {
        alldata();
        System.out.print("Pilih nomor data yang ingin dihapus: ");
        int index = Integer.parseInt(input.readLine()) - 1;

        if (index >= 0 && index < namaP.size()) {
            namaP.remove(index);
            alamatP.remove(index);
            nmrP.remove(index);
            Motor.remove(index);
            harga.remove(index);
            cicilan.remove(index);
            uangmuka.remove(index);
            tenor.remove(index);
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Data tidak ditemukan.");
        }
        System.out.print("Hapus Data lagi? [y/t]: ");
            String choice = input.readLine().toLowerCase();
            if (!choice.equals("y")) {
                ulang = false;
            }    }    }

    static double harga(int kode) {
        switch (kode) {
            case 1: return 19880000;
            case 2: return 42610000;
            case 3: return 20820000;
            case 4: return 34410000;
            case 5: return 23480000;
            case 6: return 24360000;
            case 7: return 28500000;
            default: return 0;
        }    }

    static double tenor(int kode, int tenorP, double muka) {
    double hargaMotor = harga(kode); 
    double bunga = 0.02; 
    double sisaPembayaran = hargaMotor - muka; 
    int durasiTenor = tenorP; 
    double totalBunga = sisaPembayaran * bunga * (durasiTenor / 12.0); 
    double totalPembayaran = sisaPembayaran + totalBunga; 
    return totalPembayaran / durasiTenor; }
     
    static String Motor(int kode) {
        switch (kode) {
            case 1: return "Honda Beat Street";
            case 2: return "Honda CBR 150 ABS";
            case 3: return "Honda Genio CBS Plus";
            case 4: return "Honda PCX 160 CBS";
            case 5: return "Honda Scoopy Sporty";
            case 6: return "Honda Vario 125 CBS Plus";
            case 7: return "Honda Vario 160 CBS Plus";
            default: return "Tidak diketahui";
        }    }

    public static void main(String[] args) throws IOException {
    System.out.println("==============================");
    System.out.println("======= LEASING MOTOR ========");
    System.out.println("== 0083-NAUFAL_NABIL_ARUFA ===");
    System.out.println("==============================");
        do {
            showMenu();
        } while (isRunning);    }}
