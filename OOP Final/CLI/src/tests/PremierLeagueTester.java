package tests;
import console.ConsoleApplication;
import entities.DateMatch;
import entities.FootballClub;
import entities.LeagueManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.PremierLeagueManager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


// MAKE SURE THAT THE TXT FILE IS EMPTY (which is inside the backend model folder) BEFORE RUNNING THIS TESTS
public class PremierLeagueTester
{
    // variable used
    private LeagueManager premierLeagueManager;

    @Before
    public void beforeTesting(){

        // RUNS BEFORE TESTING
        System.out.println("testing started . . . ");
        premierLeagueManager = PremierLeagueManager.getInstance();
    }

    @Test
    public void testCreatingClub(){

        // TESTING FOR CLUBS AS VALID UP TO 20 CLUBS
        String[] clubType = {"normal","university","school"};
        String[] schoolUniName = {null, "IIT", "RoyalInstitute"};

        for (int index = 0; index < clubType.length; index++) {

            for (int num = 0; num < 20; num++) {

                String result = premierLeagueManager.createClub("Juventus","Spain","Nazhim",
                        schoolUniName[index],
                        clubType[index]);
                assertEquals(" Clubs Successfully added!",result);
                System.out.println("Club number: " + num);
            }

            // TESTING FOR AN INVALID CLUB WHEN ADDED MORE THAN 20
            String expectedResult = premierLeagueManager.createClub("Juventus","Spain","Nazhim",
                    schoolUniName[index],
                    clubType[index]);
            assertEquals(" Sorry there is no room for a new club, the maximum number of club limit " +
                    "has been reached!",expectedResult);

            // CLEARING THE CONTENT OF THE obj FOR OTHER TESTINGS
            PremierLeagueManager.setPremierLeagueFootballClubList(new ArrayList<>());
        }
    }

    @Test
    public void testDeletingClub(){

        // TESTING WITH VALID CLUB TO BE REMOVED
        // adding a club so that it can be deleted
        premierLeagueManager.createClub("Juventus","Spain","Nazhim",null,
                "normal");

        // getting the details of the added football club
        FootballClub actualResult = PremierLeagueManager.getPremierLeagueFootballClubList().get(0);

        FootballClub expectedResult = (FootballClub) premierLeagueManager.deleteClub("Juventus");
        assertEquals(actualResult, expectedResult);

        // TESTING WITH INVALID CLUB TO BE REMOVED
        expectedResult = (FootballClub) premierLeagueManager.deleteClub("Real Madird");
        assertNull(expectedResult);

        // CLEARING THE CONTENT OF THE obj FOR OTHER TESTINGS
        PremierLeagueManager.setPremierLeagueFootballClubList(new ArrayList<>());
    }

    @Test
    public void testDisplayingStats(){

        // TESTING THE DISPLAY STATS METHOD WITH A VALID CLUB NAME ENTERED
        premierLeagueManager.createClub("Juventus","Spain","Nazhim",null,
                "normal");
        String expectedResult = premierLeagueManager.displayStats("Juventus");
        assertEquals(" Result Displayed", expectedResult);

        // TESTING THE DISPLAY STATS METHOD WITH AN INVALID CLUB NAME ENTERED
        expectedResult = premierLeagueManager.displayStats("Fake Club");
        assertEquals("\n Sorry, there is no club with the given name 'Fake Club'", expectedResult);

        // CLEARING THE CONTENT OF THE obj FOR OTHER TESTINGS
        PremierLeagueManager.setPremierLeagueFootballClubList(new ArrayList<>());
    }


