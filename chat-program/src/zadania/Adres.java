package zadania;

public class Adres {
    private String miejscowosc, kodPocztowy, nazwaUlicy;
    private int nrDomu;

    public Adres(String miejscowosc, String kodPocztowy, String nazwaUlicy, int nrDomu) {
        this.miejscowosc = miejscowosc;
        this.kodPocztowy = kodPocztowy;
        this.nazwaUlicy = nazwaUlicy;
        this.nrDomu = nrDomu;
    }

    public String getMiejscowosc() {
        return this.miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getkodPocztowy() {
        return this.kodPocztowy;
    }

    public void setkodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getnazwaUlicy() {
        return this.nazwaUlicy;
    }

    public void setnazwaUlicy(String nazwaUlicy) {
        this.nazwaUlicy = nazwaUlicy;
    }

    public int getNrDomu() {
        return this.nrDomu;
    }

    public void setNrDomu(int nrDomu) {
        this.nrDomu = nrDomu;
    }

    public String toString() {
        return(nazwaUlicy + " " + nrDomu + " w miejscowosci " + kodPocztowy + " " + miejscowosc);
    }
}