package textdatei;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class SmallDatabase {
	// 3
	private String vorname, nachname, wohnort;
	private int alter;

	public SmallDatabase(String vorname, String nachname, int alter, String wohnort) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.alter = alter;
		this.wohnort = wohnort;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public int getAlter() {
		return alter;
	}

	public String getWohnort() {
		return wohnort;
	}

	public static void main(String[] args) {
		// 3a
		SmallDatabase[] person = { 
				new SmallDatabase("Manuel", "Neuer", 33, "München"),
				new SmallDatabase("Thomas", "Müller", 35, "München"),
				new SmallDatabase("Michael", "Ballack", 50, "Berlin"),
				new SmallDatabase("Lukas", "Podolski", 37, "Köln"),
				new SmallDatabase("Noch", "Jemand", 30, "Hamburg") 
				};
		
		try {
			String smallDatabase = "/home/jach/workspace/Java_Uebung/src/smallDatabase.txt";
			File file = new File(smallDatabase);
			FileWriter writer = new FileWriter(file);
			
			for (SmallDatabase p : person) {
				writer.write(p.getVorname() + ", " + p.getNachname() + ", " + p.getAlter() + ", " + p.getWohnort()+"\n");
			}
			writer.close();
			System.out.println("Die Textdatei wurde erfolgreich erstellt!\n");
			// 3b - von Datei auslesen
			try {
				List<String> readFile = Files.readAllLines(Paths.get(smallDatabase));
				readFile.forEach(System.out::println);
			}catch(IOException e) {
				System.out.println("Fehler beim Auslesen der Textdatei" + e.getMessage());
			}
		} catch (IOException e) {
			System.out.println("Fehler beim Erstellen der Textdatei" + e.getMessage());
		}
	}
}
