package company;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		int errorCodes1 = 2;
		int errorCodes2 = 12;
		String testZustand = "Testzustand";
		String testZZD = "TestZZD-Ziel Zustand Drehen";
		String testEG = "TestEG";
		String testSTL = "TestSTL";
		String testFEBO = "TestFEBO";
		String testLageTKS = "Test LageTKS";
		List<Double> maxAbmessungen = new ArrayList<>();
		maxAbmessungen.add(50.0);
		maxAbmessungen.add(150.0);
		maxAbmessungen.add(250.0);
		
		STL stl = new STL(testSTL);
		FEBO febo = new FEBO(testFEBO);
		Zustand zustand = new Zustand(testZustand);
		LageTKS lagetks = new LageTKS(testZZD);
		ZielZustandDrehen zzd = new ZielZustandDrehen(errorCodes1, testZustand, testLageTKS, maxAbmessungen);
		EG eg = new EG(errorCodes2, testEG);
		
		InterfaceMitMethoden imm = new DummyImplementierungInterface();	
		imm.umwandelnSTL2Zustand(stl, febo, zustand, lagetks);
		imm.schliesseBohrungen(stl, febo, zustand, lagetks);
		imm.berechneZielZustandDrehen(stl, febo, zustand, lagetks, zzd);
		imm.berechneEGM_Daten(stl, febo, zustand, lagetks, eg);
	}
}
