package controllers;

// remember to import the Junit library here
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PremierLeagueTester
{
    // For all these tests to run perfectly the objectDataFile.txt has to be empty
    LeagueManager obj;

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

    @After
    public void afterTesting(){
        // RUNS AFTER TESTING IS COMPLETED
        System.out.println("testing completed . . .");
    }
}
