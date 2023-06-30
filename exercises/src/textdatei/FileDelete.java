package textdatei;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileDelete {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Schreib den Pfad um Datei zu löschen: ");
		String output = scanner.nextLine();
		
		try {
			File file = new File(output);
			if(file.delete()) {
				System.out.println("In den Papierkorb geworfen!");
			} else {
				System.out.println("Die Datei kann nicht in den Papierkorb geworfen werden.");
			}
		} catch(Exception e) {
			System.out.println("Error");;
		}

	}

}
