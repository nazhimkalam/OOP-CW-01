package controllers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

// MAKE SURE THAT THE TXT FILE IS EMPTY (which is inside the backend model folder) BEFORE RUNNING THIS TESTS

public class PremierLeagueTester
{
    // variable used
    private LeagueManager obj;

    @Before
    public void beforeTesting(){
        // RUNS BEFORE TESTING
        System.out.println("testing started . . . ");
        obj = PremierLeagueManager.getInstance();
    }

    @Test
    public void testCreatingClub(){
        // TESTING FOR CLUBS AS VALID UP TO 20 CLUBS
        for (int i = 0; i < 20; i++) {
            String result = obj.createClub("Juventus","Spain","Nazhim",null,
                    "normal");
            assertEquals(" Clubs Successfully added!",result);
            System.out.println("Club number: " + i);
        }

        // TESTING FOR AN INVALID CLUB WHEN ADDED MORE THAN 20
        String result = obj.createClub("Juventus","Spain","Nazhim",null,
                "normal");
        assertEquals(" Sorry there is no room for a new club, the maximum number of club limit has been reached!",result);

        // CLEARING THE CONTENT OF THE obj FOR OTHER TESTINGS
        PremierLeagueManager.premierLeagueFootballClubList = new java.util.ArrayList<>();
    }

    @Test
    public void testDeletingClub(){
        // TESTING WITH VALID CLUB TO BE REMOVED
        // adding a club so that it can be deleted
        obj.createClub("Juventus","Spain","Nazhim",null, "normal");

        // getting the details of the added football club
        FootballClub addedClub = PremierLeagueManager.premierLeagueFootballClubList.get(0);

        FootballClub removedFootballClub = (FootballClub) obj.deleteCLub("Juventus");
        assertEquals(addedClub, removedFootballClub);

        // TESTING WITH INVALID CLUB TO BE REMOVED
        removedFootballClub = (FootballClub) obj.deleteCLub("Juventus");
        assertNull(removedFootballClub);

        // CLEARING THE CONTENT OF THE obj FOR OTHER TESTINGS
        PremierLeagueManager.premierLeagueFootballClubList = new java.util.ArrayList<>();
    }

    @Test
    public void testDisplayingStats(){
        // TESTING THE DISPLAY STATS METHOD WITH A VALID CLUB NAME ENTERED
        obj.createClub("Juventus","Spain","Nazhim",null, "normal");
        String result = obj.displayStats("Juventus");
        assertEquals(" Result Displayed", result);

        // TESTING THE DISPLAY STATS METHOD WITH AN INVALID CLUB NAME ENTERED
        result = obj.displayStats("Fake Club");
        assertEquals("\n Sorry, there is no club with the given name 'Fake Club'", result);

        // CLEARING THE CONTENT OF THE obj FOR OTHER TESTINGS
        PremierLeagueManager.premierLeagueFootballClubList = new java.util.ArrayList<>();
    }


    @Test
    public void testAddMatch()
    {
        // Testing adding match into a club
        obj.createClub("barca","spain","nazhim",null,"normal");
        obj.createClub("juventus","japan","hashim",null,"normal");
        obj.createClub("realMadrid","australia","saman",null,"normal");
        DateMatch date = new DateMatch();
        String result;

        // TESTING FOR A VALID MATCH ENTERED FOR A SEASON
        result = obj.addPlayedMatch(
                "2020-21","realMadrid","juventus",1,2,
                date,"away"
        );
        assertEquals("\n Match Successfully added! \n", result);

        // TESTING FOR MULTIPLE VALID MATCHES ENTERED FOR A SEASON (UNTIL 38 PER CLUB)
        for (int i = 0; i < 37; i++) {
            result = obj.addPlayedMatch(
                    "2020-21","realMadrid","juventus",1,2,
                    date,"away"
            );
            assertEquals("\n Match Successfully added! \n", result);
        }

        // TESTING FOR THE 39th MATCH FOR A CLUB FOR A SEASON
        result = obj.addPlayedMatch(
                "2020-21","realMadrid","juventus",1,2,
                date,"away"
        );
        assertEquals("\n Sorry, both the clubs have reached the maximum number of matches played!", result);


        // TESTING FOR A CLUB IN DIFFERENT SEASONS
        for (int i = 0; i < 38; i++) {
            result = obj.addPlayedMatch(
                    "2019-20","realMadrid","juventus",1,2,
                    date,"away"
            );
            assertEquals("\n Match Successfully added! \n", result);
        }

        // TESTING FOR THE 39TH MATCH ADDED FOR THE CLUB WITH A DIFFERENT SEASON
        result = obj.addPlayedMatch(
                "2019-20","realMadrid","juventus",1,2,
                date,"away"
        );
        assertEquals("\n Sorry, both the clubs have reached the maximum number of matches played!", result);

        // TESTING FOR A NUMBER OF CLUBS INVOLVED
        obj.addPlayedMatch(
                "2018-19","barca","juventus",1,2,
                date,"home"
        );

        for (int i = 0; i < 37; i++) {
            result = obj.addPlayedMatch(
                    "2018-19","juventus","realMadrid",1,2,
                    date,"home"
            );
            assertEquals("\n Match Successfully added! \n",result);
        }
        result = obj.addPlayedMatch(
                "2018-19","realMadrid","juventus",1,2,
                date,"home"
        );
        assertEquals("\n Sorry, 'juventus' has reached the maximum number of matches played!",result);

        // CLEARING THE CONTENT OF THE obj FOR OTHER TESTINGS
        PremierLeagueManager.premierLeagueFootballClubList = new java.util.ArrayList<>();
    }

