package team;

public class Team {
    private int number;
    private String name;
    private String position;
    private String club;

    public void settingNumber(int newNumber) {
        number = newNumber;
    }

    public void settingName(String newName) {
        name = newName;
    }

    public void settingPosition(String newPosition) {
        position = newPosition;
    }

    public void settingClub(String newClub) {
        club = newClub;
    }

    public String toString() {
        return(name + " plays for " + club + " as " + position + " with the number " + number + ".");
    }
}
