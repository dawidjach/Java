package aufgabe03;

public class Zylinder {
	
private double radius, hoehe, volumen, oberflaeche;
	
	public Zylinder(double radius, double hoehe) {
		this.hoehe = hoehe;
		this.radius = radius;
		this.volumen = volumen;
		this.oberflaeche = oberflaeche;
		double mathPowRadiusRadius = Math.pow(this.radius, this.radius);
		double mathPiMalHoehe = Math.PI * this.hoehe;
		
		// π r² h
		volumen = mathPiMalHoehe * mathPowRadiusRadius;
		// 2π r h + 2π r²
		oberflaeche = (2*mathPiMalHoehe * radius) + (2*Math.PI * mathPowRadiusRadius);
	}

	public String toString( ) {
		return("Volumen = " + String.format("%.02f", volumen) 
			+ " und Oberfläche eines Zylinders  = " + String.format("%.02f", oberflaeche));
	}

}
