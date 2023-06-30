package textdatei;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextFileCreator {
	
    public static void main(String[] args) {
    	// 1, 2
        Scanner scanner = new Scanner(System.in);

        System.out.println("Gib den Pfad und den Namen der Textdatei ein: ");
        String pfadUndName = scanner.nextLine();

        System.out.println("Gib den Text für die Textdatei ein: ");
        String text = scanner.nextLine();

        try {
            File datei = new File(pfadUndName);
            FileWriter writer = new FileWriter(datei);
            writer.write(text);
            writer.close();
            System.out.println("Die Textdatei wurde erfolgreich erstellt: " + pfadUndName);
            System.out.println("Der Text lautet: " + text);
        } catch (IOException e) {
            System.out.println("Fehler beim Erstellen der Textdatei: " + e.getMessage());
        }
    }
}

