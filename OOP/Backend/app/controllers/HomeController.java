package controllers;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

public class HomeController extends Controller {

    // variables used
    private LeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
    private ArrayList<FootballClub> guiSeasonFilteredClubs = null;

    // this is the index method which runs for the endpoint http://localhost:9000/
    public Result index() {
        return ok(views.html.index.render());
    }

    // sending all the season for the dropdown menu, endpoint http://localhost:9000/seasons/all
    public Result allSeasons(){

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

        // getting the seasons
        ArrayList<String> allSeasons = PremierLeagueManager.getAllSeasonAdded();

        // converting into JSON format
        JsonNode allSeasonsJson = Json.toJson(allSeasons);

        return ok(allSeasonsJson);
    }


    // sending the sorted table data by points (descending order) by season
    public Result sortByPoints(String season){

        // loading the data from the file
        PremierLeagueManager.loadingData();

        // filters the football clubs according to the season
        guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

        // sorting by points only in descending order
        guiSeasonFilteredClubs = sortClubsByPoints(guiSeasonFilteredClubs);

        // converting into json format
        JsonNode guiSeasonFilteredClubsJson = Json.toJson(guiSeasonFilteredClubs);
        return ok(guiSeasonFilteredClubsJson);
    }

    // This function is used to sort the matches of a football club in a season by descending order of points
    public ArrayList<FootballClub> sortClubsByPoints(ArrayList<FootballClub> guiSeasonFilteredClubs) {

        // comparator to sort the clubs by points
        Comparator<FootballClub> comparator = (club1, club2) -> {
            if(club1.getClubStatistics().getTotalPointsScored() < club2.getClubStatistics().getTotalPointsScored()){
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

    // sending the sorted table data by wins (descending order) by season
    public Result sortByWins(String season){

        // loading the data from the file
        PremierLeagueManager.loadingData();

        // filters the football clubs according to the season
        guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

        // sorting by points only in descending order of wins
        guiSeasonFilteredClubs = sortClubsByWins(guiSeasonFilteredClubs);

        // converting into JSON format
        JsonNode guiSeasonFilteredClubsJson = Json.toJson(guiSeasonFilteredClubs);
        return ok(guiSeasonFilteredClubsJson);
    }

    // sorting by points only in descending order of wins
    public ArrayList<FootballClub> sortClubsByWins(ArrayList<FootballClub> guiSeasonFilteredClubs) {

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

    // sending the sorted table data by goals (descending order) by season
    public Result sortByGoals(String season){

        // loading the data from the file
        PremierLeagueManager.loadingData();

        // filters the football clubs according to the season
        guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

        // sorting by points only in descending order goal scored
        guiSeasonFilteredClubs = sortClubsByGoals(guiSeasonFilteredClubs);

        // converting the data into JSON format
        JsonNode guiSeasonFilteredClubsJson = Json.toJson(guiSeasonFilteredClubs);
        return ok(guiSeasonFilteredClubsJson);
    }

    // sorting by points only in descending order goal scored
    public ArrayList<FootballClub> sortClubsByGoals(ArrayList<FootballClub> guiSeasonFilteredClubs) {

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

    // This function is to return the listOfClubs filtered by season
    public ArrayList<FootballClub> getGuiSeasonFilteredClubs(String season){
        try {
            // get the clubs filtered by season
            guiSeasonFilteredClubs = PremierLeagueManager.seasonFilteredFootballCLubList(season);

        } catch (CloneNotSupportedException e) {
            // Handles the exception
            e.printStackTrace();

        }
        return guiSeasonFilteredClubs;
    }


    // sending all the matches data by season
    public Result allMatches(String season){
        // loading the data from the file
        PremierLeagueManager.loadingData();

        // getting the clubs with the filtered matches by season
        guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

        // getting the matches filtered by season
        ArrayList<Match> matchesDisplayed = getMatchesForSeason(guiSeasonFilteredClubs);

        // converting the data into JSON format
        JsonNode matchesDisplayedJson = Json.toJson(matchesDisplayed);
        return ok(matchesDisplayedJson);

    }

    // sending all the matches data for a specific date
    public Result matchesByDate(String date,String season){

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

        // converting into JSON format
        JsonNode filteredMatchedOnDateJson = Json.toJson(filteredMatchedOnDate);
        return ok(filteredMatchedOnDateJson);

    }

    // This will return an arraylist which will filter all the matches of the club by date
    public static ArrayList<FootballClub> filterMatchesByDate(ArrayList<FootballClub> seasonBasedClub,
                                                              String dateEntered)
            throws CloneNotSupportedException {
        ArrayList<FootballClub> filteredClubListByDate = new ArrayList<>();

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

    // generating a new match
    public Result generateMatch(String season){

        // loading the data from the file
        PremierLeagueManager.loadingData();

        // getting the clubs with the filtered matches by season
        guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

        Random random = new Random();

        // step 01: randomly select 2 clubs
        int randomClub_01 = random.nextInt(guiSeasonFilteredClubs.size());
        FootballClub selectedClub_O1 = guiSeasonFilteredClubs.get(randomClub_01);

        int randomClub_02 = random.nextInt(guiSeasonFilteredClubs.size());
        while (randomClub_02==randomClub_01){   // make sure not to take the same club again
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

        int day = random.nextInt(30)+1;
        int month = random.nextInt(12)+1;
        int year = possibleYears[random.nextInt(2)];

        DateMatch date = new DateMatch(day, month, year);
        String[] matchTypes = new String[]{"Home", "Away"};
        String matchType = matchTypes[random.nextInt(2)];


        // step 03: call the addPlayedMatch() wisely by passing all the generated random data
        premierLeagueManager.addPlayedMatch(season,selectedClub_O1.getName(), selectedClub_O2.getName(),
                numberGoalScored_club_1, numberGoalScored_club_2, date, matchType);

        // step 04: call the save file method
        premierLeagueManager.saveDataIntoFile();

        // step 05: call the load file method
        PremierLeagueManager.loadingData();

        // getting the clubs with the filtered matches by season
        guiSeasonFilteredClubs = getGuiSeasonFilteredClubs(season);

        // getting the matches for a season
        ArrayList<Match> matchesDisplayed = getMatchesForSeason(guiSeasonFilteredClubs);

        // converts the data into JSON format
        JsonNode matchesDisplayedJson = Json.toJson(matchesDisplayed);

        return ok(matchesDisplayedJson);
    }

    // This returns a list of matches for a given season
    public ArrayList<Match> getMatchesForSeason(ArrayList<FootballClub> seasonBasedClub){

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
}
