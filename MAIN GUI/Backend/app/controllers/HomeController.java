package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class HomeController extends Controller {

    LeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
    ArrayList<FootballClub> guiSeasonFilteredClubs = null;


    public Result index() {
        return ok(views.html.index.render());
    }

    public Result about() {
        Person person = new Person();
        person.firstName = "Foo";
        person.lastName = "Bar";
        person.age = 30;

        ArrayList<Person> people = new ArrayList<>();
        people.add(person);
        people.add(person);
        people.add(person);
        people.add(person);
        people.add(person);
        people.add(person);

        JsonNode personJson = Json.toJson(person); // {"firstName": "Foo", "lastName": "Bar", "age": 30}
        JsonNode peopleJson = Json.toJson(people);

        return ok(peopleJson);
    }
    public Result welcome(String name) {
        return ok("Welcome " + name);
    }

    // sending all the season for the dropdown menu
    public Result allSeasons(){
        // sort the seasons using the comparator
        Comparator<String> comparator = (season1, season2) -> {
            if(Integer.parseInt(season1.split("-")[0]) > Integer.parseInt(season2.split("-")[0])){
                return 1;
            }
            return -1;
        };

        PremierLeagueManager.setAllSeasonAdded((ArrayList<String>)
                PremierLeagueManager.getAllSeasonAdded().stream().distinct().collect(Collectors.toList()));
        PremierLeagueManager.getAllSeasonAdded().sort(comparator);

        ArrayList<String> allSeasons = PremierLeagueManager.getAllSeasonAdded();
        JsonNode allSeasonsJson = Json.toJson(allSeasons);

        return ok(allSeasonsJson);
    }


    // sending the sorted table data by points (descending order) by season
    public Result sortByPoints(String season){
        try{
            guiSeasonFilteredClubs = PremierLeagueManager.seasonFilteredFootballCLubList(season);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // sorting by points only in descending order
        Comparator<FootballClub> comparator = (club1, club2) -> {
                if(club1.getClubStatistics().getTotalPointsScored() < club2.getClubStatistics().getTotalPointsScored()){
                    return 1;
                }

            return -1;
        };
        if (guiSeasonFilteredClubs != null) {
            guiSeasonFilteredClubs.sort(comparator);
        }
        JsonNode guiSeasonFilteredClubsJson = Json.toJson(guiSeasonFilteredClubs);
        return ok(guiSeasonFilteredClubsJson);
    }

    // sending the sorted table data by wins (descending order) by season
    public Result sortByWins(String season){
        try {
            guiSeasonFilteredClubs = PremierLeagueManager.seasonFilteredFootballCLubList(season);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // sorting by points only in descending order
        Comparator<FootballClub> comparator = (club1, club2) -> {
            if(club1.getClubStatistics().getTotalWins() < club2.getClubStatistics().getTotalWins()){
                return 1;
            }

            return -1;
        };
        if (guiSeasonFilteredClubs != null) {
            guiSeasonFilteredClubs.sort(comparator);
        }
        JsonNode guiSeasonFilteredClubsJson = Json.toJson(guiSeasonFilteredClubs);
        return ok(guiSeasonFilteredClubsJson);
    }

    // sending the sorted table data by goals (descending order) by season
    public Result sortByGoals(String season){
        try {
            guiSeasonFilteredClubs = PremierLeagueManager.seasonFilteredFootballCLubList(season);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // sorting by points only in descending order
        Comparator<FootballClub> comparator = (club1, club2) -> {
            if(club1.getTotalGoalsScored() < club2.getTotalGoalsScored()){
                return 1;
            }

            return -1;
        };
        if (guiSeasonFilteredClubs != null) {
            guiSeasonFilteredClubs.sort(comparator);
        }
        JsonNode guiSeasonFilteredClubsJson = Json.toJson(guiSeasonFilteredClubs);
        return ok(guiSeasonFilteredClubsJson);
    }

    // sending all the matches data by season
    public Result allMatches(String season){

        try {
            guiSeasonFilteredClubs = PremierLeagueManager.seasonFilteredFootballCLubList(season);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // these both arrayList will be of the same size
        ArrayList<Match> matchesDisplayed = new ArrayList<>();
        ArrayList<Match> allMatches = new ArrayList<>();

        // populating the allMatches list will all the matches from the seasonBasedClub
        // adding all the matches played for that season inside the allMatches list
        for (FootballClub footballClub: guiSeasonFilteredClubs) {
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
        JsonNode matchesDisplayedJson = Json.toJson(matchesDisplayed);
        return ok(matchesDisplayedJson);
    }

    // sending all the matches data for a specific date
    public Result matchesByDate(String date){

        return ok();
    }

    // generating a new match
    public Result generateMatch(String season){

        

        return ok();
    }

}
