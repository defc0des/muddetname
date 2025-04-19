import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MuddetnameHesaplayici {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Kullanıcıdan verileri al
        System.out.print("Ceza süresi (yıl): ");
        int cezaYili = scanner.nextInt();

        System.out.print("Cezaevine giriş tarihi (yyyy-MM-dd): ");
        String girisTarihiStr = scanner.next();

        System.out.println("İnfaz oranını seçin:");
        System.out.println("1 - 3/4 (0.75) [Kasten adam öldürme, cinsel suçlar vs.]");
        System.out.println("2 - 2/3 (0.666...) [Uyuşturucu, örgüt suçları vs.]");
        System.out.println("3 - 1/2 (0.5) [Genel suçlar]");
        System.out.print("Seçiminiz (1/2/3): ");
        int secim = scanner.nextInt();

        double infazOrani;

        switch (secim) {
            case 1:
                infazOrani = 0.75;
                break;
            case 2:
                infazOrani = 2.0 / 3.0;
                break;
            case 3:
                infazOrani = 0.5;
                break;
            default:
                System.out.println("Geçersiz seçim, varsayılan 3/4 alınıyor.");
                infazOrani = 0.75;
        }

        // Tarih formatı Gün-Ay-Yıl şeklinde girilmesi için düzenlenmiştir.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate girisTarihi = LocalDate.parse(girisTarihiStr, formatter);

        // Toplam ceza günü
        int toplamCezaGunu = cezaYili * 365; // Daha doğru hesap için artık yıllar eklenebilir

        // Koşullu salıverilme günü
        int infazGunu = (int) (toplamCezaGunu * infazOrani);

        // Tarih hesaplamaları
        LocalDate kosulluSaliverilmeTarihi = girisTarihi.plusDays(infazGunu);
        LocalDate tamCezaBitisTarihi = girisTarihi.plusDays(toplamCezaGunu);

        // Sonuçları yazdır
        System.out.println("\n--- HESAPLAMA SONUCU ---");
        System.out.println("Toplam Ceza Gün: " + toplamCezaGunu);
        System.out.println("İnfaz Oranı: " + infazOrani);
        System.out.println("İnfazda Kalınacak Gün: " + infazGunu);
        System.out.println("Koşullu Salıverilme Tarihi: " + kosulluSaliverilmeTarihi.format(formatter));
        System.out.println("Tam Ceza Bitiş Tarihi (indirimsiz): " + tamCezaBitisTarihi.format(formatter));
    }
}
