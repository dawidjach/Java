package zadania;

public class komischeSchlafe {
    private double radius;
    private double hoehe;

    public komischeSchlafe(double radius, double hoehe) {
        this.radius = radius;
        this.hoehe = hoehe;
    }

    public String toString() {
        double volumen = Math.PI * Math.pow(radius, 2) * hoehe;
        return ("Volumen = " + String.format("%.2f", volumen));
    }

    public static void main(String[] args) {
        komischeSchlafe vol01 = new komischeSchlafe(2, 5);
        System.out.println(vol01);
    }
}
