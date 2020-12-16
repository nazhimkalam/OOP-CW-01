import java.util.ArrayList;
import java.util.Objects;
/*
 * @author Nazhim Kalam
 * w1761265
 * Java version 8 or higher required
 * SE2019281
 */

public class PremierLeagueManager {
    //  variables used
    private ArrayList<FootballClub> premierLeagueFootballClubList;

    // Default constructor
    public PremierLeagueManager(){
        premierLeagueFootballClubList = new ArrayList<>();
    }

    // getting the list of football clubs
    public ArrayList<FootballClub> getPremierLeagueFootballClubList() {
        return premierLeagueFootballClubList;
    }

    // setting the list of football clubs
    public void setPremierLeagueFootballClubList(ArrayList<FootballClub> premierLeagueFootballClubList) {
        this.premierLeagueFootballClubList = premierLeagueFootballClubList;
    }

    @Override
    public String toString() {
        return  "premierLeagueFootballClubList=" + premierLeagueFootballClubList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PremierLeagueManager that = (PremierLeagueManager) o;
        return premierLeagueFootballClubList.equals(that.premierLeagueFootballClubList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(premierLeagueFootballClubList);
    }
}