    @Test
    public void testSavingDataIntoFile(){
        // Testing the saving the data into the file
        String result = obj.saveDataIntoFile();
        assertEquals("\n Saving the data . . .\n Successfully saved!", result);

    }

    @Test
    public void testLoadingDataIntoFile(){
        // Testing the loading data from the file method

        // Assuming that the file path is correct and file contains data
        String result = PremierLeagueManager.loadingData();
//        assertEquals("\n Successfully loaded all the data\n", result);

        // Assuming that the file contains no data to read
        assertEquals(" Exception when performing read/write operations to the file!" +
                "\n No permission to read/write from or to the file", result);

    }

    @Test
    public void testClearingDataIntoFile(){
        // Testing the clearing the data from the file method

        // Assuming that the file path is correct
        String result = obj.clearDataFile();
        assertEquals("\n Clearing the contents of the file . . .\n Successfully cleared the file details!", result);

    }

    @Test
    public void testingCheckingForValidClub(){
       // testing for checking valid club method
       obj.createClub("Juventus","Spain","Nazhim",null,
                "normal");
       obj.createClub("Barca","Spain","Hashim",null,
                "normal");
       obj.createClub("Titan Fc","Spain","Kalam",null,
                "normal");

       String[] clubNames = {"Juventus", "Barca", "Titan Fc"};
       for (int i = 0; i < 3; i++) {
           String input = clubNames[i];
           InputStream in = new ByteArrayInputStream(input.getBytes());
           System.setIn(in);
           assertEquals(clubNames[i], ConsoleApplication.checkingForValidClub(input));
        }
//        assertEquals("JuventusFake", ConsoleApplication.checkingForValidClub(input)); This throws error for invalid
    }

    @Test
    public void testingValidatingIntegers(){
        // testing for the validation of integers entered

        for (int i = 0; i < 100; i++) {
            int input = i;
            InputStream in = new ByteArrayInputStream(String.valueOf(input).getBytes());
            System.setIn(in);
            assertEquals(i, ConsoleApplication.validatingIntegers("Testing integers"));
        }
//        assertEquals(14, ConsoleApplication.validatingIntegers("Testing integers")); Invalid number throws error
    }

    @Test
    public void testingValidatingSeason(){
        // testing for the validation of season
        // When testing with invalid data the program throws exception which is common

        String[] seasons = {"2020-21", "2019-20", "2018-19", "2017-18", "2016-17"};
        for (int i = 0; i < 5; i++) {
            String input = seasons[i];
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            assertEquals(seasons[i], ConsoleApplication.validatingSeason());
        }
    }

    @Test
    public void testingValidateString(){
        // testing for valid String entered
        // When testing with invalid data the program throws exception which is common

        String[] validStrings = {"Nazhim", "Kalam", "Mohammed", "Saman", "Lakshan"};
        for (int i = 0; i < 5; i++) {
            String input = validStrings[i];
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            assertEquals(validStrings[i], ConsoleApplication.validateString("Validating Strings"));
        }
    }

    @After
    public void afterTesting(){
        // RUNS AFTER TESTING IS COMPLETED
        System.out.println("testing completed . . .");
    }
}
