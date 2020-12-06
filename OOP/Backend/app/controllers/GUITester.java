//package controllers;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import java.util.ArrayList;
//import java.util.Comparator;
//import static org.junit.Assert.assertEquals;
//
//// MAKE SURE THAT THE TXT FILE IS EMPTY BEFORE RUNNING THIS JUNIT TEST
//public class GUITester {
//
//    // Variables used for testing
//    PremierLeagueManager premierLeagueManager = (PremierLeagueManager) PremierLeagueManager.getInstance();
//    HomeController homeController = new HomeController();
//
//    @Before
//    public void addingDataToFile(){
//        // before testing we add data into the Txt file using the @Before annotation
//        PremierLeagueManager.loadingData();   // load all the data first
//        DateMatch date = new DateMatch(14,12,2020);
//
//        // creating 3 clubs
//        premierLeagueManager.createClub("Barca","Spain","Nazhim",null,
//                "normal");
//        premierLeagueManager.createClub("Juventus","India","Aladin","IIT",
//                "university");
//        premierLeagueManager.createClub("Titan FC","USA","Hashim","RI",
//                "school");
//        premierLeagueManager.createClub("Onco","Africa","Abdul","RI",
//                "school");
//
//        // Add the necessary match which can show difference when ur sorting the data
//        premierLeagueManager.addPlayedMatch("2020-21","Barca","Juventus",
//                18,6,date,"Home");
//
//        premierLeagueManager.addPlayedMatch("2020-21","Titan FC","Juventus",
//                5,4,date,"Home");
//
//        premierLeagueManager.addPlayedMatch("2020-21","Titan FC","Juventus",
//                5,3,date,"Home");
//
//        premierLeagueManager.addPlayedMatch("2020-21","Titan FC","Onco",
//                5,2,date,"Home");
//
//        premierLeagueManager.addPlayedMatch("2020-21","Juventus","Onco",
//                1,0,date,"Home");
//
//        premierLeagueManager.addPlayedMatch("2020-21","Juventus","Onco",
//                2,1,date,"Home");
//        premierLeagueManager.addPlayedMatch("2019-20","Juventus","Onco",
//                2,1,date,"Home");
//
//        // saving the records into the file
//        premierLeagueManager.saveDataIntoFile();
//
//    }
//
//    @Test
//    public void testingGetGuiSeasonFilteredClubs(){
//        // testing the getGuiSeasonFilteredClubs() method
//
//        // loading the records from the file
//        PremierLeagueManager.loadingData();
//
//        ArrayList<FootballClub> seasonList = null;
//        try {
//            // getting the clubs based on the season
//            seasonList = PremierLeagueManager.seasonFilteredFootballCLubList("2020-21");
//
//        } catch (CloneNotSupportedException e) {
//            // Handle exception
//            e.printStackTrace();
//
//        }
//        // testing
//        assertEquals(PremierLeagueManager.premierLeagueFootballClubList.size(),seasonList.size());
//
//    }
//
//    @Test
//    public void testSortingByPoints() {
//        // testing the sortClubsByPoints() method to make sure that the return list of clubs are in sorted order of
//        // points
//
//        // getting the sorted matches by points from
//        ArrayList<FootballClub> sortClubsByPoints = homeController.sortClubsByPoints(PremierLeagueManager.
//                premierLeagueFootballClubList);
//
//        // Comparator to sort by points
//        Comparator<FootballClub> comparatorPoints = (club1, club2) -> {
//            if(club1.getClubStatistics().getTotalPointsScored() < club2.getClubStatistics().getTotalPointsScored()){
//                return 1;
//            }
//
//            return -1;
//        };
//
//        // sort only if there are clubs present
//        if (PremierLeagueManager.premierLeagueFootballClubList != null) {
//            PremierLeagueManager.premierLeagueFootballClubList.sort(comparatorPoints);
//        }
//
//        // testing
//        assertEquals(PremierLeagueManager.premierLeagueFootballClubList,sortClubsByPoints);
//
//    }
//
//    @Test
//    public void testSortingByWins() {
//        // Testing sortClubsByWins() method, if the clubs are sorted my wins or not
//
//        // getting the sorted matches by wins from
//        ArrayList<FootballClub> sortClubsByWins = homeController.sortClubsByWins(
//                PremierLeagueManager.premierLeagueFootballClubList);
//
//        // comparator to sort the clubs by descending order of wins
//        Comparator<FootballClub> comparatorByWins = (club1, club2) -> {
//            if(club1.getClubStatistics().getTotalWins() < club2.getClubStatistics().getTotalWins()){
//                return 1;
//            }
//
//            return -1;
//        };
//
//        // check is the club list is not empty and then only sorts
//        if (PremierLeagueManager.premierLeagueFootballClubList != null) {
//            PremierLeagueManager.premierLeagueFootballClubList.sort(comparatorByWins);
//        }
//
//        // testing
//        assertEquals(PremierLeagueManager.premierLeagueFootballClubList,sortClubsByWins);
//
//    }
//
//    @Test
//    public void testSortingByGoals() {
//        // testing sortClubsByGoals() method, which is used to sort the clubs in descending order of the goal scored
//        ArrayList<FootballClub> sortClubsByGoals = homeController.sortClubsByGoals(
//                PremierLeagueManager.premierLeagueFootballClubList);
//
//        // This comparator is used to sort the clubs in descending order of goals
//        Comparator<FootballClub> comparatorByGoals = (club1, club2) -> {
//            if(club1.getTotalGoalsScored() < club2.getTotalGoalsScored()){
//                return 1;
//            }
//
//            return -1;
//        };
//
//        // check is the club list is not empty and then only sorts
//        if (PremierLeagueManager.premierLeagueFootballClubList != null) {
//            PremierLeagueManager.premierLeagueFootballClubList.sort(comparatorByGoals);
//        }
//
//        // testing
//        assertEquals(PremierLeagueManager.premierLeagueFootballClubList,sortClubsByGoals);
//    }
//
//    @Test
//    public void testAllSeasons(){
//        // testing the allSeasons() method
//
//        // we call this method first because it will do the sorting and filtering of distinct season and setting them.
//        homeController.allSeasons();
//
//        // setting the expected seasons
//        ArrayList<String> expectedSeasons = new ArrayList<>();
//        expectedSeasons.add("2019-20");
//        expectedSeasons.add("2020-21");
//
//        // testing
//        assertEquals(expectedSeasons,PremierLeagueManager.getAllSeasonAdded());
//    }
//
//    @Test
//    public void testAllMatches(){
//        // testing the method which is used to get all the matches for the GUI
//
//        // getting the actual list of matches
//        ArrayList<Match> actualMatches = homeController.
//                getMatchesForSeason(PremierLeagueManager.premierLeagueFootballClubList);
//
//        // getting the expected list of matches
//        ArrayList<Match> expectedMatches = new ArrayList<>();
//        for (FootballClub club: PremierLeagueManager.premierLeagueFootballClubList) {
//            expectedMatches.addAll(club.getMatchesPlayed());
//        }
//
//        // we divide by 2 because in total 1 match is played by the 2 teams not 2 matches
//        // testing
//        assertEquals(expectedMatches.size()/2, actualMatches.size());
//    }
//
//    @Test
//    public void testMatchesByDate(){
//
//        // testing the method which is used to get the matches by date and sort in ascending order of the date
//        ArrayList<FootballClub> actualClubsWithMatchesByDate = new ArrayList<>();
//        try {
//            // getting the actual list of clubs filtered with the matches for the given date
//            actualClubsWithMatchesByDate = homeController.filterMatchesByDate(
//                    PremierLeagueManager.premierLeagueFootballClubList, "2020-12-14"
//            );
//
//        } catch (CloneNotSupportedException e) {
//            // Handling the exception
//            e.printStackTrace();
//
//        }
//
//        // getting the actual list of matches for a given date
//        ArrayList<Match> allActualMatchesByDate = new ArrayList<>();
//
//        // getting the list of expected matches for a given date
//        ArrayList<Match> allExpectedMatchesByDate = new ArrayList<>();
//
//        // setting the actual values
//        for (FootballClub club: actualClubsWithMatchesByDate) {
//            allActualMatchesByDate.addAll(club.getMatchesPlayed());
//        }
//
//        // setting the expected values
//        for (FootballClub club: PremierLeagueManager.premierLeagueFootballClubList) {
//            allExpectedMatchesByDate.addAll(club.getMatchesPlayed());
//        }
//
//        // testing
//        assertEquals(allExpectedMatchesByDate.size(), allActualMatchesByDate.size());
//
//    }
//
//    @After
//    public void completedTesting(){
//        // this runs when test is over
//        System.out.println("Testing completed!");
//    }
//}
