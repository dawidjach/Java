import java.util.Random;

public class Abrufcode {
    public static void main(String[] args) {
        String kennung = "SQ";
        Random rand = new Random();
        int zufallsziffern;
        int pruefzahl = 56;
        int summe = 0;
        int abrufcode = (summe + pruefzahl) % 97;

        System.out.print(kennung);
        for (int i = 0; i < 8; i++) {
            zufallsziffern = rand.nextInt(9) + 1;
            summe = summe + zufallsziffern;
            System.out.print(zufallsziffern);
        }
        System.out.print(pruefzahl);
        System.out.println();

        if (abrufcode == 1) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
