package controllers;
import com.fasterxml.jackson.databind.JsonNode;
import entities.FootballClub;
import entities.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.*;
import utils.PremierLeagueUtil;
import java.util.ArrayList;

public class PremierLeagueController extends Controller {

    // variables used
    private ArrayList<FootballClub> guiSeasonFilteredClubs = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger("premierLeagueController");

    // This is the index URL
    public Result index(){
        return ok("Main route");
    }

    // sending all the season for the dropdown menu
    public Result allSeasons(){

        // this is the logger for debugging purposes
        logger.debug("In PremierLeagueController.allSeasons()");

        // the PremierLeagueUtils returns the seasons
        ArrayList<String> allSeasons = PremierLeagueUtil.allSeasons();

        // converting into JSON format
        JsonNode allSeasonsJson = Json.toJson(allSeasons);
        logger.debug("In PremierLeagueController.allSeasons(), result is {}",allSeasonsJson.toString());
        return ok(allSeasonsJson);

    }

    // sending the sorted table data by points (descending order) by season
    public Result sortByPoints(String season){

        // this is the logger for debugging purposes
        logger.debug("In PremierLeagueController.sortByPoints()");

        // gets the sorted clubs from the Utils class
        guiSeasonFilteredClubs = PremierLeagueUtil.sortByPoints(season);

        // converting into json format
        JsonNode guiSortedByPointsClubs = Json.toJson(guiSeasonFilteredClubs);
        logger.debug("In PremierLeagueController.sortByPoints(), result is {}",guiSortedByPointsClubs.toString());
        return ok(guiSortedByPointsClubs);

    }

    // sending the sorted table data by wins (descending order) by season
    public Result sortByWins(String season){

        // this is the logger for debugging purposes
        logger.debug("In PremierLeagueController.sortByWins()");

        // gets the sorted clubs from the PremierLeagueUtil class
        guiSeasonFilteredClubs = PremierLeagueUtil.sortByWins(season);

        // converting into JSON format
        JsonNode guiSortedByWinsClubs = Json.toJson(guiSeasonFilteredClubs);
        logger.debug("In PremierLeagueController.sortByWins(), result is {}",guiSortedByWinsClubs.toString());
        return ok(guiSortedByWinsClubs);

    }

    // sending the sorted table data by goals (descending order) by season
    public Result sortByGoals(String season){

        // this is the logger for debugging purposes
        logger.debug("In PremierLeagueController.sortByGoals()");

        // gets the sorted clubs from the PremierLeagueUtil class
        guiSeasonFilteredClubs = PremierLeagueUtil.sortByGoals(season);

        // converting the data into JSON format
        JsonNode guiSortByGoalsClubs = Json.toJson(guiSeasonFilteredClubs);
        logger.debug("In PremierLeagueController.sortByGoals(), result is {}",guiSortByGoalsClubs.toString());
        return ok(guiSortByGoalsClubs);

    }

    // sending all the matches data by season
    public Result allMatches(String season){

        // this is the logger for debugging purposes
        logger.debug("In PremierLeagueController.allMatches()");

        // gets the list of matches
        ArrayList<Match> matchesDisplayed = PremierLeagueUtil.allMatches(season);

        // converting the data into JSON format
        JsonNode allMatchesJson = Json.toJson(matchesDisplayed);
        logger.debug("In PremierLeagueController.allMatches(), result is {}",allMatchesJson.toString());
        return ok(allMatchesJson);

    }

    // sending all the matches data for a specific date
    public Result matchesByDate(String date,String season){

        // this is the logger for debugging purposes
        logger.debug("In PremierLeagueController.matchesByDate()");

        // returning the matches filled by date
        ArrayList<Match> filteredMatchedOnDate = PremierLeagueUtil.matchesByDate(date, season);

        // converting into JSON format
        JsonNode matchesByDateJson = Json.toJson(filteredMatchedOnDate);
        logger.debug("In PremierLeagueController.matchesByDate(), result is {}",matchesByDateJson.toString());
        return ok(matchesByDateJson);

    }

    // generating a new match
    public Result generateMatch(String season){

        // this is the logger for debugging purposes
        logger.debug("In PremierLeagueController.generateMatch()");

        // gets all the matches with the generated matches list
        ArrayList<Match> matchesDisplayed = PremierLeagueUtil.generateMatch(season);

        // converts the data into JSON format
        JsonNode generatedWithAllMatches = Json.toJson(matchesDisplayed);
        logger.debug("In PremierLeagueController.generateMatch(), result is {}",generatedWithAllMatches.toString());
        return ok(generatedWithAllMatches);

    }

}
