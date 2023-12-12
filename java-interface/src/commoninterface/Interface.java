package commoninterface;

import java.util.List;

public interface Interface {

	public ReturnZustand umwandelnSTL2Zustand(STL stl);

	public ReturnZustand schliesseBohrungen(Zustand zustand);

	public ReturnZielZustandDrehen berechneZielZustandDrehen(List<Triangle> triangleList);

	public ReturnEg berechneEgDatenDrehen(Zustand zustand, EgVolumen volumen,
			EgFlaeche flaeche);
}
