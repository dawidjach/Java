package aufgabe03;

public class Geometrie {
	public static void main(String[] args) {
		Zylinder zylinder01 = new Zylinder(2,5); // KCalc: Vol = 62.8 , Oberf = 87.92
		System.out.println(zylinder01);
		
		Kugel kugel01 = new Kugel(2);	// KCalc:Oberfläche= 50.3, Volumen= 33.5, Umfang= 12.56
		System.out.println(kugel01);
		
		Quader quader01 = new Quader(2, 3, 4);	// Volumen= 24, Oberfläche= 52, Läng.Raumdiag.= 4.24, Gesamtkantenlänge= 36.
		System.out.println(quader01);
	}

}
