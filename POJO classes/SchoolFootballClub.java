/*
 * @author Nazhim Kalam
 * w1761265
 * Java version 8 or higher required
 * SE2019281
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class SchoolFootballClub implements Serializable {

    // variables used
    private String schoolName;
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
    public SchoolFootballClub() {
        schoolName = "";
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
    public SchoolFootballClub(String schoolName, String name, String location, ClubStats clubStatistics,
                              String coachName, int totalGoalsReceived, int totalGoalsScored, int totalGoalsDifference,
                              int totalYellowCards, int totalRedCards, ArrayList<Match> matchesPlayed) {
        this.schoolName = schoolName;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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

    @Override
    public String toString() {
        return  "schoolName='" + schoolName + ", name='" + name + ", location='" + location +
                ", clubStatistics=" + clubStatistics + ", coachName='" + coachName +
                ", totalGoalsReceived=" + totalGoalsReceived + ", totalGoalsScored=" + totalGoalsScored +
                ", totalGoalsDifference=" + totalGoalsDifference + ", totalYellowCards=" + totalYellowCards +
                ", totalRedCards=" + totalRedCards + ", matchesPlayed=" + matchesPlayed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return totalGoalsReceived == that.totalGoalsReceived &&
                totalGoalsScored == that.totalGoalsScored &&
                totalGoalsDifference == that.totalGoalsDifference &&
                totalYellowCards == that.totalYellowCards &&
                totalRedCards == that.totalRedCards &&
                schoolName.equals(that.schoolName) &&
                name.equals(that.name) &&
                location.equals(that.location) &&
                clubStatistics.equals(that.clubStatistics) &&
                coachName.equals(that.coachName) &&
                matchesPlayed.equals(that.matchesPlayed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolName, name, location, clubStatistics, coachName, totalGoalsReceived, totalGoalsScored, totalGoalsDifference, totalYellowCards, totalRedCards, matchesPlayed);
    }
}
