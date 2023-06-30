package jdbcmariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BibliothekManagement {
	private Connection con;
	private BibliothekVerwalten bv;

	// Verbindung mit FirstSqlAccess
	public BibliothekManagement(Connection con) {
		this.con = con;
		if (con != null) {
			System.out.println("Verbindung mit Datenbank vom BibliothekManagement korrekt.");
		} else {
			System.out.println("Fehlerhafte Verbindung mit Datenbank vom BibltiothekManagement.");
		}
	}

	// einzige Managementmethoden verkleinern:
	// SQL-Abfrage (select, insert, update der Daten in der DB)
	public PreparedStatement preparedStatement(String insertQuery) throws SQLException {
		return con.prepareStatement(insertQuery);
	}

	private void rowsAffected(PreparedStatement ps) throws SQLException {
		int rowsAffected = ps.executeUpdate();
		ps.close();
		if (rowsAffected > 0) {
			System.out.println("Objekte in die DB erfolgreich eingelegt!\n");
		} else {
			System.out.println("Fehler beim Einlegen der Objekte in die DB!\n");
		}
	}

	// 1 Buch
	// Buch speichern(schreiben) in die DB
	public void newBuchInsert(Buch buch) {
		try {
			con.setAutoCommit(false);
			int buchID = getErsteFreieBuchID();
			buch.setBuchID(buchID);
			String insertQuery = "INSERT INTO Buch (buchID, verlagID, titel, jahr, isbn, version, anzahlSeiten) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(insertQuery);
			ps.setInt(1, buchID);
			ps.setInt(2, buch.getVerlag().getVerlagID()); // wenn 1-1 Beziehung
			ps.setString(3, buch.getTitel());
			ps.setInt(4, buch.getJahr());
			ps.setInt(5, buch.getIsbn());
			ps.setString(6, buch.getVersion());
			ps.setInt(7, buch.getAnzahlSeiten());
			// ps.executeUpdate(); // Fehler! update der Daten = Meldung über eingelegten Duplikaten in der DB
			rowsAffected(ps);
			ps.close();

			// autorInsert aufrufen (wenn 1-n Beziehung)
			for (Autor newAutor : buch.getAutoren()) {
				newBuchAutorInsert(buch, newAutor);
			}
			// katInsert aufrufen (wenn 1-n Beziehung)
			for (Kategorie newKat : buch.getKategorien()) {
				newBuchKategorieInsert(buch, newKat);
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	// letzte ID des Buchs in der DB prüfen
	public int getErsteFreieBuchID() {
		int ersteFreieID = 1;
		try {
			String selectQuery = "SELECT buchID FROM Buch ORDER BY buchID ASC";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();

			// erste freie ID raussuchen:
			while (rs.next()) {
				int currentID = rs.getInt("buchID");
				if (currentID != ersteFreieID) {
					break;
				}
				ersteFreieID++;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ersteFreieID;
	}

	// Buch in die Konsole anzeigen
	public List<Buch> selectBuecher() {
		List<Buch> buecher = new ArrayList<>();
		try {
			String selectQuery = "SELECT buchID, titel, jahr, isbn, version, anzahlSeiten, verlagID FROM Buch GROUP BY buchID";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int buchID = rs.getInt("buchID");
				int verlagID = rs.getInt("verlagID");
				String titel = rs.getString("titel");
				int jahr = rs.getInt("jahr");
				int isbn = rs.getInt("isbn");
				String version = rs.getString("version");
				int anzahlSeiten = rs.getInt("anzahlSeiten");

				Verlag verlag = getVerlag(verlagID); // verlagID auf Objekt Verlag konvertieren
				Buch buch = new Buch(buchID, verlag, titel, jahr, isbn, version, anzahlSeiten);
				List<Autor> autoren = selectAutorenForBuch(buchID);
				buch.setAutoren(autoren);
				List<Kategorie> kategorien = selectKategorien(buchID);
				buch.setKategorien(kategorien);

				buecher.add(buch);
			}

			// in die Konsole ausgeben
			System.out.printf("%-5s %-15s %-30s %-10s %-10s %-15s %-15s %-20s %-10s\n", "ID", "Verlag", "Titel", "Jahr",
					"ISBN", "Version", "Seitenanzahl", "Kategorie", "Autoren");
			System.out.println("-".repeat(166));
			for (Buch buch : buecher) {
				System.out.printf("%-5s %-15s %-30s %-10s %-10s %-15s %-16s", buch.getBuchID(),
						buch.getVerlag().getVerlagName(), buch.getTitel(), buch.getJahr(), buch.getIsbn(),
						buch.getVersion(), buch.getAnzahlSeiten());

				// Entsprechende Kategorien anzeigen
				List<Kategorie> kategorien = buch.getKategorien();

				// Kategorien nach Koma ausgeben:
				StringBuilder kategorienString = new StringBuilder();
				for (Kategorie kategorie : kategorien) {
					kategorienString.append(kategorie.getKatName()).append(", ");
				}
				kategorienString.setLength(Math.max(kategorienString.length() - 2, 0));
				System.out.printf("%-21s", kategorienString.toString());

				// Entsprechende Autoren anzeigen
				List<Autor> autoren = buch.getAutoren();

				// Autoren nach Koma ausgeben:
				StringBuilder autorenString = new StringBuilder();
				for (Autor autor : autoren) {
					autorenString.append(autor.getVorname()).append(" ").append(autor.getNachname()).append(", ");
				}
				autorenString.setLength(Math.max(autorenString.length() - 2, 0));
				System.out.printf("%-5s", autorenString.toString());
				System.out.println();
			}
			System.out.println(".".repeat(166));

			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buecher;
	}

	public List<Kategorie> selectKategorien(int buchID) {
		List<Kategorie> kategorien = new ArrayList<>();
		try {
			String selectQuery;
			if (buchID > 0) {
				selectQuery = "SELECT k.katID, k.katName " + "FROM Kategorie k "
						+ "JOIN BuchKategorie bk ON k.katID = bk.katID " + "WHERE bk.buchID = ?";
			} else {
				selectQuery = "SELECT * FROM Kategorie";
			}
			PreparedStatement ps = con.prepareStatement(selectQuery);
			if (buchID > 0) {
				ps.setInt(1, buchID);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int katID = rs.getInt("katID");
				String katName = rs.getString("katName");
				Kategorie kategorie = new Kategorie(katID, katName);
				kategorien.add(kategorie);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kategorien;
	}

	// verlagID auf Objekt Verlag konvertieren
	public Verlag getVerlag(int verlagID) {
		try {
			String selectQuery = "SELECT verlagName FROM Verlag WHERE verlagID = ?";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ps.setInt(1, verlagID);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String verlagName = rs.getString("verlagName");
				return new Verlag(verlagID, verlagName);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // wenn nicht gefunden
	}

	// Buch aktualisieren
	public void updateBuch(Buch buch) {
		// um `case 8 -> case 2 -> case 0` keine Update-Meldung gezeigt würde:
		boolean isUpdated = false;

		try {
			String updateQuery = "UPDATE Buch SET verlagID = ?, titel = ?, jahr = ?, isbn = ?, version = ?, anzahlSeiten = ? WHERE buchID = ?";
			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.setInt(1, buch.getVerlag().getVerlagID());
			ps.setString(2, buch.getTitel());
			ps.setInt(3, buch.getJahr());
			ps.setInt(4, buch.getIsbn());
			ps.setString(5, buch.getVersion());
			ps.setInt(6, buch.getAnzahlSeiten());
			ps.setInt(7, buch.getBuchID());
			ps.executeUpdate();
			isUpdated = true;
			int rowsAffected = ps.executeUpdate();

			if (isUpdated || rowsAffected > 0 || bv.output != 0) {
				System.out.println("Das Buch wurde aktualisiert.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBuch(int buchID) {
		try {
			String deleteAutorQuery = "DELETE FROM BuchAutor WHERE buchID = ?";
			PreparedStatement psAutor = con.prepareStatement(deleteAutorQuery);
			psAutor.setInt(1, buchID);
			psAutor.executeUpdate();

			String deleteKategorieQuery = "DELETE FROM BuchKategorie WHERE buchID = ?";
			PreparedStatement psKategorie = con.prepareStatement(deleteKategorieQuery);
			psKategorie.setInt(1, buchID);
			psKategorie.executeUpdate();

			String deleteBuchQuery = "DELETE FROM Buch WHERE buchID = ?";
			PreparedStatement psBuch = con.prepareStatement(deleteBuchQuery);
			psBuch.setInt(1, buchID);
			psBuch.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Autor
	// Autor in die DB speichern(schreiben)
	public void newAutorInsert(Autor autor) {
		try {
			int autorID = getErsteFreieAutorID();
			autor.setAutorID(autorID);
			String insertQuery = "INSERT INTO Autor (autorID, vorname, nachname) VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(insertQuery);
			ps.setInt(1, autor.getAutorID());
			ps.setString(2, autor.getVorname());
			ps.setString(3, autor.getNachname());
			rowsAffected(ps);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// letzte ID des Autors in der DB prüfen
	public int getErsteFreieAutorID() {
		int ersteFreieID = 1;
		try {
			String selectQuery = "SELECT autorID FROM Autor ORDER BY autorID ASC";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			// erste freie ID raussuchen:
			while (rs.next()) {
				int currentID = rs.getInt("autorID");
				if (currentID != ersteFreieID) {
					break;
				}
				ersteFreieID++;
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ersteFreieID;
	}

	// List<Autor>autoren aus der DB auslesen
	public List<Autor> selectAutoren() {
		List<Autor> autoren = new ArrayList<>();
		try {
			String selectQuery = "SELECT * FROM Autor GROUP BY autorID";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int autorID = rs.getInt("autorID");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				Autor autor = new Autor(autorID, vorname, nachname);
				autoren.add(autor);
			}

			// in die Konsole ausgeben:
			System.out.print("Autoren:\nID\tVorname\t\tNachname\n");
			System.out.print("-".repeat(35) + "\n");
			for (Autor autor : autoren) {
				System.out.println(autor.getAutorID() + "\t" + autor.getVorname() + "\t\t" + autor.getNachname());
			}
			System.out.print("-".repeat(35) + "\n");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autoren;
	}

	public List<Autor> selectAutorenForBuch(int buchID) {
		List<Autor> autoren = new ArrayList<>();
		try {
			String selectQuery;
			if (buchID > 0) {
				selectQuery = "SELECT a.autorID, a.vorname, a.nachname " + "FROM Autor a "
						+ "JOIN BuchAutor ba ON a.autorID = ba.autorID " + "WHERE ba.buchID = ?";
			} else {
				selectQuery = "SELECT * FROM Autor";
			}
			PreparedStatement ps = con.prepareStatement(selectQuery);
			if (buchID > 0) {
				ps.setInt(1, buchID);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int autorID = rs.getInt("autorID");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				Autor autor = new Autor(autorID, vorname, nachname);
				autoren.add(autor);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autoren;
	}

	public void updateAutor(Autor autor) {
		try {
			String updateQuery = "UPDATE Autor SET vorname = ?, nachname = ? WHERE autorID = ?";
			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.setString(1, autor.getVorname());
			ps.setString(2, autor.getNachname());
			ps.setInt(3, autor.getAutorID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAutor(Autor autor) {
		try {
			deleteBuchAutor(autor);

			String deleteQuery = "DELETE FROM Autor WHERE autorID = ?";
			PreparedStatement ps = con.prepareStatement(deleteQuery);
			ps.setInt(1, autor.getAutorID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Verlag
	public void newVerlagInsert(Verlag verlag) {
		try {
			int verlagID = getErsteFreieVerlagID();
			verlag.setVerlagID(verlagID);
			String insertQuery = "INSERT INTO Verlag (verlagID, verlagName) VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(insertQuery);
			ps.setInt(1, verlag.getVerlagID());
			ps.setString(2, verlag.getVerlagName());
			rowsAffected(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// letzte Verlag-ID prüfen ob schon existiert
	public int getErsteFreieVerlagID() {
		int ersteFreieID = 1;
		try {
			String selectQuery = "SELECT verlagID FROM Verlag ORDER BY verlagID ASC";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			// erste freie ID raussuchen:
			while (rs.next()) {
				int currentID = rs.getInt("verlagID");
				if (currentID != ersteFreieID) {
					break;
				}
				ersteFreieID++;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ersteFreieID;
	}

	// List<Verlag>verlage aus der DB in die Konsole auslesen/anzeigen
	public List<Verlag> selectVerlage() {
		List<Verlag> verlage = new ArrayList<>();
		try {
			String selectQuery = "SELECT * FROM Verlag GROUP BY verlagID";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int verlagID = rs.getInt("verlagID");
				String verlagName = rs.getString("verlagName");
				Verlag verlag = new Verlag(verlagID, verlagName);
				verlage.add(verlag);
			}

			// in die Konsole ausgeben:
			System.out.print("\nVerlage:\nID\tName\n");
			System.out.print("-".repeat(35) + "\n");
			for (Verlag verlag : verlage) {
				System.out.println(verlag.getVerlagID() + "\t" + verlag.getVerlagName());
			}
			System.out.print("-".repeat(35) + "\n");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verlage;
	}

	public void updateVerlag(Verlag verlag) {
		try {
			String updateQuery = "UPDATE Verlag SET verlagName = ? WHERE verlagID = ?";
			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.setString(1, verlag.getVerlagName());
			ps.setInt(2, verlag.getVerlagID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteVerlag(Verlag verlag) {
		deleteBuchVerlag(verlag.getVerlagID());
		try {
			String deleteQuery = "DELETE FROM Verlag WHERE verlagID = ?";
			PreparedStatement ps = con.prepareStatement(deleteQuery);
			ps.setInt(1, verlag.getVerlagID());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBuchVerlag(int verlagID) {
		try {
			String deleteBuchVerlagQuery = "DELETE FROM Buch WHERE verlagID = ?";
			PreparedStatement psBuchVerlag = con.prepareStatement(deleteBuchVerlagQuery);		
			psBuchVerlag.setInt(1, verlagID);
			psBuchVerlag.executeUpdate();
			psBuchVerlag.close();
		}  catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 4 Kategorie
	public void newKategorieInsert(Kategorie kategorie) {
		try {
			int katID = getErsteFreieKatID();
			kategorie.setKatID(katID);
			String insertQuery = "INSERT INTO Kategorie (katID, katName) VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(insertQuery);
			ps.setInt(1, kategorie.getKatID());
			ps.setString(2, kategorie.getKatName());
			rowsAffected(ps);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// letzte Kategorie-ID prüfen
	private int getErsteFreieKatID() {
		int ersteFreieID = 1;
		try {
			String selectQuery = "SELECT katID FROM Kategorie ORDER BY katID ASC";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			// erste freie ID raussuchen:
			while (rs.next()) {
				int currentID = rs.getInt("katID");
				if (currentID != ersteFreieID) {
					break;
				}
				ersteFreieID++;
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ersteFreieID;
	}

	// List<Kategorie>kategorien aus der DB auslesen
	public List<Kategorie> selectKategorien() {
		List<Kategorie> kategorien = new ArrayList<>();
		try {
			String selectQuery = "SELECT * FROM Kategorie GROUP BY katID";
			PreparedStatement ps = con.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int katID = rs.getInt("katID");
				String katName = rs.getString("katName");
				Kategorie kategorie = new Kategorie(katID, katName);
				kategorien.add(kategorie);
			}

			// in die Konsole ausgeben:
			System.out.print("\nKategorien:\nID\tName\n");
			System.out.print("-".repeat(35) + "\n");
			for (Kategorie kategorie : kategorien) {
				System.out.println(kategorie.getKatID() + "\t" + kategorie.getKatName());
			}
			System.out.print("-".repeat(35) + "\n");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kategorien;
	}

	public void updateKategorie(Kategorie kategorie) {
		try {
			String updateQuery = "UPDATE Kategorie SET katName = ? WHERE katID = ?";
			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.setString(1, kategorie.getKatName());
			ps.setInt(2, kategorie.getKatID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteKategorie(Kategorie kategorie) {
		try {
			deleteBuchKategorie(kategorie);

			String deleteQuery = "DELETE FROM Kategorie WHERE katID = ?";
			PreparedStatement ps = con.prepareStatement(deleteQuery);
			ps.setInt(1, kategorie.getKatID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// BuchAutor
	// Daten einlegen:
	public void newBuchAutorInsert(Buch buch, Autor autor) {
		try {
			String insertBuchAutorQuery = "INSERT INTO BuchAutor (buchID, autorID) VALUES (?, ?)";
			PreparedStatement psBuchAutor = con.prepareStatement(insertBuchAutorQuery);
			psBuchAutor.setInt(1, buch.getBuchID());
			psBuchAutor.setInt(2, autor.getAutorID());
			psBuchAutor.executeUpdate();
			psBuchAutor.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBuchAutor(Buch buch, Autor autor) {
		if (autor != null) {
			boolean alreadyExists = false;
			for (Autor existingAutor : buch.getAutoren()) {
				if (existingAutor.getAutorID() == autor.getAutorID()) {
					alreadyExists = true;
					break;
				}
			}
			if (!alreadyExists) {
				buch.getAutoren().add(autor);
				newBuchAutorInsert(buch, autor);
			}
		}
	}

	// nach autorID löschen
	public void deleteBuchAutor(Autor autor) {
		try {
			String deleteBuchAutorQuery = "DELETE FROM BuchAutor WHERE autorID = ?";
			PreparedStatement psBuchAutor = con.prepareStatement(deleteBuchAutorQuery);
			psBuchAutor.setInt(1, autor.getAutorID());
			psBuchAutor.executeUpdate();
			psBuchAutor.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// alle Verknüpfungen zw. enstpr. Buch und Autor löschen
	public void deleteBuchAutor(Buch buch, Autor autor) {
		try {
			String deleteBuchAutorQuery = "DELETE FROM BuchAutor WHERE buchID = ? AND autorID = ?";
			PreparedStatement psBuchAutor = con.prepareStatement(deleteBuchAutorQuery);
			psBuchAutor.setInt(1, buch.getBuchID());
			psBuchAutor.setInt(2, autor.getAutorID());
			psBuchAutor.executeUpdate();

			// für Anwender:
			String checkQuery = "SELECT COUNT(*) FROM BuchAutor WHERE buchID = ? AND autorID = ?";
			PreparedStatement psCheck = con.prepareStatement(checkQuery);
			psCheck.setInt(1, buch.getBuchID());
			psCheck.setInt(2, autor.getAutorID());
			ResultSet rs = psCheck.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				System.out.println(
						"buchID= " + buch.getBuchID() + ", autorID= " + autor.getAutorID() + ", count= " + count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// BuchKategorie
	// Daten einlegen:
	public void newBuchKategorieInsert(Buch buch, Kategorie kategorie) {
		try {
			String insertBuchKategorieQuery = "INSERT INTO BuchKategorie (buchID, katID) VALUES (?, ?)";
			PreparedStatement psBuchKategorie = con.prepareStatement(insertBuchKategorieQuery);
			psBuchKategorie.setInt(1, buch.getBuchID());
			psBuchKategorie.setInt(2, kategorie.getKatID());
			psBuchKategorie.executeUpdate();
			psBuchKategorie.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBuchKategorie(Buch buch, Kategorie kategorie) {
		if (kategorie != null) {
			boolean alreadyExists = false;
			for (Kategorie existingKat : buch.getKategorien()) {
				if (existingKat.getKatID() == kategorie.getKatID()) {
					alreadyExists = true;
					break;
				}
			}
			if (!alreadyExists) {
				buch.getKategorien().add(kategorie);
				newBuchKategorieInsert(buch, kategorie);
			}
		}
	}

	// nach katID löschen
	public void deleteBuchKategorie(Kategorie kategorie) {
		try {
			String deleteBuchKatQuery = "DELETE FROM BuchKategorie WHERE katID = ?";
			PreparedStatement psBuchKat = con.prepareStatement(deleteBuchKatQuery);
			psBuchKat.setInt(1, kategorie.getKatID());
			psBuchKat.executeUpdate();
			psBuchKat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// alle Verknüpfungen zw. enstpr. Buch und Kategorie löschen
	public void deleteBuchKategorie(Buch buch, Kategorie kategorie) {
		try {
			String deleteBuchKatQuery = "DELETE FROM BuchKategorie WHERE buchID = ? AND katID = ?";
			PreparedStatement psBuchKat = con.prepareStatement(deleteBuchKatQuery);
			psBuchKat.setInt(1, buch.getBuchID());
			psBuchKat.setInt(2, kategorie.getKatID());
			psBuchKat.executeUpdate();
			psBuchKat.close();

			// für Anwender:
			String checkQuery = "SELECT COUNT(*) FROM BuchKategorie WHERE buchID = ? AND katID = ?";
			PreparedStatement psCheck = con.prepareStatement(checkQuery);
			psCheck.setInt(1, buch.getBuchID());
			psCheck.setInt(2, kategorie.getKatID());
			ResultSet rs = psCheck.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				System.out.println(
						"buchID= " + buch.getBuchID() + ", katID= " + kategorie.getKatID() + ", count= " + count);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
