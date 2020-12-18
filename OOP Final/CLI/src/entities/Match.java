package entities;
import java.io.Serializable;

/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */

public class Match implements Serializable, Cloneable {

    // variables used
    private int goalScored;
    private int goalReceived;
    private String season;
    private MatchStats matchStats;
    private DateMatch date;
    private String opponentClubName;
    private String matchType;
    private String participatedCLubName;

    // default constructor
    public Match(){

    }

    // Argument Constructor
    public Match(int goalScored, int goalReceived, MatchStats matchStats, DateMatch date,
                 String opponentClubName,String season, String matchType, String participatedCLubName) {

        this.goalScored = goalScored;
        this.goalReceived = goalReceived;
        this.date = date;
        this.opponentClubName = opponentClubName;
        this.matchStats = matchStats;
        this.season = season;
        this.matchType = matchType;
        this.participatedCLubName = participatedCLubName;

    }

    // overriding the toString method in order to display the details of the match
    @Override
    public String toString() {
        return "\n Goal Scored = " + goalScored +
                "\n Goal Received = " + goalReceived +
                "\n Season = " + season +
                "\n Date = " + date +
                "\n Opponent Club Name = " + opponentClubName +
                 matchStats.toString();
    }

    // SETTERS AND GETTERS FOR THE CLASS
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

    public String getParticipatedCLubName() {
        return participatedCLubName;
    }

    public void setParticipatedCLubName(String participatedCLubName) {
        this.participatedCLubName = participatedCLubName;
    }

    // overriding the clone method, in order to enable cloning of the match when needed to
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
