import java.io.Serializable;
import java.util.Objects;

/*
 * @author Nazhim Kalam
 * w1761265
 * Java version 8 or higher required
 * SE2019281
 */
public class ClubStats implements Serializable {

    // variables
    private int totalMatchesPlayed;
    private int totalWins;
    private int totalDraws;
    private int totalDefeats;
    private int totalPointsScored;

    // default constructor
    public ClubStats() {
        totalMatchesPlayed = 0;
        totalWins = 0;
        totalDraws = 0;
        totalDefeats = 0;
        totalPointsScored = 0;
    }

    // Argument constructor
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
        return "totalMatchesPlayed=" + totalMatchesPlayed + ", totalWins=" + totalWins + ", totalDraws=" + totalDraws +
                ", totalDefeats=" + totalDefeats + ", totalPointsScored=" + totalPointsScored;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubStats clubStats = (ClubStats) o;
        return totalMatchesPlayed == clubStats.totalMatchesPlayed &&
                totalWins == clubStats.totalWins &&
                totalDraws == clubStats.totalDraws &&
                totalDefeats == clubStats.totalDefeats &&
                totalPointsScored == clubStats.totalPointsScored;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalMatchesPlayed, totalWins, totalDraws, totalDefeats, totalPointsScored);
    }
}