    @Test
    public void testAddPlayedMatch()
    {
        // SINCE THERE ARE 3 CLUBS HERE THEN 1 CLUBS PLAYS 4 MATCHES
        // Testing adding match into a club
        premierLeagueManager.createClub("barca","spain","nazhim",null,
                "normal");
        premierLeagueManager.createClub("juventus","japan","hashim",null,
                "normal");
        premierLeagueManager.createClub("realMadrid","australia","saman",null,
                "normal");

        DateMatch date = new DateMatch();
        String expectedResult;
        String[] seasons = {"2020-21", "2019-20","2018-19"};

        for(String season: seasons){
            // TESTING FOR A VALID MATCH ENTERED FOR A SEASON of match type "Away"
            // REAL MADRID VS JUVENTUS "away" $$$$$$$
            expectedResult = premierLeagueManager.addPlayedMatch(
                    season,"realMadrid","juventus",1,
                    2,
                    date,"away"
            );
            assertEquals("\n Match Successfully added! \n", expectedResult);

            // TESTING FOR A VALID MATCH ENTERED FOR A SEASON of match type "Home"
            // REAL MADRID VS JUVENTUS "home" $$$$$$$
            expectedResult = premierLeagueManager.addPlayedMatch(
                    season,"realMadrid","juventus",1,
                    2,
                    date,"home"
            );
            assertEquals("\n Match Successfully added! \n", expectedResult);

            // TESTING FOR A DUPLICATE MATCH ADDED FOR THE SAME "season", "teams" and "match type"
            // REAL MADRID VS JUVENTUS "away"
            expectedResult = premierLeagueManager.addPlayedMatch(
                    season,"realMadrid","juventus",1,
                    2,
                    date,"away"
            );
            assertEquals("\n Sorry can't add match, because it's already played for the given teams, season and" +
                    " match type! \n", expectedResult);

            // TESTING FOR A DUPLICATE MATCH ADDED FOR THE SAME "season", "teams" and "match type"
            // REAL MADRID VS JUVENTUS "home"
            expectedResult = premierLeagueManager.addPlayedMatch(
                    season,"realMadrid","juventus",1,
                    2,
                    date,"home"
            );
            assertEquals("\n Sorry can't add match, because it's already played for the given teams, season and" +
                    " match type! \n", expectedResult);


            // TESTING FOR MULTIPLE VALID MATCHES ENTERED FOR A SEASON (UNTIL MAXIMUM NUMBER OF MATCHES PER CLUB REACHED)
            // Real Madrid and juventus has 2 more matches to play inf order to reach the max number of matches played
            // Barca VS Juventus "away" $$$$$$$
            expectedResult = premierLeagueManager.addPlayedMatch(
                    season,"barca","juventus",1,
                    2,
                    date,"away"
            );
            assertEquals("\n Match Successfully added! \n", expectedResult);

            // Barca VS Juventus "home" $$$$$$$
            expectedResult = premierLeagueManager.addPlayedMatch(
                    season,"barca","juventus",1,
                    2,
                    date,"home"
            );
            assertEquals("\n Match Successfully added! \n", expectedResult);

            // TESTING FOR ADDING A MATCH WHICH EXCEEDS THE LIMIT for "Juventus"
            expectedResult = premierLeagueManager.addPlayedMatch(
                    season,"barca","juventus",1,
                    2,
                    date,"away"
            );
            assertEquals("\n Sorry, 'juventus' has reached the maximum number of matches played!",
                    expectedResult);
        }

        // Barca VS Real Madrid "away" $$$$$
        expectedResult = premierLeagueManager.addPlayedMatch(
                "2020-21","barca","realMadrid",1,
                2,
                date,"away"
        );
        assertEquals("\n Match Successfully added! \n", expectedResult);

        // Barca VS Real Madrid "home" $$$$$
        expectedResult = premierLeagueManager.addPlayedMatch(
                "2020-21","barca","realMadrid",1,
                2,
                date,"home"
        );
        assertEquals("\n Match Successfully added! \n", expectedResult);

        // TESTING FOR ADDING A MATCH WHICH EXCEEDS THE LIMIT for "barca"
        expectedResult = premierLeagueManager.addPlayedMatch(
                "2020-21","barca","juventus",1,
                2,
                date,"away"
        );
        assertEquals("\n Sorry, both the clubs have reached the maximum number of matches played!",
                expectedResult);

        // CLEARING THE CONTENT OF THE obj FOR OTHER TESTINGS
        PremierLeagueManager.setPremierLeagueFootballClubList(new ArrayList<>());
    }

