import java.util.Random;

public class HockeyGameSimulation {
    public static void main(String[] args) throws InterruptedException {
        int periodLength = 1200; // czas trwania jednej tercji w minutach
        int numPeriods = 3; // liczba tercji w meczu
        int gameTime = 0; // czas gry w sekundach
        int homeScore = 0; // liczba bramek zdobytych przez drużynę gospodarzy
        int awayScore = 0; // liczba bramek zdobytych przez drużynę gości
        Random rand = new Random(); // generator liczb losowych

        // pętla po tercjach meczu
        for (int period = 1; period <= numPeriods; period++) {
            System.out.println("\nTercja " + period);

            // pętla po czasie gry w tercji
            for (int time = 1; time <= periodLength; time += 30) {
                int eventTime = rand.nextInt(time); // losowy czas wydarzenia
                gameTime += eventTime;

                if (eventTime >= gameTime) {
                    gameTime += eventTime;
                }
                // koniec tercji jeśli upłynął czas
                if (gameTime >= periodLength) {
                    gameTime = 0;
                    break;
                }

                // losowe wydarzenie
                int event = rand.nextInt(6); // 6 możliwych wydarzeń
                String eventType = "";
                int teamScored = 0;

                switch (event) {
                    case 0:
                        eventType = "Podanie na druga strone";
                        break;
                    case 1:
                        eventType = "Walka przy bandzie";
                        break;
                    case 2:
                        eventType = "Odbior pucka";
                        break;
                    case 3:
                        eventType = "Strzal na bramke";
                        if (rand.nextInt(10) < 9) { // 90% szans na trafienie bramki
                            teamScored = rand.nextInt(2) == 0 ? 1 : -1; // losowa drużyna zdobywa bramkę
                            if (teamScored == 1) {
                                homeScore++;
                            } else {
                                awayScore++;
                            }
                        }
                        break;
                    case 4:
                        eventType = "Zmiana linii. Nowe piatki wchodza na tafle.";
                        break;
                    case 5:
                        int penaltyTeam = rand.nextInt(2); // losowanie drużyny na karę (0 dla gospodarzy, 1 dla
                                                           // gości)
                        eventType = "Dwuminutowa kara dla "
                                + (penaltyTeam == 0 ? "Gospodarzy" : "Gosci");
                        break;
                }

                // wyświetlenie wydarzenia w konsoli
                String teamName = teamScored == 1 ? "Gospodarze" : teamScored == -1 ? "Goscie" : "";
                System.out.printf("%02d:%02d - %s%s%n", gameTime / 60, gameTime % 60, eventType,
                        teamName.isEmpty() ? ""
                                : " (GOOOL! " + teamName + " zdobyli bramke!) " + homeScore + "-"
                                        + awayScore);

                // pauza x sek
                Thread.sleep(3000);
            }
        }

        // wynik meczu
        System.out.println("Wynik meczu:");
        System.out.printf("Gospodarze %d - %d Goscie%n", homeScore, awayScore);
    }
}