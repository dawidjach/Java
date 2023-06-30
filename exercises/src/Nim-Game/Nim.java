package nim;

import java.util.Scanner;

public class Nim {
    private String computer = "Computer";
    private String player = "Player";
    int startAmount; // Streichhölzer auf dem Tisch (am Anfang des Spieles)
    private int maxPick; // max Streichhölzer wegzunehmen
    private int remain; // verbleibende Streichhölzer auf dem Tisch
    private final int minPick = 1;
    Scanner scanner = new Scanner(System.in);
    int pick = 0;
    private String playerName;
    
    public Nim(int startAmount, int maxPick) {
        this.startAmount = startAmount;
        this.maxPick = maxPick;
        this.remain = startAmount;
    }

    private void game() {	
        boolean isPlayerTurn = true;

        do {
            playerName = isPlayerTurn ? player : computer;
            System.out.printf("\n%s, pick number (%d-%d): ", playerName, minPick, Math.min(maxPick, remain));

            if (isPlayerTurn) {
                pick = playerPick();
            } else {
                pick = bestPick();
                System.out.println(pick);
            }

            remain -= pick;
            System.out.printf("Remain: %d\n", remain);
            
            whoWin();
            
            isPlayerTurn = !isPlayerTurn;
        } while (remain > 0);
        scanner.close();
    }
    
    private void whoWin() {
    	if (remain <= 1) {
            System.out.printf("\n%s won!\n", playerName);
            System.exit(0);
        }
    }
    
    // Player
    private int playerPick() {
    	int pick = scanner.nextInt();
    	 if (pick < minPick || pick > Math.min(maxPick, remain)) {
             System.out.println("Invalid pick!");
         }
    	 return pick;
    }
    
    // Computer
    private int bestPick() {
        int r = remain % (maxPick + 1);
        int pick;

        if (r == 0) {
            pick = (int) (Math.random() * maxPick) + 1;
        } else {
            pick = r - 1;
            if (pick < minPick || pick > maxPick) {
                pick = (int) (Math.random() * maxPick) + 1;
            }
        }
        return pick;
    }

    public static void main(String[] args) {
        Nim newGame = new Nim(20, 3);
        newGame.game();
    }
}

