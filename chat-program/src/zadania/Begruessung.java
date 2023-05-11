package zadania;

public class Begruessung {
    private int alter;
    private String name;
    private char geschlecht;

    public Begruessung(int alter, String name, char geschlecht) {
        this.alter = alter;
        this.name = name;
        this.geschlecht = geschlecht;
    }

    public String toString() {
        if(geschlecht == 'm' && alter >= 18) {
            return("Guten Tag Herr "+name+"!");
        } else if(geschlecht == 'w' && alter >= 18) {
            return("Guten Tag Frau "+name+"!");
        } else {
            return("Hallo "+name+"!");
        }
    }
    
    public static void main(String[] args) {
        Begruessung person1 = new Begruessung(50, "Harald", 'm');
        Begruessung person2 = new Begruessung(40, "Vanessa", 'w');
        Begruessung person3 = new Begruessung(15, "Daniel", 'm');
        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person3);
    }
}
