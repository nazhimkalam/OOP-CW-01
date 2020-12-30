package utils;

import entities.DateMatch;
import entities.FootballClub;
import entities.Match;
import entities.LeagueManager;
import services.PremierLeagueManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

public class PremierLeagueUtil {

    private static ArrayList<FootballClub> guiSeasonFilteredClubs;

    public static ArrayList<String> allSeasons(){

        // loading the data from the file
        PremierLeagueManager.loadingData();

        // sort the seasons using the comparator
        Comparator<String> comparator = (season1, season2) -> {

            if(Integer.parseInt(season1.split("-")[0]) > Integer.parseInt(season2.split("-")[0])){
                return 1;
            }
            return -1;

        };

        // setting the seasons with distinct seasons only
        PremierLeagueManager.setAllSeasonAdded((ArrayList<String>)
                PremierLeagueManager.getAllSeasonAdded().stream().distinct().collect(Collectors.toList()));

        // sorting the seasons
        PremierLeagueManager.getAllSeasonAdded().sort(comparator);

        // getting the seasons and return them
        return PremierLeagueManager.getAllSeasonAdded();
    }

    public static ArrayList<FootballClub> sortByPoints(String season){

        // loading the data from the file
        PremierLeagueManager.loadingData();

        // filters the football clubs according to the season
        guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

        // sorting by points only in descending order
        guiSeasonFilteredClubs = sortClubsByPoints(guiSeasonFilteredClubs);

        return guiSeasonFilteredClubs;
    }

    // This function is to return the listOfClubs filtered by season
    public static ArrayList<FootballClub> getGuiSeasonFilteredClubs(String season){

        try {
            // get the clubs filtered by season
            guiSeasonFilteredClubs = PremierLeagueManager.seasonFilteredFootballCLubList(season);

        } catch (CloneNotSupportedException e) {
            // Handles the exception
            e.printStackTrace();

        }
        return guiSeasonFilteredClubs;

    }

    // This function is used to sort the matches of a football club in a season by descending order of points
    public static ArrayList<FootballClub> sortClubsByPoints(ArrayList<FootballClub> guiSeasonFilteredClubs) {

        // comparator to sort the clubs by points
        Comparator<FootballClub> comparator = (club1, club2) -> {

            if(club1.getClubStatistics().getTotalPointsScored() == (club2.getClubStatistics()
                    .getTotalPointsScored())){

                if(club1.getTotalGoalsScored() < club2.getTotalGoalsScored()){
                    return 1;

                }

            }else{

                if(club1.getClubStatistics().getTotalPointsScored() < club2.getClubStatistics()
                        .getTotalPointsScored()){
                    return 1;

                }
            }
            return -1;
        };

        // sorting only if there are clubs to sort
        if (guiSeasonFilteredClubs != null) {
            guiSeasonFilteredClubs.sort(comparator);

        }

        return guiSeasonFilteredClubs;

    }

    public static ArrayList<FootballClub> sortByWins(String season){

        // loading the data from the file
        PremierLeagueManager.loadingData();

        // filters the football clubs according to the season
        guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

        // sorting by points only in descending order of wins
        guiSeasonFilteredClubs = sortClubsByWins(guiSeasonFilteredClubs);

        return guiSeasonFilteredClubs;
    }

    // sorting by points only in descending order of wins
    public static ArrayList<FootballClub> sortClubsByWins(ArrayList<FootballClub> guiSeasonFilteredClubs) {

        // comparator to sort the clubs in descending order of the their wins
        Comparator<FootballClub> comparator = (club1, club2) -> {

            if(club1.getClubStatistics().getTotalWins() < club2.getClubStatistics().getTotalWins()){
                return 1;
            }

            return -1;
        };

        // sorting only if there are clubs to sort
        if (guiSeasonFilteredClubs != null) {
            guiSeasonFilteredClubs.sort(comparator);

        }

        return guiSeasonFilteredClubs;
    }

    public static ArrayList<FootballClub> sortByGoals(String season){

        // loading the data from the file
        PremierLeagueManager.loadingData();

        // filters the football clubs according to the season
        guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

        // sorting by points only in descending order goal scored
        guiSeasonFilteredClubs = sortClubsByGoals(guiSeasonFilteredClubs);

        return guiSeasonFilteredClubs;
    }

