package commoninterface;

public interface Interface {
	
	public void umwandelnSTL2Zustand(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks);
	
	public void schliesseBohrungen(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks);
	
	public void berechneZielZustandDrehen(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks, ZielZustandDrehen zzd);
	
	public void berechneEGM_Daten(STL stl, FEBO febo, Zustand zustand, LageTKS lagetks, EG eg);
}
