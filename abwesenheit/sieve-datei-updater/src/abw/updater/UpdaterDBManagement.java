package src.abw.updater;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Diese Klasse ist für die Verwaltung der Verbindung zur Datenbank und das Abrufen von Abwesenheitsinformationen
 * aus der Datenbank verantwortlich.
 */
public class UpdaterDBManagement {
	private Connection con;
	private static UpdaterDBManagement instance;
	PropertiesDateiEinlesen properties = new PropertiesDateiEinlesen();

    /**
     * Erstellt eine neue Instanz des UpdaterDBManagement und initialisiert die Verbindung zur Datenbank.
     *
     * @throws IOException Wenn ein Fehler beim Lesen der Konfigurationsdateien auftritt.
     */
	private UpdaterDBManagement() throws IOException {
		connectToDatabase();
		if (con != null) {
			System.out.println("Verbindung mit Datenbank erfolgreich.");
		} else {
			System.out.println("Verbindung mit Datenbank fehlerhaft.");
			JOptionPane.showMessageDialog(null, "Verbindung mit Datenbank fehlerhaft.");
		}
	}

    /**
     * Stellt eine Verbindung zur Datenbank her.
     *
     * @throws IOException Wenn ein Fehler beim Lesen der Konfigurationsdateien auftritt.
     */
	private void connectToDatabase() throws IOException {
		String url = null;
		String user = null;
		String pass = null;

//		String path = Updater.class.getResource("/abw/conf/remoteConfig").getPath();
		properties.propertiesEinlesen();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(properties.remoteConfig));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Database URL:")) {
					url = line.substring("Database URL:".length()).trim();
				} else if (line.startsWith("Root-Username:")) {
					user = line.substring("Root-Username:".length()).trim();
				} else if (line.startsWith("Root-Password:")) {
					pass = line.substring("Root-Password:".length()).trim();
				} else if (line.startsWith("Extra-Nutzer-Username:")) {
					user = line.substring("Extra-Nutzer-Username:".length()).trim();
				} else if (line.startsWith("Extra-Nutzer-Password:")) {
					pass = line.substring("Extra-Nutzer-Password:".length()).trim();
				}
			}
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Connected");
			reader.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Disconnected");
			JOptionPane.showMessageDialog(null, "Disconnected: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

    /**
     * Erstellt und gibt ein PreparedStatement für die angegebene Abfrage zurück.
     *
     * @param insertQuery Die Abfrage, für die ein PreparedStatement erstellt werden soll.
     * @return Ein PreparedStatement-Objekt.
     * @throws SQLException Wenn ein SQL-Fehler auftritt.
     */
	public PreparedStatement preparedStatement(String insertQuery) throws SQLException {
		return con.prepareStatement(insertQuery);
	}

    /**
     * Ruft Sieve-Informationen aus der Datenbank für aktuelle Abwesenheiten ab.
     *
     * @return Eine Liste von Sieve-Objekten mit Nutzernamen und Sieve-Skripten.
     */
	public List<Sieve> selectSieves() {
		List<Sieve> sieves = new ArrayList<>();
		Date heutigesDatum = new Date();
		try {
			String selectQuery = "SELECT msn, abwtxt FROM abwesenheit  WHERE ? BETWEEN startdatum AND enddatum";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ps.setDate(1, new java.sql.Date(heutigesDatum.getTime()));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String nutzername = rs.getString("msn");
				String abwtxt = rs.getString("abwtxt");
				Sieve sieve = new Sieve();
				sieve.setMsn(nutzername);
				sieve.setAbwtxt(abwtxt);
				sieves.add(sieve);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return sieves;
	}

    /**
     * Gibt eine Singleton-Instanz von UpdaterDBManagement zurück.
     *
     * @return Eine Instanz von UpdaterDBManagement.
     * @throws IOException Wenn ein Fehler beim Lesen der Konfigurationsdateien auftritt.
     */
	public static synchronized UpdaterDBManagement getInstance() throws IOException {
		if (instance == null) {
			instance = new UpdaterDBManagement();
		}
		return instance;
	}
}