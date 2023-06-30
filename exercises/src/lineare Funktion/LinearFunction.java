package aufgabe10;

public class LinearFunction {
	public static void main(String args[]) {
		double[] y = new double[21];

		System.out.println("Es wird ein 20 zelliges Array gefüllt mit Funktionswerten für f(x)= x * 2 - 5  für x in [0,20]");

		y = fillArray(y, 0, 2, -5);

		System.out.println("Es sind " + biggerZero(y) + " Funktionswerte größer als 0.");

		System.out.println("");
		System.out.println("Flächenbetrag im Interval [0,5]: " + absArea(2, -5, 0, 5));
		System.out.println("Flächenbetrag im Interval [0,5]: " + absArea(2, -5, -2, 0));

		System.out.println("");
		makeFunction(0, 1, polynomial1(0, 2, -5), polynomial1(1, 2, -5));
		System.out.println("");
		makeFunction(0, 2.5, polynomial1(0, 2, -5), polynomial1(2.5, 2, -5));
		System.out.println("");
		makeFunction(1, 3, polynomial1(1, 2, -5), polynomial1(3, 2, -5));
		System.out.println("");
		makeFunction(3, 5, polynomial1(3, 2, -5), polynomial1(5, 2, -5));

		System.out.println("");
		System.out.println("offset= 5: ");
		y = offset(y, 5);
		System.out.println("Es sind " + biggerZero(y) + " Funktionswerte" + " größer als 0.");

		System.out.println("");
		System.out.println("offset= -10: ");
		y = offset(y, -10);
		System.out.println("Es sind " + biggerZero(y) + " Funktionswerte" + " größer als 0.");

	}

	/*
	 * fills array with function values f(x) = x* m +n beginning by x= start
	 * iterated by one for each entry up to the end of the array
	 */
	public static double[] fillArray(double y[], double start, double m, double n) {
		for (int i = 0; i < y.length; i++) {
			y[i] = polynomial1(start + i, m, n);
		}
		return y;
	}

	/*
	 * calculates y for function y= x * m + n, a polynomial of grade one
	 */
	public static double polynomial1(double x, double m, double n) {
		return (x * m + n);
	}

	/* counts number of entrys bigger zero */
	public static double biggerZero(double y[]) {
		int count = 0;
		for (int i = 0; i < y.length; i++) {
			if (y[i] > 0) {
				count++;
			}
		}
		return count;
	}

	/* moves graph along ordinate by given value */
	public static double[] offset(double y[], double value) {
		for (int i = 0; i < y.length; i++) {
			y[i] = y[i] + value;
		}
		return y;
	}

	/*
	 * calculates y for function y= x * m + n, a polynomial of grade one
	 */
	public static double polynomial2(double x, double a, double b, double c) {
		return (x * x * a + b * x + c);
	}

	/* calculates the area under the graph for f(x)= x*m + n, x in [a,b] */
	public static double area(double m, double n, double start, double end) {
		/*
		 * define coefficients for a polynomial of grade 2 so that it is the
		 * antiderivative for f(x)= x*m + n, thus F(x)= x^2 * m/2 + x*n
		 */
		double a = m / 2;
		double b = n;
		double c = 0;
		return (polynomial2(end, a, b, c) - polynomial2(start, a, b, c));
	}

	/*
	 * calculates the absolut value of the area between the graph and the
	 * cordinate in an interval
	 */
	public static double absArea(double m, double n, double start, double end) {
		/* is there a root in the interval? */
		if (polynomial1(start, m, n) * polynomial1(end, m, n) < 0) {
			/*
			 * There is a root. Calculate the area under the abscissa, takeits
			 * absolut value and add the area from above the abscissa.
			 */

			/* find root, 0= x*m + n, x= -n/m */
			double root = -n / m;
			return (area(m, n, start, root) * (-1) + area(m, n, root, end));
		} else {
			/* there is no spoon */
			double result = area(m, n, start, end);
			if (result < 0) {
				return result * (-1);
			} else {
				return result;
			}
		}
	}

	public static void makeFunction(double x1, double x2, double y1, double y2) {
		/* define gradient */
		double m = (y2 - y1) / (x2 - x1);
		double n = y1 - m * x1;
		System.out.println("x1: " + x1 + " x2: " + x2 + " y1: " + y1 + " y2: " + y2);
		System.out.println("Funktion ist: y= x*" + m + " +" + n);
	}
}
