import java.util.Random;

public class HockeyGameSimulator {
    public static void main(String[] args) {
        int quarter = 1;
        int time = 0;
        int homeScore = 0;
        int awayScore = 0;
        Random rand = new Random();

        while (quarter <= 3) {
            System.out.println("Start of quarter " + quarter);

            while (time < 1200) {
                // Generate a random event
                int event = rand.nextInt(6) + 1; // 6 events to choose from

                // Determine the time of the event
                int eventTime = rand.nextInt(21) + 1; // Events can occur at any second between 1 and 20
                time += eventTime;

                // Display the event and time
                System.out.printf("Quarter: %d, Time: %d:%02d ", quarter, time / 60, time % 60);

                switch (event) {
                    case 1:
                        System.out.println("Event: Faceoff");
                        break;
                    case 2:
                        System.out.println("Event: Shot on goal");
                        break;
                    case 3:
                        System.out.println("Event: Missed shot");
                        break;
                    case 4:
                        System.out.println("Event: Penalty");
                        // Stop the clock for penalty
                        int penaltyTime = rand.nextInt(121) + 60; // Penalties last between 1 and 2 minutes
                        time += penaltyTime;
                        System.out.printf("Quarter: %d, Time: %d:%02d ", quarter, time / 60, time % 60);
                        System.out.println("Penalty!");
                        break;
                    case 5:
                        System.out.println("Event: Fight");
                        break;
                    case 6:
                        System.out.println("Event: Goal!");
                        // Determine which team scored
                        int scoringTeam = rand.nextInt(2) + 1; // 1 = home team, 2 = away team
                        if (scoringTeam == 1) {
                            homeScore++;
                        } else {
                            awayScore++;
                        }
                        System.out.printf("Quarter: %d, Time: %d:%02d ", quarter, time / 60, time % 60);
                        System.out.printf("Goal for %s team! Score: %d-%d%n", (scoringTeam == 1 ? "Home" : "Away"),
                                homeScore, awayScore);
                        break;
                }

                try {
                    Thread.sleep(1000); // Wait for x seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("End of quarter!");
            quarter++;
            time = 0;
        }

        System.out.println("End of game!");
        System.out.printf("Final score: Home team - %d, Away team - %d%n", homeScore, awayScore);
    }
}
