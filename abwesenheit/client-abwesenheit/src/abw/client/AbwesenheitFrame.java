package src.abw.client;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import com.toedter.calendar.JCalendar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Konstruktor für das Abwesenheits-Fenster.
 *
 * @throws IOException Wird ausgelöst, wenn ein Ein-/Ausgabefehler auftritt.
 */
public class AbwesenheitFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private AbwesenheitDBManagement am;
	private DefaultTableModel model;
	JTable table = new JTable();
	JPanel inputPanel = new JPanel();
	SimpleDateFormat europeanDateFormat = new SimpleDateFormat("dd.MM.yyyy");
	private boolean result;
	private PropertiesDateiEinlesen properties = new PropertiesDateiEinlesen();

	public AbwesenheitFrame() throws IOException {
		am = AbwesenheitDBManagement.getInstance();

		setTitle("Abwesenheit - github.com/dawidjach/");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		add(mainPanel());
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Erstellt das Hauptpanel für das Abwesenheits-Fenster.
	 *
	 * @return Das Hauptpanel.
	 */
	private JPanel mainPanel() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Abstand

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		JPanel menuPanel = menuPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.1;
		mainPanel.add(menuPanel, gbc);

		JPanel bodyPanel = bodyPanel();
		gbc.gridy = 1;
		gbc.weighty = 0.9;
		mainPanel.add(bodyPanel, gbc);
		return mainPanel;
	}

	/**
	 * Erstellt das Menüpanel für das Abwesenheits-Fenster.
	 *
	 * @return Das Menüpanel.
	 */
	private JPanel menuPanel() {
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridBagLayout());
		Border border = BorderFactory.createLineBorder(Color.decode("#1b7895"));
		menuPanel.setBorder(border);
		Color background = Color.decode("#a0d2eb");
		menuPanel.setBackground(background);
		JPanel buttonsPanel = buttonsPanel();
		menuPanel.add(buttonsPanel);
		return menuPanel;
	}

	/**
	 * Erstellt das Panel mit Schaltflächen (Buttons) im Menü.
	 *
	 * @return Das Panel mit Schaltflächen.
	 */
	private JPanel buttonsPanel() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(null);

		JButton newButton, editButton, deleteButton;
		newButton = new JButton("Neu");
		editButton = new JButton("Editieren");
		deleteButton = new JButton("Löschen");

		newButton.setPreferredSize(new Dimension(90, 25));
		newButton.setFocusPainted(false);
		editButton.setPreferredSize(new Dimension(90, 25));
		editButton.setFocusPainted(false);
		deleteButton.setPreferredSize(new Dimension(90, 25));
		deleteButton.setFocusPainted(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10); // Abstand

		buttonsPanel.add(newButton, gbc);

		gbc.gridx = 1;
		buttonsPanel.add(editButton, gbc);

		gbc.gridx = 2;
		buttonsPanel.add(deleteButton, gbc);

		/**
		 * Erstellt und gibt die Schaltfläche "Abwesenheit Anlegen" zurück.
		 *
		 * @param inputPanel3 Das Panel, zu dem die Schaltfläche hinzugefügt wird.
		 * @return JButton für das Anlegen von Abwesenheiten.
		 */
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					abwesenheitAnlegen();
				} catch (IOException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
					JOptionPane.showMessageDialog(null, "Fehler: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		/**
		 * Erstellt und gibt die Schaltfläche "Abwesenheit Editieren" zurück.
		 *
		 * @param inputPanel3 Das Panel, zu dem die Schaltfläche hinzugefügt wird.
		 * @return JButton zum Bearbeiten von Abwesenheiten.
		 */
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					abwesenheitEditieren();
				} catch (IOException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
					JOptionPane.showMessageDialog(null, "Fehler: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		/**
		 * Erstellt und gibt die Schaltfläche "Abwesenheit Löschen" zurück.
		 *
		 * @param inputPanel3 Das Panel, zu dem die Schaltfläche hinzugefügt wird.
		 * @return JButton zum Löschen von Abwesenheiten.
		 */
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					abwesenheitLoeschen();
				} catch (SQLException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
					JOptionPane.showMessageDialog(null, "Fehler: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		return buttonsPanel;
	}

	/**
	 * Erstellt das Haupt-Panel für den Inhalt des Abwesenheits-Fensters.
	 *
	 * @return Das Haupt-Panel für den Inhalt.
	 */
	private JPanel bodyPanel() {
		JPanel bodyPanel = new JPanel(new BorderLayout());
		bodyPanel.setBackground(null);
		bodyPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		JPanel tablePanel = tablePanel();
		bodyPanel.add(tablePanel);
		JScrollPane scrollPane = new JScrollPane(table);
		bodyPanel.add(scrollPane, BorderLayout.CENTER);
		return bodyPanel;
	}

	/**
	 * Erstellt ein Panel mit einer Tabelle für Daten.
	 *
	 * @return Das Panel mit der Tabelle für Daten.
	 */
	private JPanel tablePanel() {
		JPanel tablePanel = new JPanel(new BorderLayout());
		String[] columnNames = { "", "Nutzername", "Abwesenheitsgrund", "Startdatum", "Enddatum" };
		model = new DefaultTableModel(columnNames, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			// blockieren das Editieren der Spalten mit Abwesenheiten
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		table.setBorder(null);

		// Render- und Fonteinstellung für Header
		setHeaderTableRenderer(table, new Font("Arial", Font.BOLD, 15));

		// Breite einstellen
		setBodyPanelColumnsSize(table);

		setTable(model);
		return tablePanel;
	}

	/**
	 * Erstellt und gibt ein Dropdown-Menü (JComboBox) mit verfügbaren Benutzern zurück.
	 *
	 * @param inputPanel1a Das Panel, zu dem die Dropdown-Liste hinzugefügt wird.
	 * @return JComboBox mit verfügbaren Benutzern.
	 * @throws IOException Wird ausgelöst, wenn ein Ein-/Ausgabefehler auftritt.
	 */
	private JComboBox<String> createNutzernameComboBox(JPanel inputPanel1a) throws IOException {
		List<Mitarbeiter> nutzernamenList = readNutzernamenList();
		List<String> nutzernamenStrings = new ArrayList<>();
		JComboBox<String> nutzernameComboBox = new JComboBox<>();

		// als String anzeigen
		for (Mitarbeiter mitarbeiter : nutzernamenList) {
			String nutzernameString = mitarbeiter.getNutzername();
			nutzernamenStrings.add(nutzernameString);
		}
		nutzernameComboBox.setModel(new DefaultComboBoxModel<>(nutzernamenStrings.toArray(new String[0])));
		inputPanel1a.add(nutzernameComboBox);
		return nutzernameComboBox;
	}

	/**
	 * Erstellt und gibt ein Dropdown-Menü (JComboBox) mit verfügbaren Abwesenheitsgründen zurück.
	 *
	 * @param inputPanel1b Das Panel, zu dem die Dropdown-Liste hinzugefügt wird.
	 * @return JComboBox mit verfügbaren Abwesenheitsgründen.
	 * @throws IOException Wird ausgelöst, wenn ein Ein-/Ausgabefehler auftritt.
	 */
	private JComboBox<String> createAbwgrundComboBox(JPanel inputPanel1b) throws IOException {
		List<Abwesenheitsgrund> abwgruendeList = readAbwesenheitsgruendeList();
		List<String> abwgruendeStrings = new ArrayList<>();
		JComboBox<String> abwgrundComboBox = new JComboBox<>();

		// als String anzeigen
		for (Abwesenheitsgrund abwgrund : abwgruendeList) {
			String abwgrundString = abwgrund.getName();
			abwgruendeStrings.add(abwgrundString);
		}
		abwgrundComboBox.setModel(new DefaultComboBoxModel<>(abwgruendeStrings.toArray(new String[0])));
		inputPanel1b.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		inputPanel1b.add(abwgrundComboBox);
		return abwgrundComboBox;
	}

	/**
	 * Erstellt und gibt einen neuen JCalendar für das Startdatum zurück.
	 *
	 * @param inputPanel2 Das Panel, zu dem der JCalendar hinzugefügt wird.
	 * @return JCalendar für das Startdatum.
	 */
	private JCalendar createNewStartdatumCalendar(JPanel inputPanel2) {
		inputPanel2.add(new JLabel("Startdatum:"));
		JCalendar startdatumCalendar = new JCalendar();
		startdatumCalendar.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		inputPanel2.add(startdatumCalendar);
		return startdatumCalendar;
	}

	/**
	 * Erstellt und gibt einen JCalendar für das Startdatum mit vorausgefülltem Datum zurück.
	 *
	 * @param inputPanel2 Das Panel, zu dem der JCalendar hinzugefügt wird.
	 * @return JCalendar für das Startdatum mit vorausgefülltem Datum.
	 */
	private JCalendar createEditStartdatumCalendar(JPanel inputPanel2, Abwesenheit abwesenheit) {
		inputPanel2.add(new JLabel("Startdatum:"));
		JCalendar startdatumCalendar = new JCalendar();
		startdatumCalendar.setDate(abwesenheit.getStartdatum());
		startdatumCalendar.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		inputPanel2.add(startdatumCalendar);
		return startdatumCalendar;
	}

	/**
	 * Erstellt und gibt einen neuen JCalendar für das Enddatum zurück.
	 *
	 * @param inputPanel2 Das Panel, zu dem der JCalendar hinzugefügt wird.
	 * @return JCalendar für das Enddatum.
	 */
	private JCalendar createNewEnddatumCalendar(JPanel inputPanel2) {
		inputPanel2.add(new JLabel("Enddatum:"));
		JCalendar enddatumCalendar = new JCalendar();
		enddatumCalendar.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		inputPanel2.add(enddatumCalendar);
		return enddatumCalendar;
	}

	/**
	 * Erstellt und gibt einen JCalendar für das Enddatum mit vorausgefülltem Datum zurück.
	 *
	 * @param inputPanel2 Das Panel, zu dem der JCalendar hinzugefügt wird.
	 * @return JCalendar für das Enddatum mit vorausgefülltem Datum.
	 */
	private JCalendar createEditEnddatumCalendar(JPanel inputPanel2, Abwesenheit abwesenheit) {
		inputPanel2.add(new JLabel("Enddatum:"));
		JCalendar enddatumCalendar = new JCalendar();
		enddatumCalendar.setDate(abwesenheit.getEnddatum());
		enddatumCalendar.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		inputPanel2.add(enddatumCalendar);
		return enddatumCalendar;
	}

	/**
	 * Erstellt und gibt ein neues JPanel für die Eingabe zurück.
	 *
	 * @return Ein neues JPanel für die Eingabe.
	 */
	private JPanel setInputPanel() {
		JPanel inputPanel = new JPanel(new BorderLayout());
		return inputPanel;
	}

	/**
	 * Erstellt und gibt ein neues JPanel für den Abschnitt 1a der Eingabe zurück.
	 *
	 * @return Ein neues JPanel für den Abschnitt 1a der Eingabe.
	 */
	private JPanel setInputPanel1a() {
		JPanel inputPanel1a = new JPanel(new GridLayout(1, 1));
		inputPanel1a.add(new JLabel("Nutzername:"));
		return inputPanel1a;
	}

	/**
	 * Erstellt und gibt ein neues JPanel für den Abschnitt 1b der Eingabe zurück.
	 *
	 * @return Ein neues JPanel für den Abschnitt 1b der Eingabe.
	 */
	private JPanel setInputPanel1b() {
		JPanel inputPanel1b = new JPanel(new GridLayout(1, 2));
		inputPanel1b.add(new JLabel("Abwesenheitsgrund:"));
		return inputPanel1b;
	}

	/**
	 * Erstellt und gibt ein neues JPanel für den Abschnitt 2 der Eingabe zurück.
	 *
	 * @return Ein neues JPanel für den Abschnitt 2 der Eingabe.
	 */
	private JPanel setInputPanel2() {
		JPanel inputPanel2 = new JPanel(new GridLayout(2, 1));
		return inputPanel2;
	}

	/**
	 * Zeigt ein Eingabedialogfeld für das Hinzufügen einer neuen Abwesenheit an.
	 *
	 * @param title Der Titel des Dialogfelds.
	 * @param abwesenheit Die Abwesenheit, die bearbeitet wird.
	 * @param mitarbeiter Der Mitarbeiter, für den die Abwesenheit erstellt wird.
	 * @throws IOException Wird ausgelöst, wenn ein Ein-/Ausgabefehler auftritt.
	 */
	private void showInputDialog(String title, Abwesenheit abwesenheit) throws IOException {
		inputPanel = setInputPanel();
		JPanel inputPanel1a = setInputPanel1a();
		JComboBox<String> nutzernameComboBox = createNutzernameComboBox(inputPanel1a);
		JPanel inputPanel1b = setInputPanel1b();
		JComboBox<String> abwgrundComboBox = createAbwgrundComboBox(inputPanel1b);
		JPanel inputPanel2 = setInputPanel2();
		JCalendar startdatumCalendar = createNewStartdatumCalendar(inputPanel2);
		JCalendar enddatumCalendar = createNewEnddatumCalendar(inputPanel2);

		inputPanel.add(inputPanel1a, BorderLayout.NORTH);
		inputPanel.add(inputPanel1b, BorderLayout.CENTER);
		inputPanel.add(inputPanel2, BorderLayout.SOUTH);

		showConfirmDialog(title, nutzernameComboBox, abwgrundComboBox, startdatumCalendar, enddatumCalendar,
				abwesenheit);
	}

	/**
	 * Zeigt ein Bestätigungsdialogfeld für die Eingabe an.
	 *
	 * @param title Der Titel des Dialogfelds.
	 * @param nutzernameComboBox Die JComboBox für den Nutzernamen.
	 * @param abwgrundComboBox Die JComboBox für den Abwesenheitsgrund.
	 * @param startdatumCalendar Der JCalendar für das Startdatum.
	 * @param enddatumCalendar Der JCalendar für das Enddatum.
	 * @throws IOException Wird ausgelöst, wenn ein Ein-/Ausgabefehler auftritt.
	 */
	private void showConfirmDialog(String title, JComboBox<String> nutzernameComboBox,
			JComboBox<String> abwgrundComboBox, JCalendar startdatumCalendar, JCalendar enddatumCalendar,
			Abwesenheit abwesenheit) throws IOException {
		result = (JOptionPane.showConfirmDialog(null, inputPanel, title,
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION);
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		Abwesenheitsgrund abwgrund = new Abwesenheitsgrund();
		if (result) {
			String nutzername = (String) nutzernameComboBox.getSelectedItem();
			String abwesenheitsgrund = (String) abwgrundComboBox.getSelectedItem();
			java.sql.Date startdatum = new java.sql.Date(startdatumCalendar.getDate().getTime());
			java.sql.Date enddatum = new java.sql.Date(enddatumCalendar.getDate().getTime());

			mitarbeiter.setNutzername(nutzername);
			abwgrund.setName(abwesenheitsgrund);
			abwesenheit.setStartdatum(startdatum);
			abwesenheit.setEnddatum(enddatum);

			Mitarbeiter passendeMitarbeiter = null;
			for (Mitarbeiter newMitarbeiter1 : mitarbeiter.mitarbeiterListe(mitarbeiter)) {
				if (nutzername != null && nutzername.equals(newMitarbeiter1.getNutzername())) {
					passendeMitarbeiter = newMitarbeiter1;
					mitarbeiter = passendeMitarbeiter;
					abwesenheit.setMitarbeiter(mitarbeiter);
					abwesenheit.setAbwesenheitsgrund(abwgrund);
					setSieveText(abwesenheit, abwgrund);
				}
			}
		} else {
			result = false;
		}
	}
	
	/**
	 * Zeigt ein Dialogfeld für die Bearbeitung einer vorhandenen Abwesenheit an.
	 *
	 * @param title Der Titel des Dialogfelds.
	 * @param abwesenheit Die Abwesenheit, die bearbeitet wird.
	 * @throws IOException Wird ausgelöst, wenn ein Ein-/Ausgabefehler auftritt.
	 */
	private void showEditDialog(String title, Abwesenheit abwesenheit) throws IOException {
		inputPanel = setInputPanel();
		JPanel inputPanel1a = setInputPanel1a();
		JComboBox<String> nutzernameComboBox = createNutzernameComboBox(inputPanel1a);
		List<Mitarbeiter> nutzernamenList = readNutzernamenList();
		List<String> nutzernamenStrings = new ArrayList<>();

//		Mitarbeiter mitarbeiter = abwesenheit.getMitarbeiter();
		for (Mitarbeiter m : nutzernamenList) {
			String nutzernameString = m.getNutzername();
			nutzernamenStrings.add(nutzernameString);
		}
		nutzernameComboBox.setSelectedItem(abwesenheit.getMitarbeiter().getNutzername());
		inputPanel1a.add(nutzernameComboBox);

		JPanel inputPanel1b = setInputPanel1b();
		JComboBox<String> abwgrundComboBox = createAbwgrundComboBox(inputPanel1b);
		List<Abwesenheitsgrund> abwgruendeList = readAbwesenheitsgruendeList();
		List<String> abwesenheitsgruendeStrings = new ArrayList<>();

		for (Abwesenheitsgrund a : abwgruendeList) {
			String abwgrundString = a.getName();
			abwesenheitsgruendeStrings.add(abwgrundString);
		}
		abwgrundComboBox.setSelectedItem(abwesenheit.getAbwesenheitsgrund().getName());
		inputPanel1b.add(abwgrundComboBox);

		JPanel inputPanel2 = setInputPanel2();
		JCalendar startdatumCalendar = createEditStartdatumCalendar(inputPanel2, abwesenheit);
		JCalendar enddatumCalendar = createEditEnddatumCalendar(inputPanel2, abwesenheit);

		inputPanel.add(inputPanel1a, BorderLayout.NORTH);
		inputPanel.add(inputPanel1b, BorderLayout.CENTER);
		inputPanel.add(inputPanel2, BorderLayout.SOUTH);

		showConfirmDialog(title, nutzernameComboBox, abwgrundComboBox, startdatumCalendar, enddatumCalendar,
				abwesenheit);
	}

	/**
	 * Liest eine Liste von Mitarbeitern aus einer Datei ein und gibt sie als Liste von Mitarbeitern zurück.
	 *
	 * @return Eine Liste von Mitarbeitern.
	 * @throws IOException Wenn ein Fehler beim Lesen der Datei auftritt.
	 */
	private List<Mitarbeiter> readNutzernamenList() throws IOException {
//		String path = AbwesenheitFrame.class.getResource("/home/jach/client-abwesenheit/conf/nutzernamenListe.txt").getPath();
//		String path = "/home/jach/client-abwesenheit/conf/nutzernamenListe.txt";
		List<Mitarbeiter> nutzernamenList = new ArrayList<>();
		properties.propertiesEinlesen();
		try (BufferedReader reader = new BufferedReader(new FileReader(properties.nutzernamenListe))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String nutzername = line.trim();
				Mitarbeiter mitarbeiter = new Mitarbeiter(nutzername);
				nutzernamenList.add(mitarbeiter);
			}
		}
		return nutzernamenList;
	}

	/**
	 * Liest eine Liste von Abwesenheitsgründen aus einer Datei ein und gibt sie als Liste von Abwesenheitsgründen zurück.
	 *
	 * @return Eine Liste von Abwesenheitsgründen.
	 * @throws IOException Wenn ein Fehler beim Lesen der Datei auftritt.
	 */
	private List<Abwesenheitsgrund> readAbwesenheitsgruendeList() throws IOException {
//		String path = AbwesenheitFrame.class.getResource("/home/jach/client-abwesenheit/conf/abwListe.txt").getPath();
//		String path = "/home/jach/client-abwesenheit/conf/abwListe.txt";
		properties.propertiesEinlesen();
		List<Abwesenheitsgrund> abwesenheitsgruendeList = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(properties.abwListe))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String abwgrundString = line.trim();
				Abwesenheitsgrund abwgrund = new Abwesenheitsgrund(abwgrundString);
				abwesenheitsgruendeList.add(abwgrund);
			}
		}
		return abwesenheitsgruendeList;
	}
	
	/**
	 * Liest den Inhalt eines Sieve-Skripts aus einer Datei ein und gibt ihn als Zeichenkette zurück.
	 *
	 * @return Der Inhalt des Sieve-Skripts als Zeichenkette.
	 * @throws IOException Wenn ein Fehler beim Lesen der Datei auftritt.
	 */
	private String readSieveText() throws IOException {
//		String path = AbwesenheitFrame.class.getResource("abw/conf/sieve.txt").getPath();
//		String path = "/home/jach/client-abwesenheit/conf/sieve.txt";
//		String path = System.getProperty("user.dir")+"/src/abw/updater/dovecot.sieve/".trim();
		properties.propertiesEinlesen();
		
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(properties.sieve), StandardCharsets.UTF_8)) {
		  stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		String fileContent = contentBuilder.toString();
		// Sieve-Skript (Sieve-Text) mit Platzhalter "<>" anzeigen
//		System.out.println(fileContent);
		return fileContent;
	}
	
	/**
	 * Ersetzt bestimmte Platzhalter im Sieve-Skript durch Werte aus der Abwesenheit und setzt das aktualisierte Skript in der Abwesenheitsgrund-Instanz.
	 *
	 * @param abwesenheit Die Abwesenheit, deren Informationen in das Sieve-Skript eingefügt werden sollen.
	 * @throws IOException Wenn ein Fehler beim Lesen oder Ersetzen der Platzhalter im Skript auftritt.
	 */
	private void setSieveText(Abwesenheit abwesenheit, Abwesenheitsgrund abwgrund) throws IOException {
		String sieveText = readSieveText();

		String mail = abwesenheit.getMitarbeiter().getMail();
//		System.out.println(mail);
		if (sieveText.contains("<mail>")) {
			sieveText = sieveText.replace("<mail>", mail);
		} else {
			System.out.println("Kein <mail>-Tag in Sieve-Text gefunden.");
			JOptionPane.showMessageDialog(null, "Kein <>-Tag in Sieve-Text gefunden");
		}

		String formattedEnddatum = europeanDateFormat.format(abwesenheit.getEnddatum());
//		System.out.println(formattedEnddatum);
		if (sieveText.contains("<enddatum>")) {
			sieveText = sieveText.replace("<enddatum>", formattedEnddatum);
		} else {
			System.out.println("Kein <enddatum>-Tag in Sieve-Text gefunden.");
			JOptionPane.showMessageDialog(null, "Kein <>-Tag in Sieve-Text gefunden");
		}

		try {
		    Date date = europeanDateFormat.parse(formattedEnddatum);

		    // Ustalanie sufiksu dnia
		    String daySuffix;
		    int day = date.getDate();
		    if (day >= 11 && day <= 13) {
		        daySuffix = "th";
		    } else {
		        switch (day % 10) {
		            case 1:
		                daySuffix = "st";
		                break;
		            case 2:
		                daySuffix = "nd";
		                break;
		            case 3:
		                daySuffix = "rd";
		                break;
		            default:
		                daySuffix = "th";
		                break;
		        }
		    }

		    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd'" + daySuffix + "' MMMM yyyy", Locale.ENGLISH);
		    String formattedDate = outputDateFormat.format(date);

		    if (sieveText.contains("<enddatumEN>")) {
		        sieveText = sieveText.replace("<enddatumEN>", formattedDate);
		    } else {
		        System.out.println("Kein <enddatumEN>-Tag in Sieve-Text gefunden.");
		        JOptionPane.showMessageDialog(null, "Kein <>-Tag in Sieve-Text gefunden");
		    }
		} catch (ParseException e) {
		    e.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}


		String vollname = abwesenheit.getMitarbeiter().getVollname();
//		System.out.println(vollname);
		if (sieveText.contains("<vollname>")) {
			sieveText = sieveText.replace("<vollname>", vollname);
		} else {
			System.out.println("Kein <vollname>-Tag in Sieve-Text gefunden.");
			JOptionPane.showMessageDialog(null, "Kein <>-Tag in Sieve-Text gefunden");
		}
		abwgrund.setAbwtxt(sieveText);
//		System.out.println(sieveText);
	}

	/**
	 * Konfiguriert die Darstellung der Tabellenüberschrift (Header) einer gegebenen JTable.
	 *
	 * @param table Die JTable, für die die Tabellenüberschrift konfiguriert werden soll.
	 * @param font  Die Schriftart für die Tabellenüberschrift.
	 */
	private void setHeaderTableRenderer(JTable table, Font font) {
		JTableHeader header = table.getTableHeader();
		header.setFont(font);
		Color foreground = Color.decode("#ffffff");
		Color background = Color.decode("#023547b");
//		Color foreground = Color.decode("#FFFFFF");
//		Color background = Color.decode("#103783");
		// gelborange #FFCC80
		// dunkelblau #003366
		header.setForeground(foreground);
		header.setBackground(background);
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.LEFT);
	}

	/**
	 * Konfiguriert die Größe und das Erscheinungsbild der Spalten in einer Tabelle, einschließlich Schriftart und Hintergrundfarbe der Zellen.
	 *
	 * @param table Die JTable, für die die Spaltengröße und das Erscheinungsbild konfiguriert werden sollen.
	 */
	private void setBodyPanelColumnsSize(JTable table) {
		TableColumn aidColumn = table.getColumnModel().getColumn(0);
		aidColumn.setMinWidth(0);
		aidColumn.setMaxWidth(0);
		table.addColumn(aidColumn);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//		renderer.setForeground(Color.decode("#003366"));
		renderer.setBorder(null);

		TableColumn nutzernameColumn = table.getColumnModel().getColumn(1);
		nutzernameColumn.setPreferredWidth(300);

		TableColumn abwesenheitsgrundColumn = table.getColumnModel().getColumn(2);
		abwesenheitsgrundColumn.setPreferredWidth(300);

		TableColumn datumVonColumn = table.getColumnModel().getColumn(3);
		datumVonColumn.setPreferredWidth(150);

		TableColumn datumBisColumn = table.getColumnModel().getColumn(4);
		datumBisColumn.setPreferredWidth(150);
		table.setRowHeight(25);

		nutzernameColumn.setCellRenderer(renderer);
		abwesenheitsgrundColumn.setCellRenderer(renderer);
		datumVonColumn.setCellRenderer(renderer);
		datumBisColumn.setCellRenderer(renderer);
		setListColorRenderer();
	}

	/**
	 * Legt das Erscheinungsbild der Zellen in der Tabelle fest, insbesondere die Hintergrundfarben basierend auf Zeilennummer und Auswahlstatus.
	 */
	private void setListColorRenderer() {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			String fontColor = "#003366";

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				if (!isSelected) {
					if (row % 2 == 0) {
						cellComponent.setBackground(Color.decode("#DDFAFF")); // gerade Zeilen
						cellComponent.setFont(new Font("Arial", Font.PLAIN, 14));
						cellComponent.setForeground(Color.decode(fontColor));
					} else {
						cellComponent.setBackground(Color.WHITE); // ungerade Zeilen
						cellComponent.setFont(new Font("Arial", Font.PLAIN, 14));
						cellComponent.setForeground(Color.decode(fontColor));
					}
				} else {
					cellComponent.setBackground(Color.decode("#a0d2eb")); // markierte Zeile
					cellComponent.setForeground(table.getSelectionForeground()); // markierte Zeile
					cellComponent.setFont(new Font("Arial", Font.BOLD, 14)); // markierte Zeile
					cellComponent.setForeground(Color.decode(fontColor));
				}
				// Borders aus Zeilen entfernen
				((JComponent) cellComponent).setBorder(null);
				return cellComponent;
			} 
		};

		TableColumnModel columnModel = table.getColumnModel();
		int columnCount = columnModel.getColumnCount();
		for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
			TableColumn column = columnModel.getColumn(columnIndex);
			column.setCellRenderer(renderer);
		}
		 table.setDefaultRenderer(Object.class, renderer);
		 model.fireTableDataChanged();
	}

	/**
	 * Aktualisiert das Modell einer JTable mit Daten aus der Datenbank, um die Abwesenheiten darzustellen.
	 *
	 * @param model Das Modell der JTable, das aktualisiert werden soll.
	 */
	private void setTable(DefaultTableModel model) {
		List<Abwesenheit> abwesenheiten = am.selectAbwesenheiten();

		// Abwesenheiten aufsteigend nach Enddatum sortieren
		// [...].reversed() -> absteigend
//		abwesenheiten.sort(Comparator.comparing(Abwesenheit::getEnddatum));

		for (Abwesenheit abwesenheit : abwesenheiten) {
			String formattedStartdatum = europeanDateFormat.format(abwesenheit.getStartdatum());
			String formattedEnddatum = europeanDateFormat.format(abwesenheit.getEnddatum());

			model.addRow(new Object[] { abwesenheit.getId(), abwesenheit.getMitarbeiter().getNutzername(),
					abwesenheit.getAbwesenheitsgrund().getName(), formattedStartdatum, formattedEnddatum });
		}
		model.fireTableDataChanged();
	}

	/**
	 * Öffnet ein Dialogfeld zum Anlegen einer neuen Abwesenheit.
	 *
	 * @throws IOException Wird ausgelöst, wenn ein Ein-/Ausgabefehler auftritt.
	 */
	private void abwesenheitAnlegen() throws IOException {
		Abwesenheit abwesenheit = new Abwesenheit();
		String title = "Abwesenheit anlegen";
		showInputDialog(title, abwesenheit);
		if (result == true) {
			am.newAbwesenheitInsert(abwesenheit);
		}
		model.setRowCount(0);
		setTable(model);
	}
	
	/**
	 * Öffnet ein Dialogfeld zum Bearbeiten einer vorhandenen Abwesenheit.
	 *
	 * @throws IOException Wird ausgelöst, wenn ein Ein-/Ausgabefehler auftritt.
	 */
	private void abwesenheitEditieren() throws IOException {
		String title = "Abwesenheit editieren";
		Abwesenheit abwesenheit = new Abwesenheit();

		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			int recordId = (int) model.getValueAt(selectedRow, 0); // Wert von unsichtbarer Kolumne `id` auslesen
			abwesenheit = am.selectAbwesenheitenNachId(recordId);
			showEditDialog(title, abwesenheit);

			if (result == true) {
				am.updateAbwesenheit(abwesenheit);
				model.setValueAt(abwesenheit.getMitarbeiter().getNutzername(), selectedRow, 1);
				model.setValueAt(abwesenheit.getAbwesenheitsgrund().getName(), selectedRow, 2);
				model.setValueAt(abwesenheit.getStartdatum(), selectedRow, 3);
				model.setValueAt(abwesenheit.getEnddatum(), selectedRow, 4);
				model.setRowCount(0);
				setTable(model);
			}
		}
	}

	/**
	 * Löscht die ausgewählte Abwesenheit.
	 *
	 * @throws SQLException Wird ausgelöst, wenn ein SQL-Fehler auftritt.
	 */
	private void abwesenheitLoeschen() throws SQLException {
		int selectedRow = table.getSelectedRow();

		if (selectedRow >= 0) {
			int recordId = (int) model.getValueAt(selectedRow, 0);
			System.out.println(recordId);
			am.deleteAbwesenheit(recordId);
			model.removeRow(selectedRow);
		}
	}
}
