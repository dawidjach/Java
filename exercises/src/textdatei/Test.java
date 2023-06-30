package uebung_dateien;

import java.io.*;
import java.util.*;

public class Test {
	static List<Person> personen = new ArrayList<>(); // eine geeignete Datenstruktur

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\nMenü:");
			System.out.println("1) Personen erzeugen");
			System.out.println("2) Personen in Datei speichern");
			System.out.println("3) Personen aus Datei laden");
			System.out.println("4) Alle Personen ausgeben");
			System.out.println("5) Programm beenden");

			int auswahl = sc.nextInt();

			switch (auswahl) {
			case 1:
				personenErzeugen(sc);
				break;
			case 2:
				personenInDateiSpeichern(sc);
				break;
			case 3:
				personenAusDateiLaden(sc);
				break;
			case 4:
				allePersonenAusgeben();
				break;
			case 5:
				sc.close();
				System.exit(0);
			default:
				System.out.println("Ungültige Eingabe!");
			}
		}
	}

	public static void personenErzeugen(Scanner sc) {
		System.out.println("Wie viele Personen möchten Sie erzeugen?");
		int anzahl = sc.nextInt();

		for (int i = 0; i < anzahl; i++) {
			System.out.println("\nPerson " + (i + 1));
			System.out.println("Name: ");
			String name = sc.next();
			System.out.println("Alter: ");
			int alter = sc.nextInt();
			System.out.println("Wohnort: ");
			String wohnort = sc.next();

			Person person = new Person(name, wohnort, alter, wohnort);
			personen.add(person);
		}

		System.out.println("Personen wurden erfolgreich erzeugt!");
	}

	public static void personenInDateiSpeichern(Scanner sc) {
		try {
			System.out.println("Welchen Dateinamen möchten Sie verwenden?");
			String dateiname = sc.next();

			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(dateiname));

			for (Person person : personen) {
				output.writeObject(person);
			}

			output.close();
			System.out.println("Personen wurden erfolgreich in Datei gespeichert!");

		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben der Datei: " + e.getMessage());
		}
	}

	public static void personenAusDateiLaden(Scanner sc) {
		try {
			System.out.println("Welchen Dateinamen möchten Sie laden?");
			String dateiname = sc.next();

			ObjectInputStream input = new ObjectInputStream(new FileInputStream(dateiname));

			personen.clear(); // alle bisherigen Daten in der Datenstruktur zuvor löschen

			while (true) {
				try {
					Person person = (Person) input.readObject();
					personen.add(person);
				} catch (EOFException e) {
					// EOFException tritt auf, wenn das Ende der Datei erreicht wurde
					break;
				}
			}

			input.close();
			System.out.println("Personen wurden erfolgreich aus Datei geladen!");

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Fehler beim Lesen der Datei: " + e.getMessage());
		}
	}

	public static void allePersonenAusgeben() {
		for (Person person : personen) {
			System.out.println(person.toString());
		}
	}

}
