package zadania;

public class Wlasciciel {
    private String imie, nazwisko;
    private int numerIdentyfikacyjny;

    public Wlasciciel (String imie, String nazwisko, int numerIdentyfikacyjny) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerIdentyfikacyjny = numerIdentyfikacyjny;
    }

    public String toString() {
        return ("Nr id: " + numerIdentyfikacyjny + "\nWlasciciel: " + imie + " " +
                nazwisko);
    }
}
