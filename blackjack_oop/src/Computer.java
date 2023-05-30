package SiebzehnUndVier.copy;

public class Computer extends Spieler {
    public Computer(Karten karten) {
        super(karten);
		setName("Computerspieler");
    }
    
    @Override
    public int spiel() {
        if (getPunkte() < 17) {
            System.out.print("\nComputerspieler möchte eine neue Karte: +");
            karteZiehen();
            
        } else {
            System.out.println("\nComputerspieler möchte keine neue Karte.");
        }
        return punkte;
    }
}

