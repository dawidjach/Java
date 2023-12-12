package client;

import java.util.ArrayList;
import java.util.List;
import commoninterface.*;
import commoninterface.Error;

public class ClientImplementierung implements Interface {

	@Override
	public ReturnZustand umwandelnSTL2Zustand(STL stl) {
		int errorCode = 100;
		List<Triangle> triangleListe = new ArrayList<>();
		Error error = new Error(errorCode);
		Zustand zustand = new Zustand(triangleListe);

		ReturnZustand rz = new ReturnZustand(error, zustand);
		return rz;
	}

	@Override
	public ReturnZustand schliesseBohrungen(Zustand zustand) {
		int errorCode = 100;
		List<Triangle> triangleListe = new ArrayList<>();
		Error error = new Error(errorCode);
		zustand = new Zustand(triangleListe);

		ReturnZustand rz = new ReturnZustand(error, zustand);
		return rz;
	}

	@Override
	public ReturnZielZustandDrehen berechneZielZustandDrehen(List<Triangle> triangleList) {
		double einDWX = 1.0;
		double einDWY = 1.5;
		double einDWZ = 2.3;
		double eineVerschiebungX = 2.3;
		double eineVerschiebungY = 0.4;
		double eineVerschiebungZ = 0.1;
		double einD = 50.0;
		double eineL = 80.5;
		int einErrorCode = 100;
		double dieXKP1 = 5.0, dieYKP1 = 6.5, dieZKP1 = 8.0;
		double dieXKP2 = 5.0, dieYKP2 = 6.6, dieZKP2 = 8.2;
		double dieXKP3 = 5.0, dieYKP3 = 6.4, dieZKP3 = 7.9;
		double derXKNV = 0.0, dieYKNV = 2.1, dieZKNV = 3.6;
		List<Triangle> einBiboTriangleListe = new ArrayList<>();
		Triangle einBiboTriangle01 = new Triangle(dieXKP1, dieYKP1, dieZKP1, dieXKP2, dieYKP2, dieZKP2, dieXKP3,
				dieYKP3, dieZKP3, derXKNV, dieYKNV, dieZKNV);
		einBiboTriangleListe.add(0, einBiboTriangle01);
		Error derErrorCode = new Error(einErrorCode);
		derErrorCode.getDerErrorCode();
		Zustand derBiboZustand = new Zustand(einBiboTriangleListe);
		Lage dieLageZielZustand = new Lage(einDWX, einDWY, einDWZ, eineVerschiebungX, eineVerschiebungY,
				eineVerschiebungZ);
		MaxAbmessungen biboMaxAbmessungen = new MaxAbmessungen(einD, eineL);

		ReturnZielZustandDrehen zzd = new ReturnZielZustandDrehen(derErrorCode, derBiboZustand, dieLageZielZustand,
				biboMaxAbmessungen);
		return zzd;
	}

	@Override
	public ReturnEg berechneEgDatenDrehen(Zustand zustand, EgVolumen volumen,
			EgFlaeche flaeche) {
		int einErrorCode = 100;
		double dieZRVorschubX = 4.0, dieZRVorschubY = 3.5, dieZRSpantiefeX = 2.0, dieZRSpantiefeY = 2.5;
		List<Double> diePunktefolge = new ArrayList<>();
		diePunktefolge.add(2.5);
		diePunktefolge.add(3.0);
		Error derErrorCode = new Error(einErrorCode);
		volumen = new EgVolumen(dieZRVorschubX, dieZRVorschubY, dieZRSpantiefeX, dieZRSpantiefeY, diePunktefolge);

		ReturnEg eg = new ReturnEg(derErrorCode, volumen);
		return eg;
	}
}
