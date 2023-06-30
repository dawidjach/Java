package kassenbon;

import java.util.Collections;
import java.util.Scanner;

public class Kassenbon {

	public static void main(String[] args) {
		String wurst = "Wurst", kaese = "Käse", brot = "Brot", dvd = "DVD", gesamt = "Gesamt";
		double wurstPreis = 4.20, kaesePreis = 2.30, brotPreis = 2.10, 
				dvdPreis = 24.00;
		double summe = wurstPreis + kaesePreis + brotPreis + dvdPreis;
		
		// a)
		System.out.println(String.format(wurst + "\t 1 x %.02f EUR\n%27.2f EUR", wurstPreis, wurstPreis));
		System.out.println(String.format(kaese + "\t 1 x %.02f EUR\n%27.2f EUR", kaesePreis, kaesePreis));
		System.out.println(String.format(brot + "\t 1 x %.02f EUR\n%27.2f EUR", brotPreis, brotPreis));
		System.out.println(String.format(dvd + "\t 1 x %.02f EUR\n%27.2f EUR", dvdPreis, dvdPreis));
		System.out.println(String.join("", Collections.nCopies(32, "-")));
		System.out.println(String.format(gesamt + "%21.2f EUR", summe));
		
		// b)
		Scanner scanner = new Scanner(System.in);
		System.out.printf("Wie viel gegeben: ");
		double gegeben = scanner.nextDouble();			
		if(gegeben <= summe) {
			System.out.println("Fehler!");
		} else {
			System.out.println(String.format("Rest:%22.2f EUR", gegeben-summe));			
		}
	}

}
