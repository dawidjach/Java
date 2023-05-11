package array;

import java.util.ArrayList;
import java.util.List;

public class tausendLeute {
    public static void main(String[] args) {
        int anzahlPersonen = 10_000;
        int[] kreis = new int[anzahlPersonen];
        int aktuellePersonIndex = 0;


        for (int i = 0; i < anzahlPersonen; i++) {
            kreis[i] = i + 1;
        }

        while (anzahlPersonen > 2) {
            for (int i = 0; i < 2; i++) {
                aktuellePersonIndex = (aktuellePersonIndex + 1) % anzahlPersonen;
            }
            for (int i = aktuellePersonIndex; i < anzahlPersonen - 1; i++) {
                kreis[i] = kreis[i + 1];
            }
            anzahlPersonen--;
        }

        System.out.println("Die letzten beiden Personen im Kreis sind: " + kreis[0] + " und " + kreis[1]);
    }
}
