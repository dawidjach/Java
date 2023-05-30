package SiebzehnUndVier.copy;


public class Karte {

	String wert, symbol;
	int punkt;

	public Karte(String wert, String symbol, int punkt) {
		this.wert = wert;
		this.symbol = symbol;
		this.punkt = punkt;
	}
	
	public String getName() {
		return getSymbol() + " " + getWert();
	}

	public String getWert() {
		return wert;
	}

	public String getSymbol() {
		return symbol;
	}

	public int getPunkt() {
		return punkt;
	}

}
