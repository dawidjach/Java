package commoninterface;

import java.util.ArrayList;
import java.util.List;

public class ZielZustandDrehen {
	int errorCodes;
	String zustand;
	String lagetks;
	List<Double> maxAbmessungen = new ArrayList<>();
	
	public ZielZustandDrehen(int errorCodes, String zustand, String lagetks, List<Double> maxAbmessungen) {
		this.errorCodes = errorCodes;
		this.zustand = zustand;
		this.lagetks = lagetks;
		this.maxAbmessungen = maxAbmessungen;
	}
	
	public int getErrorCodes() {
		return errorCodes;
	}
	public void setErrorCodes(int errorCodes) {
		this.errorCodes = errorCodes;
	}
	public String getZustand() {
		return zustand;
	}
	public void setZustand(String zustand) {
		this.zustand = zustand;
	}
	public String getLagetks() {
		return lagetks;
	}
	public void setLagetks(String lagetks) {
		this.lagetks = lagetks;
	}
	public List<Double> getMaxAbmessungen() {
		return maxAbmessungen;
	}
	public void setMaxAbmessungen(List<Double> maxAbmessungen) {
		this.maxAbmessungen = maxAbmessungen;
	}
	
	public String toString() {
		return errorCodes + ", " + zustand + ", " + lagetks + ", {" + maxAbmessungen + "}" ;
	}
}
