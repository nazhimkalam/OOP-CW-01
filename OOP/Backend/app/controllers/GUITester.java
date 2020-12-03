//package controllers;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import play.mvc.Result;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//
//import static org.junit.Assert.assertEquals;
//
//
//// Remember to clear the file before running this Junit
//public class GUITester {
//    PremierLeagueManager premierLeagueManager = (PremierLeagueManager) PremierLeagueManager.getInstance();
//    HomeController homeController = new HomeController();
//
//    @Before
//    public void addingDataToFile(){
//        PremierLeagueManager.loadingData();   // load all the data first
//        DateMatch date = new DateMatch(14,12,2020);
//
//        // create 3 clubs
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
//
//    }
//
//    @Test
//    public void testingGetGuiSeasonFilteredClubs(){
//        // loading the records from the file
//        PremierLeagueManager.loadingData();
//        ArrayList<FootballClub> seasonList = null;
//        try {
//            seasonList = PremierLeagueManager.seasonFilteredFootballCLubList("2020-21");
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        assertEquals(PremierLeagueManager.premierLeagueFootballClubList.size(),seasonList.size());
//    }
//
//    @Test
//    public void testSortingByPoints() {
//
//        // getting the sorted matches by points from
//        ArrayList<FootballClub> sortClubsByPoints = homeController.sortMatchesByPoints(PremierLeagueManager.
//                premierLeagueFootballClubList);
//        Comparator<FootballClub> comparatorPoints = (club1, club2) -> {
//            if(club1.getClubStatistics().getTotalPointsScored() < club2.getClubStatistics().getTotalPointsScored()){
//                return 1;
//            }
//
//            return -1;
//        };
//        if (PremierLeagueManager.premierLeagueFootballClubList != null) {
//            PremierLeagueManager.premierLeagueFootballClubList.sort(comparatorPoints);
//        }
//
//        assertEquals(PremierLeagueManager.premierLeagueFootballClubList,sortClubsByPoints);
//
//    }
//
//    @Test
//    public void testSortingByWins() {
//
//        // getting the sorted matches by wins from
//        ArrayList<FootballClub> sortClubsByWins = homeController.sortMatchesByWins(PremierLeagueManager.premierLeagueFootballClubList);
//        Comparator<FootballClub> comparatorByWins = (club1, club2) -> {
//            if(club1.getClubStatistics().getTotalWins() < club2.getClubStatistics().getTotalWins()){
//                return 1;
//            }
//
//            return -1;
//        };
//        if (PremierLeagueManager.premierLeagueFootballClubList != null) {
//            PremierLeagueManager.premierLeagueFootballClubList.sort(comparatorByWins);
//        }
//
//        assertEquals(PremierLeagueManager.premierLeagueFootballClubList,sortClubsByWins);
//
//
//    }
//
//    @Test
//    public void testSortingByGoals() {
//        // getting the sorted matches by wins from
//        ArrayList<FootballClub> sortClubsByGoals = homeController.sortMatchesByGoals(PremierLeagueManager.premierLeagueFootballClubList);
//        Comparator<FootballClub> comparatorByGoals = (club1, club2) -> {
//            if(club1.getTotalGoalsScored() < club2.getTotalGoalsScored()){
//                return 1;
//            }
//
//            return -1;
//        };
//        if (PremierLeagueManager.premierLeagueFootballClubList != null) {
//            PremierLeagueManager.premierLeagueFootballClubList.sort(comparatorByGoals);
//        }
//
//        assertEquals(PremierLeagueManager.premierLeagueFootballClubList,sortClubsByGoals);
//    }
//
//    @Test
//    public void testAllSeasons(){
//        homeController.allSeasons();
//        ArrayList<String> expectedSeasons = new ArrayList<>();
//        expectedSeasons.add("2019-20");
//        expectedSeasons.add("2020-21");
//        assertEquals(expectedSeasons,PremierLeagueManager.getAllSeasonAdded());
//    }
//
//    @Test
//    public void testAllMatches(){
//
//        ArrayList<Match> actualMatches = homeController.
//                getMatchesForSeason(PremierLeagueManager.premierLeagueFootballClubList);
//
//        ArrayList<Match> expectedMatches = new ArrayList<>();
//        for (FootballClub club: PremierLeagueManager.premierLeagueFootballClubList) {
//            for (Match match: club.getMatchesPlayed()) {
//                expectedMatches.add(match);
//            }
//        }
//
//        // we divide by 2 because in total 1 match is played by the 2 teams not 2 matches
//        assertEquals(expectedMatches.size()/2, actualMatches.size());
//    }
//
//    @Test
//    public void testMatchesByDate(){
//        ArrayList<FootballClub> actualClubsWithMatchesByDate = new ArrayList<>();
//        try {
//            actualClubsWithMatchesByDate = homeController.filterMatchesByDate(
//                    PremierLeagueManager.premierLeagueFootballClubList, "2020-12-14"
//            );
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//
//        ArrayList<Match> allActualMatchesByDate = new ArrayList<>();
//        ArrayList<Match> allExpectedMatchesByDate = new ArrayList<>();
//
//        for (FootballClub club: actualClubsWithMatchesByDate) {
//            allActualMatchesByDate.addAll(club.getMatchesPlayed());
//        }
//
//        for (FootballClub club: PremierLeagueManager.premierLeagueFootballClubList) {
//            allExpectedMatchesByDate.addAll(club.getMatchesPlayed());
//        }
//
//        assertEquals(allExpectedMatchesByDate.size(), allActualMatchesByDate.size());
//
//    }
//
//    @After
//    public void completedTesting(){
//        System.out.println("Testing completed!");
//    }
//}
