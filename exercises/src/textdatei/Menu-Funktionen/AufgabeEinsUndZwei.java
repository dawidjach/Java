package uebung_dateien;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AufgabeEinsUndZwei {
	static String pfadUndName;
	static String text;
	
	
	public static void scanner() {
		Scanner scanner = new Scanner(System.in);
		
		// /home/jach/workspace/Java_Uebung/src/person.txt
		System.out.println("Gib den Pfad und den Namen der Textdatei ein: ");
		pfadUndName = scanner.nextLine();
		
		System.out.println("Gib den Text für die Textdatei ein: ");
		text = scanner.nextLine();
		
		scanner.close();
	}


	public static void fileWrite() {
		try {
			File file = new File(pfadUndName);
			FileWriter writer = new FileWriter(file);
			BufferedWriter buffWriter = new BufferedWriter(writer);
			buffWriter.write(text);
			System.out.println("Die Textdatei wurde erfolgreich erstellt: " + pfadUndName);
			buffWriter.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Erstellen der Textdatei: " + e.getMessage());
		}
	}

	public static void fileReader() {
		try {
			File file = new File(pfadUndName);
			FileReader reader = new FileReader(file);
			BufferedReader buffReader = new BufferedReader(reader);
			while ((text = buffReader.readLine()) != null) {
				System.out.println("Die Textdatei wurde erfolgreich eingelesen und latet: \n" + text);
				buffReader.close();
				break;
			}
		} catch (IOException e) {
			System.out.println("Fehler beim Einlesen der Textdatei! " + e.getMessage());
		}
	}
	public static void main(String[] args) {
		// 1, 2
		scanner();
		fileWrite();
		fileReader();
	}
}
