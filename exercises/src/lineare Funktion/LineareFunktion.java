package aufgabe10;

public class LineareFunktion {
	public static int m = 2, n = -5;
	static int x;
	public static int[] y = new int[21];
	public static int turnY;

	// 5
	public static void funktionsgleichungBerechnen(int x1, int x2, int y1, int y2) {
		// Regeln:
		// m = (y2 - y1) / (x2 - x1)
		// n = y1 - m * x1
		int deltaX = x2 - x1;
		int deltaY = y2 - y1;
		int m = deltaY / deltaX;
		int n = y1 - m * x1;

		if (n < 0) {
			System.out.println("\n5.Die Gleichung lautet: y = " + m + "x +(" + n + ")");
		} else {
			System.out.println("\n5.Die Gleichung lautet: y = " + m + "x + " + n);
		}
	}

	// 4 - erst X-Achse verschieben
	// x^2 * m/2 + x*n
	// x * x * a + b * x + c
	int funktion = x * m + n;
	
	public static double formel1(double x, double m, double n) {
		double y = m * x + n;
		return y;
	}
	public static double formel2(double x, double a, double b, double c) {
		return (int) (x * x * a + b * x + c);
	}
	public static double flaeche(double m, double n, double start, double ende) {
		double a = m/2;
		double b = n;
		double c = 0;
		return (formel2(ende,a,b,c) - formel2(start,a,b,c));
	}
	public static double flaecheZwGrapUndXAchse(double m, double n, double start, double ende) {
		
		if(formel1(start,m,n) * formel1(ende,m,n) < 0) {
			double punktZero = -n / m;
			return (flaeche(m, n, start, punktZero) * (-1) + flaeche(m, n, punktZero, ende));		
		} else {
			double ergebnis = flaeche(m,n,start,ende);
			if(ergebnis < 0) {
				return (ergebnis * (-1));
			} else {
				return ergebnis;
			}
		}
	}

	// 3
	public static int[] yAchseVerschieben(int[] y, int turnY) {
		System.out.println("3.Wenn der Graphen an der y-Achse beliebig verschoben: ");
		for (int i = 0; i < y.length; i++) {
			y[i] = y[i] + turnY;
			System.out.print("[" + i + "]" + y[i] + "  ");
		}
		return y;
	}

	// 2
	public static int funktionswerteGroesserAlsNull() {
		int anzahl = 0;

		for (int x = 0; x < y.length; x++) {
			if (y[x] > 0) {
				anzahl++;
			}
		}
		System.out.println("\n\n2.Anzahl der Werte größer als 0 = " + anzahl + "\n");
		return anzahl;
	}

	// 1
	public static int[] funktionswerte(int[] y, int x, int m, int n) {
		System.out.println("1.Funktionswerte: ");

		for (x = 0; x < y.length; x++) {
			y[x] = hauptMethode(x, m, n);
			// int ySchieben = yAchseVerschieben(y[x]);
			System.out.print("[" + x + "]" + y[x] + "  ");
		}
		return y;
	}

	// y[] = m * x + n
	public static int hauptMethode(int x, int m, int n) {
		return y[x] = m * x + n;
	}

	public static void main(String[] args) {

		// 1
		funktionswerte(y, x, m, n);

		// 2
		funktionswerteGroesserAlsNull();

		// 3
		yAchseVerschieben(y, 2);

		// 4
		System.out.println("\n\n4.Fläche zwischen dem Graphen und X-Achse = " + flaecheZwGrapUndXAchse(2, -5, 0, 5));	// 12.5
		System.out.println("4.Fläche zwischen dem Graphen und X-Achse = " + flaecheZwGrapUndXAchse(2, -5, -2, 0));	// 14
		System.out.println("4.Fläche zwischen dem Graphen und X-Achse = " + flaecheZwGrapUndXAchse(2, 1, -2, 2));	// 8.5
		
		
		
		// 5
		funktionsgleichungBerechnen(6, 7, hauptMethode(2, 3, 4), hauptMethode(3, 4, 5));
	}
}