    // sorting by points only in descending order goal scored
    public static ArrayList<FootballClub> sortClubsByGoals(ArrayList<FootballClub> guiSeasonFilteredClubs) {

        // comparator for sorting
        Comparator<FootballClub> comparator = (club1, club2) -> {

            if(club1.getTotalGoalsScored() < club2.getTotalGoalsScored()){
                return 1;
            }

            return -1;
        };

        // checks if clubs are present to sort
        if (guiSeasonFilteredClubs != null) {
            guiSeasonFilteredClubs.sort(comparator);

        }
        return guiSeasonFilteredClubs;
    }

    public static ArrayList<Match> allMatches(String season){

        // loading the data from the file
        PremierLeagueManager.loadingData();

        // getting the clubs with the filtered matches by season
        guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

        // getting the matches filtered by season
        ArrayList<Match> matchesDisplayed = getMatchesForSeason(guiSeasonFilteredClubs);

        return matchesDisplayed;
    }

    // This returns a list of matches for a given season
    public static ArrayList<Match> getMatchesForSeason(ArrayList<FootballClub> seasonBasedClub){

        // these both arrayList will be of the same size
        ArrayList<Match> matchesDisplayed = new ArrayList<>();
        ArrayList<Match> allMatches = new ArrayList<>();

        // populating the allMatches list will all the matches from the seasonBasedClub
        // adding all the matches played for that season inside the allMatches list
        for (FootballClub footballClub: seasonBasedClub) {
            allMatches.addAll(footballClub.getMatchesPlayed());

        }

        // sort the matches in ascending order of the date
        Comparator<Match> sortByDate = (match1, match2) -> {

            if(match1.getDate().getYear() == match2.getDate().getYear()){
                if (match1.getDate().getMonth() == match2.getDate().getMonth()) {
                    if (match1.getDate().getDay() > match2.getDate().getDay()) {
                        return 1;
                    }
                } else if (match1.getDate().getMonth() > match2.getDate().getMonth()) {
                    return 1;
                }
            }else if (match1.getDate().getYear() > match2.getDate().getYear()) {
                return 1;
            }

            return -1;
        };
        allMatches.sort(sortByDate);  // sorting the matches according to the date

        // MAIN CODE FOR EXTRACTING THE NECESSARY SET OF MATCHES (NO DUPLICATES)
        for (Match match : allMatches) {

            boolean matchNotAvailable = true;

            // NOTE THAT THIS IS TO PREVENT THE REPEATING OF MATCHES IN ALL CLUBS WHICH IS DUPLICATING
            for (Match value : matchesDisplayed) {
                if (match.getOpponentClubName().equalsIgnoreCase(value.getParticipatedCLubName())) {
                    // NOTE: goal scored from the club is equal to goal received from the opponent club
                    if (
                            (value.getGoalReceived() == match.getGoalScored()) &&
                                    (value.getGoalScored() == match.getGoalReceived()) &&
                                    (value.getMatchType().equalsIgnoreCase(match.getMatchType())) &&
                                    (value.getDate().equals(match.getDate()))
                    ) {
                        matchNotAvailable = false;
                    }
                }
            }
            // WE ADD THE NON DUPLICATED MATCHES INTO THIS LIST AND SEND IT TO THE VIEWS
            if (matchNotAvailable) {
                matchesDisplayed.add(match);
            }
        }
        return matchesDisplayed;
    }

    public static ArrayList<Match> matchesByDate(String date, String season){

        // loading the data from the file
        PremierLeagueManager.loadingData();

        // getting the clubs with the filtered matches by season
        guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

        ArrayList<FootballClub> filteredClubsByDateForSeason;
        ArrayList<Match> filteredMatchedOnDate = null;

        try {
            // returns the clubs with the filtered matches by date
            filteredClubsByDateForSeason = filterMatchesByDate(guiSeasonFilteredClubs, date);

            // returns the matches form the filtered club by date
            filteredMatchedOnDate = getMatchesForSeason(filteredClubsByDateForSeason);

        } catch (CloneNotSupportedException e) {
            // Handles the exception
            e.printStackTrace();

        }
        return filteredMatchedOnDate;
    }

