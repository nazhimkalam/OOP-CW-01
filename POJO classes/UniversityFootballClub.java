/*
 * @author Nazhim Kalam
 * w1761265
 * Java version 8 or higher required
 * SE2019281
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class UniversityFootballClub implements Serializable {

    // These are the private variables for Encapsulation
    private String universityName;
    private String name;
    private String location;
    private ClubStats clubStatistics;
    private String coachName;
    private int totalGoalsReceived;
    private int totalGoalsScored;
    private int totalGoalsDifference;
    private int totalYellowCards;
    private int totalRedCards;
    private ArrayList<Match> matchesPlayed;

    // Default constructor (when ever you create an object the default constructor is called for instantiation)
    public UniversityFootballClub() {
        universityName = "";
        name = "";
        location = "";
        clubStatistics = new ClubStats();
        coachName = "";
        totalGoalsReceived = 0;
        totalGoalsScored = 0;
        totalGoalsDifference = 0;
        totalYellowCards = 0;
        totalRedCards = 0;
        matchesPlayed = new ArrayList<>();
    }

    // Argument Constructor
    public UniversityFootballClub(String universityName, String name, String location, ClubStats clubStatistics,
                                  String coachName, int totalGoalsReceived, int totalGoalsScored,
                                  int totalGoalsDifference, int totalYellowCards, int totalRedCards,
                                  ArrayList<Match> matchesPlayed) {
        this.universityName = universityName;
        this.name = name;
        this.location = location;
        this.clubStatistics = clubStatistics;
        this.coachName = coachName;
        this.totalGoalsReceived = totalGoalsReceived;
        this.totalGoalsScored = totalGoalsScored;
        this.totalGoalsDifference = totalGoalsDifference;
        this.totalYellowCards = totalYellowCards;
        this.totalRedCards = totalRedCards;
        this.matchesPlayed = matchesPlayed;
    }

    @Override
    public String toString() {
        return  "universityName='" + universityName + ", name='" + name + ", location='" + location +
                ", clubStatistics=" + clubStatistics + ", coachName='" + coachName +
                ", totalGoalsReceived=" + totalGoalsReceived + ", totalGoalsScored=" + totalGoalsScored +
                ", totalGoalsDifference=" + totalGoalsDifference + ", totalYellowCards=" + totalYellowCards +
                ", totalRedCards=" + totalRedCards + ", matchesPlayed=" + matchesPlayed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityFootballClub that = (UniversityFootballClub) o;
        return totalGoalsReceived == that.totalGoalsReceived &&
                totalGoalsScored == that.totalGoalsScored &&
                totalGoalsDifference == that.totalGoalsDifference &&
                totalYellowCards == that.totalYellowCards &&
                totalRedCards == that.totalRedCards &&
                universityName.equals(that.universityName) &&
                name.equals(that.name) &&
                location.equals(that.location) &&
                clubStatistics.equals(that.clubStatistics) &&
                coachName.equals(that.coachName) &&
                matchesPlayed.equals(that.matchesPlayed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityName, name, location, clubStatistics, coachName, totalGoalsReceived,
                totalGoalsScored, totalGoalsDifference, totalYellowCards, totalRedCards, matchesPlayed);
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ClubStats getClubStatistics() {
        return clubStatistics;
    }

    public void setClubStatistics(ClubStats clubStatistics) {
        this.clubStatistics = clubStatistics;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getTotalGoalsReceived() {
        return totalGoalsReceived;
    }

    public void setTotalGoalsReceived(int totalGoalsReceived) {
        this.totalGoalsReceived = totalGoalsReceived;
    }

    public int getTotalGoalsScored() {
        return totalGoalsScored;
    }

    public void setTotalGoalsScored(int totalGoalsScored) {
        this.totalGoalsScored = totalGoalsScored;
    }

    public int getTotalGoalsDifference() {
        return totalGoalsDifference;
    }

    public void setTotalGoalsDifference(int totalGoalsDifference) {
        this.totalGoalsDifference = totalGoalsDifference;
    }

    public int getTotalYellowCards() {
        return totalYellowCards;
    }

    public void setTotalYellowCards(int totalYellowCards) {
        this.totalYellowCards = totalYellowCards;
    }

    public int getTotalRedCards() {
        return totalRedCards;
    }

    public void setTotalRedCards(int totalRedCards) {
        this.totalRedCards = totalRedCards;
    }

    public ArrayList<Match> getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(ArrayList<Match> matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

}