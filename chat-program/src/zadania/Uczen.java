package zadania;

import java.util.Arrays;

public class Uczen {
    String imie;
    int[] ocena;
    double sredniaOcen;

    public void pewnyUczen(String nowyUczen) {
        imie = nowyUczen;
    }

    public int[] ocena(int[] noweOceny) {
        int[] ocena = { 3,4,3,5,3,2,5,4 };
        ocena = noweOceny;
        return noweOceny;
    }

    public double sredniaOcen(double sredniaOcen) {
        return sredniaOcen;
    }

    public String toString() {
        return("Imie ucznia: " + imie + "\n" + "oceny: " + ocena);
    }


    public static void main(String[] args) {
        Uczen pewnyUczen = new Uczen();
        pewnyUczen.pewnyUczen("Jasio"); // klasa Uczen

        Przedmioty pewnyPrzedmiot = new Przedmioty();   // klasa Przedmioty

        System.out.println(pewnyUczen);
        System.out.println(Arrays.toString(pewnyPrzedmiot.przedmioty));
        System.out.println(pewnyPrzedmiot.przedmioty[0]);

    }
}