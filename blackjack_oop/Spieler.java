package SiebzehnUndVier.copy;

public abstract class Spieler {
	private Karten karten;
	private int punkte;
	private int anzahlKarten;
    private int siege;
	private int niederlagen;
	private String name; 
    
	

	public Spieler(Karten karten) {
		this.karten = karten;
		this.punkte = 0;
        this.anzahlKarten = 2;
        this.siege = 0;
        this.niederlagen = 0;
	}

	public int spielProlog() {	
		System.out.print("Karten für " + getName() + ": ");
		karteZiehen();
		System.out.print(" + ");
		karteZiehen();
		System.out.println();
		
		return getPunkte();
	}

	public abstract int spiel();
	
	public void karteZiehen() {
        karten.kartenVerteilen(this);
        anzahlKarten++;
    }

    public int getPunkte() {
        return punkte;
    }
    
    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public int getAnzahlKarten() {
        return anzahlKarten;
    }
    
    public void setAnzahlKarten(int anzahlKarten) {
    	this.anzahlKarten = anzahlKarten;
    }

	public int getSiege() {
		return siege;
	}

	public int getNiederlagen() {
		return niederlagen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void gewonnen() {
		this.siege++;
	}

	public void verloren() {
		this.niederlagen++;
	}
}