    @Test
    public void testSavingDataIntoFile(){
        // Testing the saving the data into the file
        String expectedResult = premierLeagueManager.saveDataIntoFile();
        assertEquals("\n Saving the data . . .\n Successfully saved!", expectedResult);

    }

    @Test
    public void testLoadingDataIntoFile(){
        // Testing the loading data from the file method

        // Assuming that the file path is correct and file contains data
        String expectedResult = PremierLeagueManager.loadingData();
        // assertEquals("\n Successfully loaded all the data\n", expectedResult);

        // Assuming that the file contains no data to read
        assertEquals(" Exception when performing read/write operations to the file!" +
                "\n No permission to read/write from or to the file", expectedResult);

    }

    @Test
    public void testClearingDataIntoFile(){
        // Testing the clearing the data from the file method

        // Assuming that the file path is correct
        String result = premierLeagueManager.clearDataFile();
        assertEquals("\n Clearing the contents of the file . . .\n Successfully cleared the file details!",
                result);

    }

    @Test
    public void testingCheckingForValidClub(){
       // testing for checking valid club method
        premierLeagueManager.createClub("Juventus","Spain","Nazhim",null,
                "normal");
        premierLeagueManager.createClub("Barca","Spain","Hashim",null,
                "normal");
        premierLeagueManager.createClub("Titan Fc","Spain","Kalam",null,
                "normal");

       String[] clubNames = {"Juventus", "Barca", "Titan Fc"};
       for (int index = 0; index < 3; index++) {

           String input = clubNames[index];
           InputStream in = new ByteArrayInputStream(input.getBytes());
           System.setIn(in);
           Assert.assertEquals(clubNames[index], ConsoleApplication.checkingForValidClub(input));
        }

       // This throws error for invalid clubName as expected
       // assertEquals("JuventusFake", ConsoleApplication.checkingForValidClub(input));
    }

    @Test
    public void testingValidatingIntegers(){
        // testing for the validation of integers entered

        for (int index = 0; index < 100; index++) {
            InputStream in = new ByteArrayInputStream(String.valueOf(index).getBytes());
            System.setIn(in);
            assertEquals(index, ConsoleApplication.validatingIntegers("Testing integers"));
        }

        // Invalid number throws error for invalid integer as expected
       //  assertEquals(14, ConsoleApplication.validatingIntegers("Testing integers"));
    }

    @Test
    public void testingValidatingSeason(){
        // testing for the validation of season
        // When testing with invalid data the program throws exception which is common

        String[] seasons = {"2020-21", "2019-20", "2018-19", "2017-18", "2016-17"};
        for (int index = 0; index < 5; index++) {
            String input = seasons[index];
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            assertEquals(seasons[index], ConsoleApplication.validatingSeason());
        }

        // Invalid Season Format String Entered, this throws an error as expected
        // String invalidSeason = "21-2020";
        // InputStream in = new ByteArrayInputStream(invalidSeason.getBytes());
        // System.setIn(in);
        // assertEquals("21-2020", ConsoleApplication.validatingSeason());
    }

    @Test
    public void testingValidateString(){
        // testing for valid String entered
        // When testing with invalid data the program throws exception which is common

        String[] validStrings = {"Nazhim", "Kalam", "Mohammed", "Saman", "Lakshan"};
        for (int index = 0; index < 5; index++) {
            String input = validStrings[index];
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            assertEquals(validStrings[index], ConsoleApplication.validateString("Validating Strings"));
        }
    }

    @After
    public void afterTesting(){
        // RUNS AFTER TESTING IS COMPLETED
        System.out.println("testing completed . . .");
    }
}


// References used
// https://www.youtube.com/playlist?list=PLqq-6Pq4lTTa4ad5JISViSb2FVG8Vwa4o