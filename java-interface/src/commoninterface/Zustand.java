package commoninterface;

import java.util.ArrayList;
import java.util.List;

public class Zustand {
	List<Triangle> dieBiboTriangleListe = new ArrayList<>();
	
	public Zustand(List<Triangle> einBiboTriangleListe) {
		setDieBiboTriangleListe(einBiboTriangleListe);
	}

	

	private List<Triangle> getDieBiboTriangleListe() {
		return dieBiboTriangleListe;
	}
	
	// um @SuppressWarnings("unused") zu vermeiden
	public List<Triangle> retrieveDieBiboTriangleListe() {
		return getDieBiboTriangleListe();
	}

	private void setDieBiboTriangleListe(List<Triangle> dieBiboTriangleListe) {
		this.dieBiboTriangleListe = dieBiboTriangleListe;
	}
	
	
	
	
	public void addBiboTriangle(Triangle einBiboTriangle) {
		dieBiboTriangleListe.add(einBiboTriangle);
	}
	
	public int getBiboTriangleAnzahl() {
		return dieBiboTriangleListe.size();
	}
	
	public Triangle getBiboTriangle(int einIndex) {
		if (einIndex >= 0 && einIndex < dieBiboTriangleListe.size()) {
	        return dieBiboTriangleListe.get(einIndex);
		} else {
			return null;
		}
	}
	
	public String toString() {
		return String.format("DieBiboTriangleListe %s", dieBiboTriangleListe);
	}
}
