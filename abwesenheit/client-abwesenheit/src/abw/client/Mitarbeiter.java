package src.abw.client;

import java.util.ArrayList;
import java.util.List;

public class Mitarbeiter {
	private int id;
	private String nutzername, vollname, msn, mail;

	public Mitarbeiter(String nutzername, String vollname, String msn, String mail) {
		this.nutzername = nutzername;
		this.vollname = vollname;
		this.msn = msn;
		this.mail = mail;
	}

	public Mitarbeiter(String nutzername) {
		this.nutzername = nutzername;
	}

	// Konstruktor nur um Daten aus DB einzulesen
	public Mitarbeiter(int id, String nutzername) {
		this.id = id;
		this.nutzername = nutzername;
	}

	public Mitarbeiter() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNutzername() {
		return nutzername;
	}

	public void setNutzername(String nutzername) {
		this.nutzername = nutzername;
	}
	
	public String getVollname() {
		return vollname;
	}

	public void setVollname(String vollname) {
		this.vollname = vollname;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<Mitarbeiter> mitarbeiterListe(Mitarbeiter mitarbeiter) {
		List<Mitarbeiter> mitarbeitern = new ArrayList<>();
		
	    Mitarbeiter mmustermann = new Mitarbeiter("mmustermann", "Max Mustermann", "mustermann", "max.mustermann@company.de");
	    Mitarbeiter jschmidt = new Mitarbeiter("jschmidt", "Jennifer Schmidt", "schmidt", "jennifer.schmidt@company.de");
	    Mitarbeiter hmoeller = new Mitarbeiter("hmoeller", "Hannah Möller", "moeller", "hannah.moeller@company.de");
	    Mitarbeiter aweiss = new Mitarbeiter("aweiss", "Andreas Weiß", "weiss", "andreas.weiss@company.de");

	    mitarbeitern.add(mmustermann);
	    mitarbeitern.add(jschmidt);
	    mitarbeitern.add(hmoeller);
	    mitarbeitern.add(aweiss);

	    return mitarbeitern;
	}
}
