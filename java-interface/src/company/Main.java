package company;

import java.util.ArrayList;
import java.util.List;
import client.*;
import commoninterface.*;

public class Main {
	
	public static void main(String[] args) {
		double dieZRVorschubX = 2.3, dieZRVorschubY = 0.4, dieZRSpantiefeX = 0.1, dieZRSpantiefeY = 1.8;
		List<Double> diePunktefolge = new ArrayList<>();
		List<Triangle> triangleList = new ArrayList<>();
		String filePath = STL.filePath();
		STL stl = new STL(filePath);

		Zustand zustand = new Zustand(triangleList);
		EgVolumen volumen = new EgVolumen(dieZRVorschubX, dieZRVorschubY, dieZRSpantiefeX, dieZRSpantiefeY, diePunktefolge);
		EgFlaeche flaeche = new EgFlaeche(dieZRVorschubX, dieZRVorschubY, dieZRSpantiefeX, dieZRSpantiefeY);

		Interface schnittstelle = new ClientImplementierung();
		ReturnZustand result1 = schnittstelle.umwandelnSTL2Zustand(stl);
		ReturnZustand result2 = schnittstelle.schliesseBohrungen(zustand);
		ReturnZielZustandDrehen result3 = schnittstelle.berechneZielZustandDrehen(triangleList);
		ReturnEg result4 = schnittstelle.berechneEgDatenDrehen(zustand, volumen,flaeche);

		System.out.println(result1.getDerBiboZustand().getBiboTriangleAnzahl());
		System.out.println(result2.getDerBiboError().getDerErrorCode());
		System.out.println(result3.getDerBiboZustand().getBiboTriangleAnzahl());
		System.out.println(result4.getDieBiboEg().toString());
	}
}
