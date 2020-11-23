
/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */

public interface LeagueManager {

    // abstract method for creating a club
    String createClub(SportClub club);

    // abstract method for deleting a club
    SportClub deleteCLub(String clubName);

    // abstract method for displaying the statistics
    String displayStats(String clubName);

    // abstract method for displaying the league table results
    void displayLeagueTable(String season);

    // abstract method for adding a played match
    String addPlayedMatch(String seasonPlayed, String clubName_01, String clubName_02,int numberGoalScored_club_1,
                          int numberGoalScored_club_2, DateMatch dateOfMatch, String matchType);

    // abstract method for saving the data into a file
    String saveDataIntoFile();

    // abstract method for clearing the data stored in the file
    String clearDataFile();

}
