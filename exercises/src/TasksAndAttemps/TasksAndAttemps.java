import java.util.Random;
import java.util.Scanner;

public class TasksAndAttemps {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.printf("How many tasks? ");
		int tasks = scanner.nextInt();
		System.out.printf("How many attemps? ");
		int attemps = scanner.nextInt();
		Game(scanner, tasks, attemps);
		
	}

	private static void Game(Scanner scanner, int tasks, int attemps) {
		for (int i = 1; i <= tasks; i++) {
			int x = random(10, 99);
			int y = random(10, 99);
			int result = x + y;

			for (int j = 1; j <= attemps; j++) {
				System.out.println(String.format("T%d A%d: %d + %d", i, j, x, y));
				int userAnswer = scanner.nextInt();

				if (userAnswer == result) {
					System.out.println(true);
					break;
				}
				if (j < attemps) {
					System.out.println("Try one more time!");
				} else {
					System.out.println("Answer is: " + result);
					i = tasks;
					j = attemps;
					System.out.println("Game over");
				}
			}
			
		}
		System.out.println("This game was awesome!");
	}

	private static int random(int i, int j) {
		return (int) (Math.random() * (j - i));
	}

}
