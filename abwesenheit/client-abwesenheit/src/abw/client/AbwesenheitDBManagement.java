package src.abw.client;

import javax.swing.*;
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

public class AbwesenheitDBManagement extends JFrame {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private static AbwesenheitDBManagement instance;
	PropertiesDateiEinlesen properties = new PropertiesDateiEinlesen();

	private AbwesenheitDBManagement() throws IOException {
		connectToDatabase();
		if (con != null) {
			System.out.println("Verbindung mit Datenbank erfolgreich.");
		} else {
			System.out.println("Verbindung mit Datenbank fehlerhaft.");
			JOptionPane.showMessageDialog(null, "Verbindung mit Datenbank fehlerhaft.");
		}
	}

	private void connectToDatabase() throws IOException {
		String url = null;
		String user = null;
		String pass = null;

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

	public PreparedStatement preparedStatement(String insertQuery) throws SQLException {
		return con.prepareStatement(insertQuery);
	}

	private static void rowsAffected(PreparedStatement ps) throws SQLException {
		@SuppressWarnings("unused")
		int rowsAffected = ps.executeUpdate();
		ps.close();
	}

	public void newAbwesenheitInsert(Abwesenheit abwesenheit) {
		try {
			int id = getErsteFreieID();
			abwesenheit.setId(id);
			String insertQuery = "INSERT INTO abwesenheit (id, nutzername, abwgrund, startdatum, enddatum, abwtxt, vollname, msn, mail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(insertQuery);
			ps.setInt(1, id);
			ps.setString(2, abwesenheit.getMitarbeiter().getNutzername());
			ps.setString(3, abwesenheit.getAbwesenheitsgrund().getName());

			// Convert auf java.sql.Date
			java.sql.Date startdatum = new java.sql.Date(abwesenheit.getStartdatum().getTime());
			java.sql.Date enddatum = new java.sql.Date(abwesenheit.getEnddatum().getTime());
			ps.setDate(4, startdatum);
			ps.setDate(5, enddatum);
			
//			Mitarbeiter mitarbeiter = new Mitarbeiter();
//			Abwesenheitsgrund abwgrund = new Abwesenheitsgrund();
			ps.setString(6, abwesenheit.getAbwesenheitsgrund().getAbwtxt());
			ps.setString(7, abwesenheit.getMitarbeiter().getVollname());
			ps.setString(8, abwesenheit.getMitarbeiter().getMsn());
			ps.setString(9, abwesenheit.getMitarbeiter().getMail());
			rowsAffected(ps);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public int getErsteFreieID() {
		int ersteFreieID = 1;
		try {
			String selectQuery = "SELECT id FROM abwesenheit ORDER BY id ASC";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			// Erste freie ID auswählen:
			while (rs.next()) {
				int currentID = rs.getInt("id");
				if (currentID != ersteFreieID) {
					break;
				}
				ersteFreieID++;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return ersteFreieID;
	}

	public List<Abwesenheit> selectAbwesenheiten() {
		List<Abwesenheit> abwesenheiten = new ArrayList<>();
		try {
			String selectQuery = "SELECT id, nutzername, abwgrund, startdatum, enddatum FROM abwesenheit ORDER BY enddatum ASC";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nutzername = rs.getString("nutzername");
				String abwesenheitsgrund = rs.getString("abwgrund");
				Date startdatum = rs.getDate("startdatum");
				Date enddatum = rs.getDate("enddatum");
				Mitarbeiter mitarbeiter = new Mitarbeiter();
				mitarbeiter.setNutzername(nutzername);
				Abwesenheitsgrund abwgrund = new Abwesenheitsgrund();
				abwgrund.setName(abwesenheitsgrund);
				Abwesenheit abwesenheit = new Abwesenheit(id, mitarbeiter, abwgrund, startdatum, enddatum);
				abwesenheiten.add(abwesenheit);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return abwesenheiten;
	}
	
	public Abwesenheit selectAbwesenheitenNachId(int recordId) {
		Abwesenheit abwesenheit = new Abwesenheit();
		try {
			String selectQuery = "SELECT id, nutzername, abwgrund, startdatum, enddatum FROM abwesenheit WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ps.setInt(1, recordId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nutzername = rs.getString("nutzername");
				String abwesenheitsgrund = rs.getString("abwgrund");
				Date startdatum = rs.getDate("startdatum");
				Date enddatum = rs.getDate("enddatum");
				Mitarbeiter mitarbeiter = new Mitarbeiter();
				mitarbeiter.setNutzername(nutzername);
				Abwesenheitsgrund abwgrund = new Abwesenheitsgrund();
				abwgrund.setName(abwesenheitsgrund);
				abwesenheit = new Abwesenheit(recordId, mitarbeiter, abwgrund, startdatum, enddatum);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return abwesenheit;
	}

	// ID auf Objekt Mitarbeiter konvertieren
	public Mitarbeiter getNutzername(int id) {
		try {
			String selectQuery = "SELECT nutzername FROM mitarbeiter WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nutzername = rs.getString("nutzername");
				return new Mitarbeiter(id, nutzername);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null; // wenn nicht gefunden
	}

	public void updateAbwesenheit(Abwesenheit abwesenheit) {
		try {
			String updateQuery = "UPDATE abwesenheit SET id = ?, nutzername = ?, abwgrund = ?, startdatum = ?, enddatum = ?, abwtxt = ?, vollname = ?, msn = ?, mail = ? WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.setInt(1, abwesenheit.getId());
			ps.setString(2, abwesenheit.getMitarbeiter().getNutzername());
			ps.setString(3, abwesenheit.getAbwesenheitsgrund().getName());
			ps.setDate(4, new java.sql.Date(abwesenheit.getStartdatum().getTime()));
			ps.setDate(5, new java.sql.Date(abwesenheit.getEnddatum().getTime()));
			ps.setString(6, abwesenheit.getAbwesenheitsgrund().getAbwtxt());
			ps.setString(7, abwesenheit.getMitarbeiter().getVollname());
			ps.setString(8, abwesenheit.getMitarbeiter().getMsn());
			ps.setString(9, abwesenheit.getMitarbeiter().getMail());
			ps.setInt(10, abwesenheit.getId());
			ps.executeUpdate();
//			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteAbwesenheit(int recordId) {
		try {
			String deleteQuery = "DELETE FROM abwesenheit WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(deleteQuery);
			ps.setInt(1, recordId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static synchronized AbwesenheitDBManagement getInstance() throws IOException {
		if (instance == null) {
			instance = new AbwesenheitDBManagement();
		}
		return instance;
	}
}
