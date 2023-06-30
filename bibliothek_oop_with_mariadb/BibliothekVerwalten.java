package jdbcmariadb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliothekVerwalten {
	Scanner scanner = new Scanner(System.in);;
	private BibliothekManagement bm;
	public int output;
	private Outputs op;

	// um Connected zu sein
	public BibliothekVerwalten(Connection con) throws SQLException {
		bm = new BibliothekManagement(con);
		this.output = 0;
		op = new Outputs(con, this);
	}

	public int getOutput() {
		return output;
	}

	// Menu (1.Buch, 2.Autor ...) vom Int to String konvertieren um in die Konsole "Buch" statt "1" ausgeben.
	// Ergebnis:
	// ___________________________________________________________
	// Buch
	// -----------------------------------------------------------
	// 1.Anzeigen 2.Erzeugen 3.Aktualisieren 4.Löschen 5.Exit
	// ...........................................................
	public String convertMenuFromIntToString(int output) {
		switch (output) {
		case 1:
			return "Buch";
		case 2:
			return "Autor";
		case 3:
			return "Verlag";
		case 4:
			return "Kategorie";
		case 0:
			return "Exit";
		default:
			return "Fehlerhafte Eingabe! ";
		}
	}

	// wenn Menu ausgewählt
	public void untermenu() throws SQLException {
		while (true) {
			menu();

			output = scanner.nextInt();
			scanner.nextLine();

			switch (output) {
			case 1:
				buchMenu();
				break;
			case 2:
				autorMenu();
				break;
			case 3:
				verlagMenu();
				break;
			case 4:
				katMenu();
				break;
			case 0:
				return;
			default:
				System.out.println("Fehlerhafte Eingabe! ");
			}
		}
	}

	public void menu() {
		op.menuOutput();
	}

	// Optionen für alle Untermenüs
	private void optionen() {
		op.optionenOutput();
	}

	// 1 Buch
	private void buchMenu() {
		while (true) {
			optionen();

			int output = scanner.nextInt();
			scanner.nextLine();

			switch (output) {
			case 1:
				bm.selectBuecher(); // Bücher anzeigen
				break;
			case 2:
				newBuchErzeugen();
				break;
			case 3:
				buchAktualisieren();
				break;
			case 4:
				buchLoeschen();
				break;
			case 0:
				return;
			default:
				System.out.println("Fehlerhafte Eingabe! ");
			}
		}
	}

	private void newBuchErzeugen() {
		Buch buch = new Buch();
		List<Autor> autoren;
		List<Verlag> verlage;
		List<Kategorie> kategorien;

		// Autor-ID aus Liste wählen
		autoren = bm.selectAutoren();

		// evtl.mehrere Autoren hinzufügen
		boolean addAnotherAutor = true;
		while (addAnotherAutor) {
			System.out.print("Wählen Sie ID des Autors: ");
			int outputAutorID = scanner.nextInt();
			scanner.nextLine();
			for (Autor newAutor : autoren) {
				if (newAutor.getAutorID() == outputAutorID) {
					Autor autor = newAutor;
					buch.getAutoren().add(autor);
					System.out.printf("%s %s %s\n\n", autor.getAutorID(), autor.getVorname(), autor.getNachname());
					break;
				}
			}
			System.out.print("Möchten Sie einen Autor hinzufügen?(j/n): ");
			String addNextAutor = scanner.nextLine();
			if (addNextAutor.equalsIgnoreCase("n")) {
				addAnotherAutor = false;
			}
		}

		// Verlag-ID aus Liste wählen
		verlage = bm.selectVerlage();
		System.out.print("Wählen Sie ID des Verlags:  ");
		int outputVerlagID = scanner.nextInt();
		scanner.nextLine();
		for (Verlag newVerlag : verlage) {
			if (newVerlag.getVerlagID() == outputVerlagID) {
				Verlag verlag = newVerlag;
				buch.setVerlag(verlag);
				System.out.printf("%s %s\n\n", verlag.getVerlagID(), verlag.getVerlagName());
				break;
			}
		}

		System.out.print("Titel: ");
		String titel = scanner.nextLine().trim();
		buch.setTitel(titel);

		// Kategorie-ID aus Liste wählen
		kategorien = bm.selectKategorien();

		// evtl.mehrere Autoren hinzufügen
		boolean addAnotherKat = true;
		while (addAnotherKat) {
			System.out.print("Wählen Sie ID der Kategorie: ");
			int outputKatID = scanner.nextInt();
			scanner.nextLine();
			for (Kategorie newKat : kategorien) {
				if (newKat.getKatID() == outputKatID) {
					Kategorie kategorie = newKat;
					buch.getKategorien().add(kategorie);
					System.out.printf("%s %s\n\n", kategorie.getKatID(), kategorie.getKatName());
					break;
				}
			}
			System.out.print("Möchten Sie eine Kategorie hinzufügen?(j/n): ");
			String addNextKat = scanner.nextLine();
			if (addNextKat.equalsIgnoreCase("n")) {
				addAnotherKat = false;
			}
		}

		System.out.print("Erscheinungstermin: ");
		int jahr = scanner.nextInt();
		scanner.nextLine();
		buch.setJahr(jahr);

		System.out.print("ISBN: ");
		int isbn = scanner.nextInt();
		scanner.nextLine();
		buch.setIsbn(isbn);

		System.out.print("Version (Papier/Digital): ");
		String version = scanner.nextLine().trim();
		buch.setVersion(version);

		System.out.print("Seitenanzahl: ");
		int anzahlSeiten = scanner.nextInt();
		scanner.nextLine();
		buch.setAnzahlSeiten(anzahlSeiten);

		if (titel.isEmpty() || version.isEmpty()) {
			System.out.println("\nFehlerhafte Eingabe! Versuchen Sie nochmal.");
		} else {
			bm.newBuchInsert(buch);
		}
	}

	private void buchAktualisieren() {
		Kategorie kategorie;
		Autor autor;
		List<Buch> buecher;
		Buch buch = null;

		buecher = bm.selectBuecher();

		System.out.print("Geben Sie ID des Buchs ein:  ");
		int outputBuchID = scanner.nextInt();
		scanner.nextLine();

		boolean isIDinDatabase = false;

		for (Buch existingBuch : buecher) {
			if (existingBuch.getBuchID() == outputBuchID) {
				isIDinDatabase = true;
				break;
			}
		}

		if (!isIDinDatabase) {
			System.out.println("Kein Buch mit der ID gefunden.");
		} else {
			for (Buch newBuch : buecher) {
				if (newBuch.getBuchID() == outputBuchID) {
					buch = newBuch;
					System.out.printf("%s \n", buch.getBuchID());
					break;
				}
			}

			if (buch != null) {
				while (true) {
					op.buchAktualisierenOutput();

					int auswahl = scanner.nextInt();
					scanner.nextLine();

					switch (auswahl) {
					case 1:
						List<Verlag> verlage = new ArrayList<>();
						verlage = bm.selectVerlage();
						System.out.print("Wählen Sie ID des Verlags:  ");
						int outputVerlagID = scanner.nextInt();
						scanner.nextLine();
						for (Verlag newVerlag : verlage) {
							if (newVerlag.getVerlagID() == outputVerlagID) {
								buch.setVerlag(newVerlag);
								System.out.printf("%s %s\n\n", newVerlag.getVerlagID(), newVerlag.getVerlagName());
								break;
							}
						}
						break;
					case 2:
						System.out.print("Titel: ");
						String titel = scanner.nextLine().trim();
						buch.setTitel(titel);
						break;
					case 3:
						System.out.print("Erscheinungstermin: ");
						int jahr = scanner.nextInt();
						scanner.nextLine();
						buch.setJahr(jahr);
						break;
					case 4:
						System.out.print("ISBN: ");
						int isbn = scanner.nextInt();
						scanner.nextLine();
						buch.setIsbn(isbn);
						break;
					case 5:
						System.out.print("Version (Papier/Digital): ");
						String version = scanner.nextLine().trim();
						buch.setVersion(version);
						break;
					case 6:
						System.out.print("Seitenanzahl: ");
						int anzahlSeiten = scanner.nextInt();
						scanner.nextLine();
						buch.setAnzahlSeiten(anzahlSeiten);
						break;
					case 7:
						op.katAktualisierenCase7Output();
						int auswahl1 = scanner.nextInt();
						scanner.nextLine();
						switch (auswahl1) {
						case 1:
							kategorie = hilfsmethodeUmKategorieAktualisierenOderLoeschen();
							bm.updateBuchKategorie(buch, kategorie);
							break;
						case 2:
							kategorie = hilfsmethodeUmKategorieAktualisierenOderLoeschen();
							if (kategorie != null) {
								final Kategorie katToRemove = kategorie;
								buch.getKategorien()
										.removeIf(existingKat -> existingKat.getKatID() == katToRemove.getKatID());
								bm.deleteBuchKategorie(buch, kategorie);
							}
							break;
						case 0:
							break;
						default:
							System.out.println("Fehlerhafte Eingabe!");
							break;
						}
						break;
					case 8:
						op.buchAktualisierenCase8Output();
						int auswahl2 = scanner.nextInt();
						scanner.nextLine();
						switch (auswahl2) {
						case 1:
							autor = hilfsmethodeUmAutorAktualisierenOderLoeschen();
							bm.updateBuchAutor(buch, autor);
							break;
						case 2:
							autor = hilfsmethodeUmAutorAktualisierenOderLoeschen();
							if (autor != null) {
								final Autor autorToRemove = autor;
								buch.getAutoren().removeIf(
										existingAutor -> existingAutor.getAutorID() == autorToRemove.getAutorID());
								bm.deleteBuchAutor(buch, autor);
							}
							break;
						case 0:
							break;
						default:
							System.out.println("Fehlerhafte Eingabe!");
							break;
						}
						break;
					case 0:
						return;
					default:
						System.out.println("Fehlerhafte Eingabe! ");
						break;
					}
					bm.updateBuch(buch);
				}
			}
		}
	}

	private void buchLoeschen() {
		Buch buch = null;
		List<Buch> buecher;
		buecher = bm.selectBuecher();

		System.out.print("Geben Sie ID des Buchs ein:  ");
		int outputBuchID = scanner.nextInt();
		scanner.nextLine();

		for (Buch newBuch : buecher) {
			if (newBuch.getBuchID() == outputBuchID) {
				buch = newBuch;
				System.out.printf("%s \n", buch.getBuchID());
				break;
			}
		}
		if (buch != null) {
			System.out.println(
					"Das Löschen ist nicht rückgängig zu machen und wird Änderungen in der Datenbank verursachen.");
			System.out.println("Möchten Sie das Buch wirklich löschen?* (j/n): ");
			String output = scanner.nextLine();
			if (output.trim().equalsIgnoreCase("j")) {
				bm.deleteBuch(buch.getBuchID());
				System.out.println("Das Buch wurde gelöscht.");
			}
			while (output != "j") {
				break;
			}
		} else {
			System.out.println("Kein Buch mit der ID gefunden.");
		}
	}

	// 2 Autor
	private void autorMenu() {
		while (true) {
			optionen();

			int output = scanner.nextInt();
			scanner.nextLine();

			switch (output) {
			case 1:
				bm.selectAutoren(); // Autoren anzeigen
				break;
			case 2:
				newAutorErzeugen();
				break;
			case 3:
				autorAktualisieren();
				break;
			case 4:
				autorLoeschen();
				break;
			case 0:
				System.out.println("Programmende ");
				return;
			default:
				System.out.println("Fehlerhafte Eingabe! ");
			}
		}
	}

	private void newAutorErzeugen() {
		Autor autor = new Autor();
		System.out.print("Geben Sie den Vornamen ein: ");
		String vorname = scanner.nextLine();
		autor.setVorname(vorname);

		System.out.print("Geben Sie den Nachnamen ein: ");
		String nachname = scanner.nextLine();
		autor.setNachname(nachname);

		if (vorname.trim().isEmpty() || nachname.trim().isEmpty()) {
			System.out.println("Fehlerhafte Eingabe! Versuchen Sie nochmal.");
		} else {
			bm.newAutorInsert(autor);
		}
	}

	private Autor hilfsmethodeUmAutorAktualisierenOderLoeschen() {
		List<Autor> autoren;
		autoren = bm.selectAutoren();
		System.out.print("Wählen Sie ID des Autors:  ");
		int outputAutorID = scanner.nextInt();
		scanner.nextLine();
		Autor autor = null;
		for (Autor newAutor : autoren) {
			if (newAutor.getAutorID() == outputAutorID) {
				autor = newAutor;
				System.out.printf("%s %s %s\n\n", autor.getAutorID(), autor.getVorname(), autor.getNachname());
				break;
			}
		}
		return autor;
	}

	private void autorAktualisieren() {
		Autor autor = hilfsmethodeUmAutorAktualisierenOderLoeschen();

		if (autor != null) {
			System.out.print("Geben Sie den neuen Vornamen ein: ");
			String newVorname = scanner.nextLine();
			autor.setVorname(newVorname);

			System.out.print("Geben Sie den neuen Nachnamen ein: ");
			String newNachname = scanner.nextLine();
			autor.setNachname(newNachname);

			bm.updateAutor(autor);
			System.out.println("Der Autor wurde aktualisiert.");
		} else {
			System.out.println("Kein Autor mit der ID gefunden.");
		}
	}

	private void autorLoeschen() {
		Autor autor = hilfsmethodeUmAutorAktualisierenOderLoeschen();

		if (autor != null) {
			System.out.println(
					"Das Löschen ist nicht rückgängig zu machen und wird Änderungen in der Datenbank verursachen.");
			System.out.println("Möchten Sie alle Referenzen des Autors löschen?* (j/n): ");
			String output = scanner.nextLine();
			if (output.trim().equalsIgnoreCase("j")) {
				bm.deleteAutor(autor);
				System.out.println("Der Autor wurde gelöscht.");
			}
			while (output != "j") {
				break;
			}
		} else {
			System.out.println("Kein Autor mit der ID gefunden.");
		}
	}

	// 3 Verlag
	private void verlagMenu() {
		while (true) {
			optionen();

			int output = scanner.nextInt();
			scanner.nextLine();

			switch (output) {
			case 1:
				bm.selectVerlage(); // Verlage anzeigen
				break;
			case 2:
				newVerlagErzeugen();
				break;
			case 3:
				verlagAktualisieren();
				break;
			case 4:
				verlagLoeschen();
				break;
			case 0:
				return;
			default:
				System.out.println("Fehlerhafte Eingabe! ");
			}
		}
	}

	private void newVerlagErzeugen() {
		Verlag verlag = new Verlag();
		System.out.print("Verlag Name: ");
		String verlagName = scanner.nextLine();
		verlag.setVerlagName(verlagName);

		if (verlagName.trim().isEmpty()) {
			System.out.println("Fehlerhafte Eingabe! Versuchen Sie nochmal.");
		} else {
			bm.newVerlagInsert(verlag);
		}
	}

	private Verlag hilfsmethodeUmVerlagAktualisierenOderLoeschen() {
		List<Verlag> verlage;
		verlage = bm.selectVerlage();
		System.out.print("Geben Sie ID des Verlags ein:  ");
		int outputVerlagID = scanner.nextInt();
		scanner.nextLine();
		Verlag verlag = null;
		for (Verlag newVerlag : verlage) {
			if (newVerlag.getVerlagID() == outputVerlagID) {
				verlag = newVerlag;
				System.out.printf("%s %s\n\n", verlag.getVerlagID(), verlag.getVerlagName());
				break;
			}
		}
		return verlag;
	}

	private void verlagAktualisieren() {
		Verlag verlag = hilfsmethodeUmVerlagAktualisierenOderLoeschen();

		if (verlag != null) {
			System.out.print("Geben Sie den neuen Namen ein: ");
			String verlagName = scanner.nextLine();
			verlag.setVerlagName(verlagName);

			bm.updateVerlag(verlag);
			System.out.println("Der Verlag wurde aktualisiert.");
		} else {
			System.out.println("Kein Verlag mit der ID gefunden.");
		}
	}

	private void verlagLoeschen() {
		Verlag verlag = hilfsmethodeUmVerlagAktualisierenOderLoeschen();

		if (verlag != null) {
			System.out.println(
					"Das Löschen ist nicht rückgängig zu machen und wird Änderungen in der Datenbank verursachen.");
			System.out.println("Möchten Sie alle Referenzen des Verlags löschen?* (j/n): ");
			String output = scanner.nextLine();
			if (output.trim().equalsIgnoreCase("j")) {
				bm.deleteVerlag(verlag);
				System.out.println("Der Verlag wurde gelöscht.");
			}
			while (output != "j") {
				break;
			}
		} else {
			System.out.println("Kein Verlag mit der ID gefunden.");
		}
	}

	// 4 Kategorie
	private void katMenu() {
		while (true) {
			optionen();

			int output = scanner.nextInt();
			scanner.nextLine();

			switch (output) {
			case 1:
				bm.selectKategorien(); // Kategorien anzeigen
				break;
			case 2:
				newKategorieErzeugen();
				break;
			case 3:
				kategorieAktualisieren();
				break;
			case 4:
				kategorieLoeschen();
				break;
			case 0:
				return;
			default:
				System.out.println("Fehlerhafte Eingabe! ");
			}
		}
	}

	private void newKategorieErzeugen() {
		Kategorie kategorie = new Kategorie();
		System.out.print("Kategorie Name: ");
		String katName = scanner.nextLine();
		kategorie.setKatName(katName);

		if (katName.trim().isEmpty()) {
			System.out.println("Fehlerhafte Eingabe! Versuchen Sie nochmal.");
		} else {
			bm.newKategorieInsert(kategorie);
		}
	}

	private Kategorie hilfsmethodeUmKategorieAktualisierenOderLoeschen() {
		List<Kategorie> kategorien;
		kategorien = bm.selectKategorien();
		System.out.print("Geben Sie ID der Kategorie ein:  ");
		int outputKatID = scanner.nextInt();
		scanner.nextLine();
		Kategorie kategorie = null;
		for (Kategorie newKat : kategorien) {
			if (newKat.getKatID() == outputKatID) {
				kategorie = newKat;
				System.out.printf("%s %s\n\n", kategorie.getKatID(), kategorie.getKatName());
				break;
			}
		}
		return kategorie;
	}

	private void kategorieAktualisieren() {
		Kategorie kategorie = hilfsmethodeUmKategorieAktualisierenOderLoeschen();

		if (kategorie != null) {
			System.out.print("Geben Sie den neuen Namen ein: ");
			String katName = scanner.nextLine();
			kategorie.setKatName(katName);

			bm.updateKategorie(kategorie);
			System.out.println("Die Kategorie wurde aktualisiert.");
		} else {
			System.out.println("Keine Kategorie mit der ID gefunden.");
		}
	}

	private void kategorieLoeschen() {
		Kategorie kategorie = hilfsmethodeUmKategorieAktualisierenOderLoeschen();

		if (kategorie != null) {
			System.out.println(
					"Das Löschen ist nicht rückgängig zu machen und wird Änderungen in der Datenbank verursachen.");
			System.out.println("Möchten Sie alle Referenzen der Kategorie löschen?* (j/n): ");
			String output = scanner.nextLine();
			if (output.trim().equalsIgnoreCase("j")) {
				bm.deleteKategorie(kategorie);
				System.out.println("Die Kategorie wurde gelöscht. ");
				}
				while (output != "j") {
					break;
				}
			} else {
				System.out.println("Keine Kategorie mit der ID gefunden. ");
			}
		}
	}

