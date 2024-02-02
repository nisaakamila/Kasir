import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
//import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
//import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors




public class Struk {
    public static List<Produk> listproduk = new ArrayList<Produk>();
    public static double total = 0;
    public void readProduk(String path){
        List<Produk> data = new ArrayList<Produk>();
        try {
            File myObj = new File("produk.txt");
            Scanner myReader = new Scanner(myObj);
            String[] line;
            while (myReader.hasNextLine()) {
                String baca = myReader.nextLine();
                line = baca.split(",");
                Produk k = new Produk();
                k.kode = line[0];
                k.nama = line[1];
                k.harga =  Double.parseDouble(line[2]);
                data.add(k);
                //System.out.println(baca); 
                
            }
        myReader.close(); 
        }
        catch (FileNotFoundException e) {
            // TODO: handle exception
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        this.listproduk = data;
    }

    public static Transaksi buatTransaksi(String kodeJumlah){
        Transaksi t = new Transaksi();
        String[] kj = kodeJumlah.split(" ");
        t.produk = cariProdukByKode(kj[0]);
        t.kuantiti = Integer.parseInt(kj[1]);
        return t;
    }

    private static Produk cariProdukByKode(String kode){
        
        for(Produk k: listproduk){
            if(k.kode.equals(kode)){
                return k;
            }
        }
        return null;
    }

    public static void printStruk(List<Transaksi> listTransaksi, double total, double bayar){
        for(Transaksi i: listTransaksi){
            System.out.println(i.produk.nama +" "+ i.kuantiti +" "+ i.produk.harga +" "+ (i.produk.harga*i.kuantiti));
        }
        total = total;
        bayar = bayar;
        double kembalian = bayar - total;
        System.out.println("Total: "+total);
        System.out.println("Pembayaran: "+bayar);
        System.out.println("Kembalian: "+kembalian);
    }

    public static void main(String[] args) {
        System.out.println("oke");
        Struk struk = new Struk();
        struk.readProduk("produk.txt");
        System.out.println("Produk-produk toko:");
        for(Produk p: struk.listproduk){
            System.out.println(p.kode +" "+ p.nama +" "+p.harga);
        }
    /*===============Langkah Selanjutnya=================*/
    Scanner sc = new Scanner(System.in);
    ArrayList <Transaksi> listransaksi = new ArrayList<Transaksi>();
    System.out.println("Masukkan kode barang: ");
    String input = "";
    while (!input.equalsIgnoreCase("end")) {//ketika nilai variabel input tidak sama dengan kata "end", maka:
        input = sc.nextLine();
        if (!input.equalsIgnoreCase("end")){
            Transaksi t = buatTransaksi(input);
            total += t.produk.harga * t.kuantiti;
            listransaksi.add(t);
        }
        
    }
    System.out.println("Total: "+total);
    System.out.println("Masukkan Pembayaran: ");
    double bayar = Double.parseDouble(sc.nextLine());
    //double kembalian = bayar - total;
    
    printStruk(listransaksi, total, bayar);



            
    }

        /*
         * 1. baca file produk.txt
         * 2. tampilkan daftar produk
         * 3. melakukan transaksi
         * 4. Print Struk teralampir(.txt)
         */
        
}
