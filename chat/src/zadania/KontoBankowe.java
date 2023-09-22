package zadania;

public class KontoBankowe {
    private String numerKonta;
    private double saldo, wplata, wyplata;

    public KontoBankowe (String numerKonta, double saldo, double wplata, double wyplata) {
        this.numerKonta = numerKonta;
        this.wplata = wplata;
        this.wyplata = wyplata;
        this.saldo = saldo + wplata - wyplata;
    }

    public String toString() {
        return (", jej nr konta to " + numerKonta + ", a saldo wynosi " + String.format("%.2f", saldo) + " PLN.");
    }

 /*    public static void main(String[] args) {
        KontoBankowe Konto1 = new KontoBankowe("01234567890", 0, 5000, 3000);

        Wlasciciel Wlasciciel1 = new Wlasciciel("Tomasz", "Wolski", 
            157);

        System.out.println(Wlasciciel1);
        System.out.println(Konto1);
    } */
}