package exercise001;

import java.util.Scanner;

public class ExampleMultipleGame {
	String player1 = "Player1";
	String player2 = "Player2";
	int a;
	int result = 0;

	public ExampleMultipleGame(int finish) {
		boolean player1Turn = true;
		String winner = "";
		
		while(result != finish) {
			String whoTurn = player1Turn ? player1 : player2;
			if (player1Turn) {
				player1Game();
			} else {
				player2Game();
			}
			player1Turn = !player1Turn;
			if(result == finish) {
				winner = whoTurn;
				System.out.println(winner + " won!");
			}
		}

	}

	private int player1Game() {
		Scanner scanner = new Scanner(System.in);
		System.out.print(player1 + ": ");
		a = scanner.nextInt();
		result += a;
		System.out.print("Result: " + result + "\n");
		return result;
	}

	private int player2Game() {
		Scanner scanner = new Scanner(System.in);
		System.out.print(player2 + ": ");
		a = scanner.nextInt();
		result += a;
		System.out.print("Result: " + result + "\n");
		return result;
	}

	public static void main(String[] args) {
		ExampleMultipleGame exmpl = new ExampleMultipleGame(10);
	}

}
