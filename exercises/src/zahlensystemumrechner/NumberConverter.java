package aufgabe06;

public class NumberConverter {


	// 1
	public static void dezimalNachBinaer(int dezimal) {
		int anzahlStellen = 0, zero = 0;
		int output = dezimal;
		while(output == 0) {
			System.out.println(zero);
			break;
		}
		while(output != 0) {
			output /= 2;
			anzahlStellen++;
		}
		int[]zahlen = new int[anzahlStellen];
		for(int i = 0; i < anzahlStellen; i++) {
			zahlen[i] = dezimal % 2;
			dezimal /= 2;
		}
		String binary = "";
		for(int i = anzahlStellen -1; i >= 0; i--) {
			binary += zahlen[i];
		}
		System.out.println(binary);
	}

	public static void main(String[] args) {
		dezimalNachBinaer(10); 	//1010
	}
}
