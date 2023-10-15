package src.abw.updater;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Diese Klasse ist für das Aktualisieren von Sieve-Skriptdateien verantwortlich, die für die Verwaltung von
 * Abwesenheitsnachrichten auf einem Mailserver verwendet werden.
 */
public class Updater {
	private UpdaterDBManagement um;
	private PropertiesDateiEinlesen properties = new PropertiesDateiEinlesen();

	/**
	 * Erstellt eine neue Instanz des Updaters und initialisiert die Verbindung zur Datenbank.
	 *
	 * @throws IOException Wenn ein Fehler beim Lesen der Konfigurationsdateien auftritt.
	 */
	public Updater() throws IOException {
		um = UpdaterDBManagement.getInstance();
		abwesenheitenPruefen();
	}

	/**
	 * Überprüft die Abwesenheiten in der Datenbank und aktualisiert die Sieve-Skriptdateien entsprechend.
	 *
	 * @throws FileNotFoundException Wenn eine Sieve-Skriptdatei nicht gefunden wird.
	 * @throws IOException           Wenn ein allgemeiner Fehler beim Lesen oder Schreiben von Dateien auftritt.
	 */
	private void abwesenheitenPruefen() throws FileNotFoundException, IOException {
		List<Sieve> sieves = um.selectSieves();
		for (Sieve sieve : sieves) {
			String msn = sieve.getMsn();
			String abwtxt = sieve.getAbwtxt();
			speichernSieveDateien(msn, abwtxt);
		}
		loeschenSieveDateien();
	}

	/**
	 * Speichert ein Sieve-Skript in einer Datei im Dateisystem.
	 *
	 * @param nutzername Der Nutzername des Mitarbeiters.
	 * @param abwtxt     Das Sieve-Skript, das gespeichert werden soll.
	 * @throws FileNotFoundException Wenn die Datei nicht gefunden wird.
	 * @throws IOException           Wenn ein Fehler beim Schreiben der Datei auftritt.
	 */
	private void speichernSieveDateien(String msn, String abwtxt) throws FileNotFoundException, IOException {
		properties.propertiesEinlesen();
		File baseDirectory = new File(properties.dateienVerzeichnis);

		if (baseDirectory.exists() && baseDirectory.isDirectory()) {
			File userDirectory = new File(baseDirectory, msn);

			File sieveFile = new File(userDirectory, "dovecot.sieve");
			try (FileWriter fileWriter = new FileWriter(sieveFile)) {
				fileWriter.write(abwtxt);
				System.out.println("Gespeichert in: " + sieveFile.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Fehler beim Speichern: ");
			}
		}
	}

	/**
	 * Löscht Sieve-Skriptdateien, die nicht mehr in der Datenbank vorhanden sind.
	 *
	 * @throws FileNotFoundException Wenn eine Sieve-Skriptdatei nicht gefunden wird.
	 * @throws IOException           Wenn ein Fehler beim Löschen von Dateien auftritt.
	 */
	private void loeschenSieveDateien() throws FileNotFoundException, IOException {
		List<Sieve> sieves = um.selectSieves();
		properties.propertiesEinlesen();

		File baseDirectory = new File(properties.dateienVerzeichnis);
		if (baseDirectory.exists() && baseDirectory.isDirectory()) {
			File[] subfolders = baseDirectory.listFiles(File::isDirectory);

			if (subfolders != null) {
				for (File subfolder : subfolders) {
					String folderName = subfolder.getName();
					boolean folderExistsInSieves = false;
					for (Sieve sieve : sieves) {
						if (sieve.getMsn().equals(folderName)) {
							folderExistsInSieves = true;
							break;
						}
					}
					if (!folderExistsInSieves) {
						String fileName = "dovecot.sieve";
						File file = new File(subfolder, fileName);

						if (file.exists() && file.isFile()) {
							if (file.delete()) {
								System.out.println("Gelöscht: " + subfolder);
							} else {
								System.err.println("Fehler beim Löschen: " + subfolder);
							}
						}
					}
				}
			}
		}
	}
}
