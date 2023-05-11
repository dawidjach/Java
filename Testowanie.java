public class Testowanie {
    private String tytul;
    private String rezyser;
    private double cenaBiletu;

    public Testowanie() {
        this("<nienazwany film>", "<brak rezysera>", 20.0); // 1
    }

    public Testowanie(String tytul) {
        this(tytul, "<brak rezysera>", 20.0); // 2
    }

    public Testowanie(String tytul, String rezyser) {
        this(tytul, rezyser, 20.0); // 3
    }

    public Testowanie(String tytul, String rezyser, double cenaBiletu) { // 4
        this.tytul = tytul;
        this.rezyser = rezyser;
        this.cenaBiletu = cenaBiletu;
    }

    public static void main(String[] args) {
        Testowanie film1 = new Testowanie();
        System.out.println("Tytuł: " + film1.tytul + ", Reżyser: " + film1.rezyser + ", Cena biletu: " + film1.cenaBiletu);

        Testowanie film2 = new Testowanie("Incepcja");
        System.out.println("Tytuł: " + film2.tytul + ", Reżyser: " + film2.rezyser + ", Cena biletu: " + film2.cenaBiletu);

        Testowanie film3 = new Testowanie("Matrix", "Lana i Lilly Wachowski");
        System.out.println("Tytuł: " + film3.tytul + ", Reżyser: " + film3.rezyser + ", Cena biletu: " + film3.cenaBiletu);

        Testowanie film4 = new Testowanie("Avengers: Endgame", "Anthony i Joe Russo", 25.0);
        System.out.println("Tytuł: " + film4.tytul + ", Reżyser: " + film4.rezyser + ", Cena biletu: " + film4.cenaBiletu);
    }
}
