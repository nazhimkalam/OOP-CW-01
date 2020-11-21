
/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * Java version 8 or higher required
 */


public interface LeagueManager {

    // abstract method for creating a club
     void createClub();

    // abstract method for deleting a club
     void deleteCLub();

    // abstract method for displaying the statistics
     void displayStats();

    // abstract method for displaying the league table results
     void displayLeagueTable();

    // abstract method for adding a played match
    void addPlayedMatch();

    // abstract method for saving the data into a file
    void saveDataIntoFile();

    // abstract method for clearing the data stored in the file
    void clearDataFile();

}
