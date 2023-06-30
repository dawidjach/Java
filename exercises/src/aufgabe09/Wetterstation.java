package aufgabe09;

public class Wetterstation {

	public static void main(String[] args) {

			
		// 1
		int[] temperatur = { 12, 14, 9, 12, 15, 16, 15, 15, 11, 8, 13, 13, 15, 12 }; // Summe= 180
		
		// 5
		System.out.printf("Tag:\t\t");
		for(int i = 1; i <= temperatur.length; i++) {
			System.out.printf("%7d", i);
		}
		System.out.printf("\nTemperatur:\t");
		for(int temp : temperatur) {
			System.out.printf("%7d", temp);
		}
		
		// 2
		int sum = 0;
		int arrayLength = temperatur.length;
		
		for(int i = 0; i < temperatur.length; i++) {
			sum += temperatur[i];
		}
		double durschnitt = sum / arrayLength;
		System.out.println("\n\nDurschnitt beträgt " + durschnitt);	// ~12.9
		
		// 3
		int min = temperatur[0], max = temperatur[0];
		
		for(int i = 0; i < temperatur.length; i++) {
			if(temperatur[i] < min) {
				min = temperatur[i];
			}
			if(temperatur[i] > max) {
				max = temperatur[i];
			}
		}
		
		// 4
		int maxDiff = 0;
		int tag1 = 0, tag2 = 0;
		
		for(int i = 0; i < temperatur.length - 1; i++) {
			int diff = Math.abs(temperatur[i] - temperatur[i+1]);
			if(diff > maxDiff) {
				maxDiff = diff;
				tag1 = i + 1;
				tag2 = i +2;
			}
		}
		
		System.out.format("Min Temp. = %d" + ", max Temp. = %d" + ".", min, max);	// min= 8, max= 16
		System.out.format("\nDer größte Temperaturumschwung gab aufeinanderfolgenden Tage: "
				+ "%d und %d und beträgt %d\u00B0C.", tag1, tag2, maxDiff);

	}
	

}
