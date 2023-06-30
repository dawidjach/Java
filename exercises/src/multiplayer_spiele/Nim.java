package exercise001;

import java.util.Scanner;

public class Nim {
	String computer = "Computer";
	String player = "Player";
	int maxPick; // max Streichhölzer wegzunehmen
	int remain; // (verbleibende) Streichhölzer auf dem Tisch
	//int r = remain % (maxPick + 1); // zeigt sogenannte Looser-Position
	//int random = random(1, maxPick);
	private final int minPick = 1;

	public Nim(int remain, int maxPick) {
		this.maxPick = maxPick;
		this.remain = remain;

		boolean isPlayerTurn = true;
		String winner = "";

		while(this.remain != 0) {
			String whoTurn = isPlayerTurn ? player : computer;
			if (isPlayerTurn) {
				playerGame();
			} else {
				bestPick();
			}
			isPlayerTurn = !isPlayerTurn;
			if(this.remain == 0) {
				winner = whoTurn;
				System.out.println(winner + " won!");
				break;
			}
			while (this.remain == 2) {
				maxPick = remain;
			}
			while (this.remain == 1) {
				maxPick = remain;
			}
		}
	}

	// player turn
	private int playerGame() {
		int userPick;

		do {
			Scanner scanner = new Scanner(System.in);
			System.out.printf(String.format("%s picks number(%d-%d): ", player, minPick, maxPick));
			userPick = scanner.nextInt();
			remain -= userPick;
			if (userPick > maxPick) {
				System.out.println("To much!");
			} else if (userPick < minPick) {
				System.out.println("To few");
			} else {
				System.out.println("Remain: " + remain);
			}
		} while (!(userPick >= minPick && userPick <= maxPick && remain > userPick));
		return remain;
	}

	// computer turn
	private int bestPick() {
		System.out.println("Remain: " + remain);
		System.out.print(String.format("%s picks number(1-%d): ", computer, maxPick));
		// int startAmount;
		int r = remain % (maxPick + 1);

		if (r == 0) {
			// remain = maxPick + 1;
			remain -= maxPick;
			// startAmount = remain;
			System.out.println(maxPick + "\nRemain: " + remain);
			while (remain == 1) {
				System.out.print(String.format("\nGame over! %s won!", computer));
				break;
			}
		} else if (r == 1) {
			int random = random(1, maxPick);
			int thisRandom = random;
			// startAmount = remain;
			remain -= thisRandom;
			System.out.print(thisRandom + "\nRemain: " + remain);
			while (remain == 1) {
				System.out.print(String.format("\nGame over! %s won!", computer));
				break;
			}
		} else {
			int rMinusOne = r - 1;
			remain = rMinusOne;
			// startAmount = remain;
			System.out.print(rMinusOne + "\nRemain: " + remain);
			while (remain == 1) {
				System.out.print(String.format("\nGame over! %s won!", computer));
				break;
			}
		}
		return remain;
	}

	private static int random(int i, int maxPick) {
		return (int) (Math.random() * (maxPick - i + 1) * maxPick);
	}

//	public String toString() {
//		String startInfo = String.format("Start amount = %d\nMax pick = %d", startAmount, maxPick);
//		return (startInfo);
//	}

	public static void main(String[] args) {
		Nim newGame = new Nim(20, 3);
		System.out.println(newGame);
	}

}