    // This will return an arraylist which will filter all the matches of the club by date
    public static ArrayList<FootballClub> filterMatchesByDate(ArrayList<FootballClub> seasonBasedClub,
                                                              String dateEntered)
            throws CloneNotSupportedException {

        ArrayList<FootballClub> filteredClubListByDate = new ArrayList<>();

        // removing unwanted zeros from date and month
        String[] splitDate = dateEntered.split("-");
        dateEntered = Integer.parseInt(splitDate[0]) + "-" + Integer.parseInt(splitDate[1]) + "-"
                + Integer.parseInt(splitDate[2]);

        // we are cloning or creating a copy of the arraylist which has to be filtered
        for (FootballClub footballClub : seasonBasedClub) {
            filteredClubListByDate.add((FootballClub) footballClub.clone());
        }

        // check and split the date entered by the user
        if(!dateEntered.isEmpty()){

            // looping through the clubs checking for matches without the match with the given date and removing them
            for (FootballClub club: filteredClubListByDate) {
                int numberOfMatchesPlayed = club.getMatchesPlayed().size();
                int index = 0;
                while(index < numberOfMatchesPlayed){

                    int matchDay = club.getMatchesPlayed().get(index).getDate().getDay();
                    int matchMonth = club.getMatchesPlayed().get(index).getDate().getMonth();
                    int matchYear = club.getMatchesPlayed().get(index).getDate().getYear();
                    String matchDate = matchYear + "-" + matchMonth + "-" + matchDay;

                    // checking if the data is not equal and then remove the match respectively
                    if(!dateEntered.trim().equalsIgnoreCase(matchDate.trim())){
                        club.getMatchesPlayed().remove(club.getMatchesPlayed().get(index));
                        numberOfMatchesPlayed--;
                    }else{
                        index++;
                    }
                }
            }
        }
        return filteredClubListByDate;
    }

    public static ArrayList<Match> generateMatch(String season){

        // creating an instance of the premier league manager service
        LeagueManager premierLeagueManagerService = PremierLeagueManager.getInstance();
        int numberOfClubsPresent = PremierLeagueManager.getPremierLeagueFootballClubList().size();

        // This condition is to make sure that there is at least 2 clubs to play a match
        if(numberOfClubsPresent > 1){

            // there 2 or more clubs present so we can generate a match
            // loading the data from the file
            PremierLeagueManager.loadingData();

            // getting the clubs with the filtered matches by season
            guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

            Random random = new Random();

            // step 01: randomly select 2 clubs
            int randomClub_01 = random.nextInt(guiSeasonFilteredClubs.size());
            FootballClub selectedClub_O1 = guiSeasonFilteredClubs.get(randomClub_01);
            int randomClub_02 = random.nextInt(guiSeasonFilteredClubs.size());

            // This is to make sure that the same club is not selected again for the match
            while (randomClub_02==randomClub_01){
                randomClub_02 = random.nextInt(guiSeasonFilteredClubs.size());
            }
            FootballClub selectedClub_O2 = guiSeasonFilteredClubs.get(randomClub_02);

            // step 02: randomly generate the necessary data
            int numberGoalScored_club_1 = random.nextInt(7);
            int numberGoalScored_club_2 = random.nextInt(7);

            // setting the random date and random season depending on the randomly selected year
            int[] possibleYears = new int[2];

            int seasonYear = Integer.parseInt(season.split("-")[0]);

            possibleYears[0] = seasonYear;
            possibleYears[1] = seasonYear + 1;

            // making sure that the months are in given range for the year select for the season
            // premier league happens every year from August to next May
            int day = random.nextInt(30)+1;
            int randomYearIndexSelected = random.nextInt(2);
            int year = possibleYears[randomYearIndexSelected];
            int month;

            // if randomYearIndexSelected = 0, then the months have to be in the range from 8 to 12 else 1 to 5
            if(randomYearIndexSelected==0){
                // 8 to 12
                month = random.nextInt(5) + 8;

            }else{
                // 1 to 5
                month = random.nextInt(5) + 1;

            }

            DateMatch date = new DateMatch(day, month, year);
            String[] matchTypes = new String[]{"Home", "Away"};
            String matchType = matchTypes[random.nextInt(2)];

            // step 03: call the addPlayedMatch() wisely by passing all the generated random data
            premierLeagueManagerService.addPlayedMatch(season,selectedClub_O1.getName(), selectedClub_O2.getName(),
                    numberGoalScored_club_1, numberGoalScored_club_2, date, matchType);

            // step 04: call the save file method
            premierLeagueManagerService.saveDataIntoFile();

            // step 05: call the load file method
            PremierLeagueManager.loadingData();

            // getting the clubs with the filtered matches by season
            guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

            // getting the matches for a season and returning
            return getMatchesForSeason(guiSeasonFilteredClubs);
        }
        // if there are less than 2 clubs we can't generate a match
        return null;
    }
}
