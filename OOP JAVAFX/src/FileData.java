
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileData {
    private SimpleIntegerProperty position;
    private SimpleStringProperty clubName;
    private SimpleIntegerProperty numberOfMatchesPlayed;
    private SimpleIntegerProperty wins;
    private SimpleIntegerProperty draws;
    private SimpleIntegerProperty losses;
    private SimpleIntegerProperty goalScored;
    private SimpleIntegerProperty goalReceived;
    private SimpleIntegerProperty goalDifference;
    private SimpleIntegerProperty points;

    public FileData(int position, String clubName,
                    int numberOfMatchesPlayed, int wins,
                    int draws, int losses, int goalScored,
                    int goalReceived, int goalDifference,
                    int points) {

        this.position = new SimpleIntegerProperty(position);
        this.clubName = new SimpleStringProperty(clubName);
        this.numberOfMatchesPlayed = new SimpleIntegerProperty(numberOfMatchesPlayed);
        this.wins = new SimpleIntegerProperty(wins);
        this.draws = new SimpleIntegerProperty(draws);
        this.losses = new SimpleIntegerProperty(losses);
        this.goalScored = new SimpleIntegerProperty(goalScored);
        this.goalReceived = new SimpleIntegerProperty(goalReceived);
        this.goalDifference = new SimpleIntegerProperty(goalDifference);
        this.points = new SimpleIntegerProperty(points);
    }

    public int getPosition() {
        return position.get();
    }

    public void setPosition(int position) {
        this.position.set(position);
    }

    public String getClubName() {
        return clubName.get();
    }

    public void setClubName(String clubName) {
        this.clubName.set(clubName);
    }

    public int getNumberOfMatchesPlayed() {
        return numberOfMatchesPlayed.get();
    }

    public void setNumberOfMatchesPlayed(int numberOfMatchesPlayed) {
        this.numberOfMatchesPlayed.set(numberOfMatchesPlayed);
    }

    public int getWins() {
        return wins.get();
    }

    public void setWins(int wins) {
        this.wins.set(wins);
    }

    public int getDraws() {
        return draws.get();
    }

    public void setDraws(int draws) {
        this.draws.set(draws);
    }

    public int getLosses() {
        return losses.get();
    }

    public void setLosses(int losses) {
        this.losses.set(losses);
    }

    public int getGoalScored() {
        return goalScored.get();
    }

    public void setGoalScored(int goalScored) {
        this.goalScored.set(goalScored);
    }

    public int getGoalReceived() {
        return goalReceived.get();
    }

    public void setGoalReceived(int goalReceived) {
        this.goalReceived.set(goalReceived);
    }

    public int getGoalDifference() {
        return goalDifference.get();
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference.set(goalDifference);
    }

    public int getPoints() {
        return points.get();
    }

    public void setPoints(int points) {
        this.points.set(points);
    }
}
