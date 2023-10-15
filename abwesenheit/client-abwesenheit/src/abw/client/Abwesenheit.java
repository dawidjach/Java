package src.abw.client;

import java.sql.Date;

public class Abwesenheit {
	private int id;
	private Mitarbeiter mitarbeiter;
	private Abwesenheitsgrund abwesenheitsgrund;
	private String datumVon, datumBis;
	private Date startdatum, enddatum;
	
	public Abwesenheit() {
		
	}

	public Abwesenheit(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
	
	// um Abwesenheit in der GUI anzeigen
	public Abwesenheit(int id, Mitarbeiter mitarbeiter, Abwesenheitsgrund abwesenheitsgrund, java.util.Date datumV, java.util.Date datumB) {
		this.id = id;
		this.mitarbeiter = mitarbeiter;
        this.abwesenheitsgrund = abwesenheitsgrund;
		this.startdatum = new java.sql.Date(datumV.getTime());
		this.enddatum = new java.sql.Date(datumB.getTime());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
	
	public Abwesenheitsgrund getAbwesenheitsgrund() {
		return abwesenheitsgrund;
	}

	public void setAbwesenheitsgrund(Abwesenheitsgrund abwesenheitsgrund) {
		this.abwesenheitsgrund = abwesenheitsgrund;
	}

	public Date getStartdatum() {
		return startdatum;
	}

	public void setStartdatum(Date startdatum) {
		this.startdatum = startdatum;
	}

	public Date getEnddatum() {
		return enddatum;
	}

	public void setEnddatum(Date enddatum) {
		this.enddatum = enddatum;
	}
	
	public String getDatumVon() {
		return datumVon;
	}

	public void setDatumVon(String datumVon) {
		this.datumVon = datumVon;
	}

	public String getDatumBis() {
		return datumBis;
	}

	public void setDatumBis(String datumBis) {
		this.datumBis = datumBis;
	}
}
