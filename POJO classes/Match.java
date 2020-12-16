import java.io.Serializable;
import java.util.Objects;
/*
 * @author Nazhim Kalam
 * w1761265
 * Java version 8 or higher required
 * SE2019281
 */

public class Match implements Serializable {

    // variables used
    private int goalScored;
    private int goalReceived;
    private String season;
    private MatchStats matchStats;
    private DateMatch date;
    private String opponentClubName;
    private String matchType;

    // Default constructor (when ever you create an object the default constructor is called for instantiation)
    public Match(){
        goalScored = 0;
        goalReceived = 0;
        season = "";
        matchStats = new MatchStats();
        date = new DateMatch();
        opponentClubName = "";
        matchType = "";
    }

    // Argument Constructor
    public Match(int goalScored, int goalReceived, String season, MatchStats matchStats, DateMatch date,
                 String opponentClubName, String matchType) {
        this.goalScored = goalScored;
        this.goalReceived = goalReceived;
        this.season = season;
        this.matchStats = matchStats;
        this.date = date;
        this.opponentClubName = opponentClubName;
        this.matchType = matchType;
    }

    // Overriding the toString method
    @Override
    public String toString() {
        return  "goalScored=" + goalScored + ", goalReceived=" + goalReceived + ", season='" + season +
                ", matchStats=" + matchStats + ", date=" + date + ", opponentClubName='" + opponentClubName +
                ", matchType='" + matchType ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return goalScored == match.goalScored &&
                goalReceived == match.goalReceived &&
                season.equals(match.season) &&
                matchStats.equals(match.matchStats) &&
                date.equals(match.date) &&
                opponentClubName.equals(match.opponentClubName) &&
                matchType.equals(match.matchType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalScored, goalReceived, season, matchStats, date, opponentClubName, matchType);
    }

    // gets the date
    public DateMatch getDate() {
        return date;
    }

    // sets the date
    public void setDate(DateMatch date) {
        this.date = date;
    }

    // getting the opponent club name
    public String getOpponentClubName() {
        return opponentClubName;
    }

    // setting the opponent club name
    public void setOpponentClubName(String opponentClubName) {
        this.opponentClubName = opponentClubName;
    }

    // get the season
    public String getSeason() {
        return season;
    }

    // set the season
    public void setSeason(String season) {
        this.season = season;
    }

    public MatchStats getMatchStats() {
        return matchStats;
    }

    public void setMatchStats(MatchStats matchStats) {
        this.matchStats = matchStats;
    }

    public int getGoalScored() {
        return goalScored;
    }

    public void setGoalScored(int goalScored) {
        this.goalScored = goalScored;
    }

    public int getGoalReceived() {
        return goalReceived;
    }

    public void setGoalReceived(int goalReceived) {
        this.goalReceived = goalReceived;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }
}
