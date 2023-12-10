package commoninterface;

public class Zustand {
	String zustand;
	
	public Zustand(String zustand) {
		this.zustand = zustand;
	}
	
	public String getZustand() {
		return zustand;
	}

	public void setZustand(String zustand) {
		this.zustand = zustand;
	}

	public String toString() {
		return zustand;
	}
}
