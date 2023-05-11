package zadania;

public class Osoba {
    private String imie, nazwisko;
    private final int rokUrodzenia;
    private Adres adres;
    private KontoBankowe kontoBankowe;

    public Osoba(String imie, String nazwisko, int rokUrodzenia, Adres adres, KontoBankowe kontoBankowe) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rokUrodzenia = rokUrodzenia;
        this.adres = adres;
        this.kontoBankowe = kontoBankowe;
    }

    public Osoba(String imie, String nazwisko, int rokUrodzenia, Adres adres, String miejscowosc,
            String kodPocztowy, String nazwaUlicy, int nrDomu, String numerKonta, double saldo, double wplata,
            double wyplata) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rokUrodzenia = rokUrodzenia;
        this.adres = new Adres(miejscowosc, kodPocztowy, nazwaUlicy, nrDomu);
        this.kontoBankowe = new KontoBankowe(numerKonta, saldo, wplata, wyplata);
    }

    public String toString() {
        return ("Pan/Pani " + imie + " " + nazwisko + " urodzony/a w " + rokUrodzenia + " roku, mieszka pod adresem "
                + adres + kontoBankowe);
    }

    public static void main(String[] args) {
        Adres adres = new Adres("Krakow", "01-245", "Sienkiewicza", 15);
        KontoBankowe kontoBankowe = new KontoBankowe("123467890", 0, 5000, 2000);
        Osoba osoba1 = new Osoba("Marceli", "Janicki", 1950, adres, kontoBankowe);

        System.out.println(osoba1);
    }
}
