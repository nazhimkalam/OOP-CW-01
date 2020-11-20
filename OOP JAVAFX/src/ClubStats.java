import java.io.Serializable;

public class ClubStats implements Serializable, Cloneable {
    private static final long serialVersionUID = 1216195785920821563L;

    private int totalMatchesPlayed;
    private int totalWins;
    private int totalDraws;
    private int totalDefeats;
    private int totalPointsScored;

    public ClubStats() {

    }

    public ClubStats(int totalMatchesPlayed, int totalWins, int totalDraws, int totalDefeats,
                     int totalPointsScored) {
        this.totalMatchesPlayed = totalMatchesPlayed;
        this.totalWins = totalWins;
        this.totalDraws = totalDraws;
        this.totalDefeats = totalDefeats;
        this.totalPointsScored = totalPointsScored;
    }

    public int getTotalMatchesPlayed() {
        return totalMatchesPlayed;
    }

    public void setTotalMatchesPlayed(int totalMatchesPlayed) {
        this.totalMatchesPlayed = totalMatchesPlayed;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public int getTotalDraws() {
        return totalDraws;
    }

    public void setTotalDraws(int totalDraws) {
        this.totalDraws = totalDraws;
    }

    public int getTotalDefeats() {
        return totalDefeats;
    }

    public void setTotalDefeats(int totalDefeats) {
        this.totalDefeats = totalDefeats;
    }

    public int getTotalPointsScored() {
        return totalPointsScored;
    }

    public void setTotalPointsScored(int totalPointsScored) {
        this.totalPointsScored = totalPointsScored;
    }

    @Override
    public String toString() {
        return  "\n * Total Matches Played = " + totalMatchesPlayed + "\n * Total Number of Wins = " + totalWins +
                "\n * Total Number of Draws = " + totalDraws + "\n * Total Number of Defeats = " + totalDefeats +
                "\n * Total Points Scored = " + totalPointsScored + "\n";
    }

    // overriding the clone method
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
