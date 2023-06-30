package uebung_dateien;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 3c Baue ein Programm mit folgendem Menü/Funktionen:
public class ProgrammMitMenuFunktionen {
	// 1
	static List<Person> personen;
	


	public static void main(String[] args) {
		personen = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Menü: ");
			System.out.println("1) Personen erzeugen");
			System.out.println("2) Personen in Datei speichern");
			System.out.println("3) Personen aus Datei laden");
			System.out.println("4) Personen auslesen");
			System.out.println("0) Programm beenden");

			int auswahl = scanner.nextInt();

			switch (auswahl) {
			case 1:
				personenErzeugen(scanner);
				break;
			case 2:
				personenSpeichern();
				break;
			case 3:
				personenLaden();
				break;
			case 4:
				personenAuslesen();
				break;
			case 0:
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Fehler");
			}
		}
	}

	// 1
	public static void personenErzeugen(Scanner scanner) {
		boolean allePersonenErzeugt = false;

		System.out.println("Wie viel Personen erzeugen? ");
		int anzahlPersonen = scanner.nextInt();

		while (!allePersonenErzeugt) {
			for (int i = 1; i <= anzahlPersonen; i++) {

				// Nachname, Vorname, Wohnort
				getStr();
				
				System.out.println("Weitere Personen erzeugen?(j/n)");

				String antwort = scanner.next();
				if (antwort.equals("n")) {
					allePersonenErzeugt = true;
				}
			}
		}
	}

	// 1
	// Alter erzeugen
	public static int alter() {
		int alter = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Alter: ");
		while (!scanner.hasNextInt() || (alter = scanner.nextInt()) < 0 || alter > 100) {
			System.out.println("Fehlerhafter Alter!");
			scanner.nextLine();
			System.out.print("Alter: ");
		}
		return alter;
	}

	// Nachname, Vorname, Wohnort erzeugen
	public static String[] getStr() {
		Scanner scanner = new Scanner(System.in);
		String[] str = new String[3];
		str[0] = validateInput(scanner, "Nachname");
		str[1] = validateInput(scanner, "Vorname");
		str[2] = validateInput(scanner, "Wohnort");
		personen.add(new Person(personen.size() + 1, str[0], str[1], alter(), str[2]));
		return str;
	}

	private static String validateInput(Scanner scanner, String feldName) {
		String input;
		do {
			System.out.print(feldName + ": ");
			input = scanner.nextLine().trim();
			if (!input.matches("^[a-zA-Z]+$")) {
				System.out.println("Fehlerhafte Eingabe! ");
				input = "";
			}
		} while (input.isEmpty());
		input = input.substring(0, 1).toUpperCase() + input.substring(1);
		return input;
	}

	// 2
	public static void personenSpeichern() {
		Person2.writeToFile(personen);
	}

	// 3
	public static void personenLaden() {
		personen = Person2.readFromFile();
	}

	// 4
	public static void personenAuslesen() {
		for(Person pers : personen) {
			pers.ausgeben();			
		}
	}
}
