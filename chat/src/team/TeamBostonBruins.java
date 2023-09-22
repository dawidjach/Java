package team;

public class TeamBostonBruins {
    public static void main(String[] args) {
        Team BostonBruins = new Team();
        BostonBruins.settingClub("Boston Bruins");
        BostonBruins.settingName("Patrick Wellington");
        BostonBruins.settingNumber(90);
        BostonBruins.settingPosition("goalkeeper");
        
        System.out.println(BostonBruins);

        String BostonBruins2 = BostonBruins.toString();
        System.out.println(BostonBruins2);
    }
}
