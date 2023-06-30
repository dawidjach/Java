package aufgabe03;

public class Kugel {
	private double r, oberflaecheKugel, volumenKugel, umfangKugel;
	public static Kugel kugel01;

	public Kugel(double r) {
		this.r = r;
		this.oberflaecheKugel = oberflaecheKugel;
		this.volumenKugel = volumenKugel;
		this.umfangKugel = umfangKugel;
		
		// volumenKugel = 4/3πr3
		// umfangKugel = 2πr (obwod)
		// oberflaecheKugel = 4πr2

		volumenKugel = 4.0/3.0 * Math.PI * Math.pow(r, 3);
		umfangKugel = 2 * Math.PI * r;
		oberflaecheKugel = umfangKugel * 2 * r;
	}
	
	public String toString( ) {
		return("\nOberfäche einer Kugel = " + String.format("%.02f", oberflaecheKugel)
			+ " , Volumen einer Kugel = " + String.format("%.02f", volumenKugel)
			+ " und ihre Umfang = " + String.format("%.02f", umfangKugel));
	}
}
