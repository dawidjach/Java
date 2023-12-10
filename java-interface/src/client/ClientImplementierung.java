package client;

import commoninterface.*;

public class ClientImplementierung implements Interface {
	
	@Override
	public void umwandelnSTL2Zustand(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks) {
		System.out.println(zustand.toString());
	}
	
	@Override
	public void schliesseBohrungen(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks) {
		System.out.println(zustand.toString());
	}
	
	@Override
	public void berechneZielZustandDrehen(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks, ZielZustandDrehen zzd) {
		System.out.println(zzd.toString());
	}
	
	@Override
	public void berechneEGM_Daten(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks, EG eg) {
		System.out.println(eg.toString());
	}

	@Override
	public void umwandelnSTL2Zustand(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schliesseBohrungen(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void berechneZielZustandDrehen(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks, ZielZustandDrehen zzd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void berechneEGM_Daten(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks, EG eg) {
		// TODO Auto-generated method stub
		
	}
}
