package exercise001;

import java.util.Scanner;

public class Zufallszahl {

	public static void main(String[] args) {
		int random = random(1, 100);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Write a number(1-100) or 0 to close: ");
		int output = scanner.nextInt();

		while (output != 0 && output != random) {
			if (output > random) {
				System.out.println("To much");
			} else {
				System.out.println("To small");
			}
			System.out.println("Write a number(1-100) or 0 to close: ");
			output = scanner.nextInt();
		}
		if(output == 0) {
			System.out.println("Game over");
		}else{
			System.out.println("Win!");
		}
	}

	private static int random(int i, int j) {
		//return (int) ((Math.random()*(j-i+1))+i); //(max-min+1)+min
		return (int) (Math.random()*(j-i)); //(max-min)
	}
}
