import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */

public class PremierLeagueManager implements LeagueManager {
    //  variables used
    public static ArrayList<FootballClub> premierLeagueFootballClubList;
    private static boolean matchedAdded;
    private static ArrayList<String> allSeasonAdded = new ArrayList<>();
    public static ArrayList<FootballClub> seasonFilteredClubs = new ArrayList<>();
    private final int MAXIMUM_NUMBER_OF_CLUBS = 20;


    public PremierLeagueManager(){
        matchedAdded = false;
        premierLeagueFootballClubList= new ArrayList<>();   // initializing the variable to run methods
        loadingData();      // load the previously saved data from the file
    }

    // getting the list of football clubs
    public ArrayList<FootballClub> getPremierLeagueFootballClubList() {
        return premierLeagueFootballClubList;
    }

    // setting the list of football clubs
    public void setPremierLeagueFootballClubList(ArrayList<FootballClub> premierLeagueFootballClubList) {
        this.premierLeagueFootballClubList = premierLeagueFootballClubList;
    }

    public static void loadingData() {
        File file = new File("objectDataFile.txt");
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);

            try {
                premierLeagueFootballClubList = (ArrayList<FootballClub>) objectInputStream.readObject();
                matchedAdded = (boolean) objectInputStream.readObject();
                allSeasonAdded = (ArrayList<String>) objectInputStream.readObject();

            } catch (ClassNotFoundException e) {
                System.out.println(" ClassNotFoundException occurred Not able to find the class");
            }


        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println(" File not found exception occurred!");
        }
        catch (IOException ioException) {
            System.out.println( " Exception when performing read/write operations to the file!" +
                    "\n No permission to read/write from or to the file");

        }
        finally {
            try{
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            }
            catch (IOException ioException) {
                System.out.println(" Exception when performing read/write operations to the file!" +
                        "\n No permission to read/write from or to the file");

            }
        }
    }

    // Overriding the createClub method from the interface
    @Override
    public String createClub(SportClub club) {

        if(premierLeagueFootballClubList.size()<20)         // This means that there is space to add more clubs/teams
        {
            premierLeagueFootballClubList.add((FootballClub) club);
            return " Clubs Successfully added!";
        }
        return " Sorry there is no room for a new club, the maximum number of club limit has been reached!";

    }

    // validate strings that should only have alphabets and return the result
    private String validateString(String message) {
        Scanner input = new Scanner(System.in);
        boolean validStringEntered;
        String userInput;

        do{
            validStringEntered = false;
            System.out.print(message);
            userInput = input.nextLine();
            if((userInput != null)&&userInput.matches("^[a-z A-Z]*$"))
                validStringEntered = true;
            else
                System.out.println("\n Given input is not in proper format! ");
        }while (!validStringEntered);

        return userInput;
    }

    // Overriding the deleteCLub method from the interface
    @Override
    public FootballClub deleteCLub(String clubName) {

        // This loop searches for the club and deletes it from the list
        for (int index = 0; index < premierLeagueFootballClubList.size(); index++) {
            if(premierLeagueFootballClubList.get(index).getName().equalsIgnoreCase(clubName)){
                return premierLeagueFootballClubList.remove(index);
            }
        }

        return null;

    }
    // Overriding the displayStats method from the interface
    @Override
    public String displayStats(String clubName) {

        boolean clubNameAvailable = false;

        // This loop searches for the club and displays it's statistics
        for (FootballClub footballClub : premierLeagueFootballClubList) {
            if (footballClub.getName().equalsIgnoreCase(clubName)) {
                clubNameAvailable = true;
                System.out.println("\n ===============> S T A T I S T I C S <===============");
                System.out.println("\n =============> PLAYERS - STATISTICS <=============\n");
                for (int i = 0; i < footballClub.getPlayersList().size(); i++) {
                    System.out.println(" <------------ Player " + ( i + 1 ) + " ---------------->\n");
                    System.out.println(footballClub.getPlayersList().get(i));
                }

                System.out.println("\n =============>  FROM  ALL  SEASONS  <=============\n");
                System.out.println(footballClub.toString());


                // sorting the seasons in ascending
                Comparator<String> comparator = (season1, season2) -> {
                    if(Integer.parseInt(season1.split("-")[0]) > Integer.parseInt(season2.split("-")[0])){
                        return 1;
                    }
                    return -1;
                };

                allSeasonAdded = (ArrayList<String>)allSeasonAdded.stream().distinct().collect(Collectors.toList());
                allSeasonAdded.sort(comparator);

                // Display the total stats by season

                for (String season : allSeasonAdded) {
                    System.out.println("\n =============> FOR SEASON (" + season + ") <=============\n");
                    ArrayList<FootballClub> seasonFilteredClubs = null;
                    try {
                        seasonFilteredClubs = seasonFilteredFootballCLubList(season);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    if (seasonFilteredClubs != null) {
                        for (FootballClub club: seasonFilteredClubs){
                            if(club.getName().equalsIgnoreCase(clubName)) {
                                System.out.println(club);
                            };
                        }
                    }
                }

                int number = 0;
                // looping through each played match and displaying their stats
                if(footballClub.getMatchesPlayed().size()!=0){
                    System.out.println(" =============>  FROM  ALL  SEASONS  <=============\n");
                    System.out.println(" => Statistics of all the matches played by '"+ clubName + "' so far! <=");
                    for (Match match:footballClub.getMatchesPlayed()) {
                        String matchResult = "\n <===============> Match "+ (++number) +" <================>\n "
                                + "* Opponent team name: '" + match.getOpponentClubName() + "'" + match.getDate()
                                + "\n * Season: " + match.getSeason() + "\n\n * Match Type: '" + match.getMatchType() + "'"
                                + "\n * Number of Goals Scored: " + match.getGoalScored()
                                + "\n * Number of Goals Received: " + match.getGoalReceived()
                                + "\n * Number of Goal Difference: " + (match.getGoalScored() - match.getGoalReceived())
                                + "\n * Number of Yellow Cards: " + match.getMatchStats().getYellowCards()
                                + "\n * Number of Red Cards: " + match.getMatchStats().getRedCards()
                                + "\n * Number of Shots: " + match.getMatchStats().getShots()
                                + "\n * Number of Shots of target: " + match.getMatchStats().getShotsOfTarget()
                                + "\n\n * Number of off sides: " + match.getMatchStats().getOffSides()
                                + "\n * Number of fouls: " + match.getMatchStats().getFouls()
                                + "\n * Number of corners: " + match.getMatchStats().getCorners()
                                + "\n * Number of passes: " + match.getMatchStats().getPasses()
                                + "\n * Pass Accuracy: " + match.getMatchStats().getPassAccuracy() + "%"
                                + "\n * Possession: " + match.getMatchStats().getPossession() + "%"
                                + "\n\n ============================================= \n";

                        System.out.println(matchResult);
                    }
                }
            }
        }

        if(!clubNameAvailable){
            return "\n Sorry, there is no club with the given name '" + clubName + "'";
        }

        return " Result Displayed";
    }

    // Overriding the displayLeagueTable method from the interface
    @Override
    public void displayLeagueTable(String seasonPlayed) {

        // we add all the football clubs with all the necessary matches related to the season and other removed.
        ArrayList<FootballClub> seasonFilteredClubs = null;
        try {
            seasonFilteredClubs = seasonFilteredFootballCLubList(seasonPlayed);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // This mainly depends on the length of the club name the rest are normal and fixed
        if (seasonFilteredClubs.size()!=0){

            // getting maximum length club name from the list.
            int maxClubNameLength = seasonFilteredClubs.get(0).getName().length();

            for (FootballClub footballClub : seasonFilteredClubs) {
                // we find the maximum length of the club names from the list of football clubs

                if(footballClub.getName().length() > maxClubNameLength){
                    maxClubNameLength = footballClub.getName().length();
                }
            }

            // Implementing the comparator for sorting
            /*
             * Comparator is an interface used to sort collection using two objects as its parameter inputs.
             */
            // this is an anonymous class
            // Sorting the points and goals in descending order for the football clubs
            Comparator<FootballClub> comparator = (club1, club2) -> {
                if(club1.getClubStatistics().getTotalPointsScored() == (club2.getClubStatistics().getTotalPointsScored())){
                    if(club1.getTotalGoalsScored() < club2.getTotalGoalsScored()){
                        return 1;
                    }
                }else{
                    if(club1.getClubStatistics().getTotalPointsScored() < club2.getClubStatistics().getTotalPointsScored()){
                        return 1;
                    }
                }
                return -1;
            };

            // sorting the list with a new arrayList
            seasonFilteredClubs.sort(comparator);  // sorting the clubs

            structuredTable(maxClubNameLength, seasonFilteredClubs);
        }else{
            // creating the empty table
            structuredTable(0, seasonFilteredClubs);
        }

    }

    // This method is for the GUI
    public static void displayLeagueTableGUI() {
        String seasonPlayed = PremierLeagueGUI.comboBox.getValue();   // you get the season from this
        try {
            seasonFilteredClubs = seasonFilteredFootballCLubList(seasonPlayed);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // sorting by points
        if(PremierLeagueGUI.sortByPointsClicked
                && !PremierLeagueGUI.sortByWinsClicked
                && !PremierLeagueGUI.sortByGoalsClicked){

            Comparator<FootballClub> comparator = (club1, club2) -> {
                if(club1.getClubStatistics().getTotalPointsScored() < club2.getClubStatistics().getTotalPointsScored()){
                    return 1;
                }
                return -1;
            };
            seasonFilteredClubs.sort(comparator);  // sorting the clubs
        }

        // sort by goals
        if(PremierLeagueGUI.sortByGoalsClicked){
            Comparator<FootballClub> comparator = (club1, club2) -> {
                if(club1.getTotalGoalsScored() < club2.getTotalGoalsScored()){
                    return 1;
                }
                return -1;
            };
            seasonFilteredClubs.sort(comparator);  // sorting the clubs
        }

        // sort by wins
        if(PremierLeagueGUI.sortByWinsClicked){
            Comparator<FootballClub> comparator = (club1, club2) -> {
                if(club1.getClubStatistics().getTotalWins() < club2.getClubStatistics().getTotalWins()){
                    return 1;
                }
                return -1;
            };
            seasonFilteredClubs.sort(comparator);  // sorting the clubs
        }

    }

    // This method returns a list of football clubs filtered by season with updated stats for that season only.
    private static ArrayList<FootballClub> seasonFilteredFootballCLubList(String seasonPlayed) throws CloneNotSupportedException {
        ArrayList<FootballClub> footballClubsListSeason = new ArrayList<>();

        // we add all the clubs, before adding the club remove the matches which aren't related
        for (int i = 0; i < premierLeagueFootballClubList.size(); i++) {
            footballClubsListSeason.add((FootballClub) premierLeagueFootballClubList.get(i).clone());

            int matchIndexLoop = 0;
            while ( matchIndexLoop < footballClubsListSeason.get(i).getMatchesPlayed().size() ){
                if(!footballClubsListSeason.get(i).getMatchesPlayed().get(matchIndexLoop).getSeason().equalsIgnoreCase(seasonPlayed)){

                    // update the stats before removing the match
                    int goalScored = footballClubsListSeason.get(i).getMatchesPlayed().get(matchIndexLoop).getGoalScored();
                    int goalReceived = footballClubsListSeason.get(i).getMatchesPlayed().get(matchIndexLoop).getGoalReceived();

                    footballClubsListSeason.get(i).setTotalGoalsDifference(             // updating total goal difference
                            footballClubsListSeason.get(i).getTotalGoalsDifference() - (goalScored - goalReceived)
                    );

                    footballClubsListSeason.get(i).setTotalGoalsScored(                 // updating total goal scored
                            footballClubsListSeason.get(i).getTotalGoalsScored() -
                                    footballClubsListSeason.get(i).getMatchesPlayed().get(matchIndexLoop).getGoalScored()
                    );

                    footballClubsListSeason.get(i).setTotalGoalsReceived(             // updating total goal received
                            footballClubsListSeason.get(i).getTotalGoalsReceived() -
                                    footballClubsListSeason.get(i).getMatchesPlayed().get(matchIndexLoop).getGoalReceived()
                    );

                    footballClubsListSeason.get(i).setTotalYellowCards(             // updating total yellow cards
                            footballClubsListSeason.get(i).getTotalYellowCards() -
                                    footballClubsListSeason.get(i).getMatchesPlayed().get(matchIndexLoop)
                                            .getMatchStats().getYellowCards()
                    );

                    footballClubsListSeason.get(i).setTotalRedCards(             // updating total red cards
                            footballClubsListSeason.get(i).getTotalRedCards() -
                                    footballClubsListSeason.get(i).getMatchesPlayed().get(matchIndexLoop)
                                            .getMatchStats().getRedCards()
                    );

                    footballClubsListSeason.get(i).getClubStatistics().setTotalMatchesPlayed(  // update number of matches
                            footballClubsListSeason.get(i).getClubStatistics().getTotalMatchesPlayed() - 1
                    );

                    if(goalScored > goalReceived){
                        // update wins and points scored
                        footballClubsListSeason.get(i).getClubStatistics().setTotalWins(
                                footballClubsListSeason.get(i).getClubStatistics().getTotalWins() - 1
                        );

                        footballClubsListSeason.get(i).getClubStatistics().setTotalPointsScored(
                                footballClubsListSeason.get(i).getClubStatistics().getTotalPointsScored() - 3
                        );
                    }else if (goalReceived > goalScored){
                        // update defeats
                        footballClubsListSeason.get(i).getClubStatistics().setTotalDefeats(
                                footballClubsListSeason.get(i).getClubStatistics().getTotalDefeats() - 1
                        );
                    }else{
                        // update draws and points scored
                        footballClubsListSeason.get(i).getClubStatistics().setTotalDraws(
                                footballClubsListSeason.get(i).getClubStatistics().getTotalDraws() - 1
                        );

                        footballClubsListSeason.get(i).getClubStatistics().setTotalPointsScored(
                                footballClubsListSeason.get(i).getClubStatistics().getTotalPointsScored() - 1
                        );
                    }

                    footballClubsListSeason.get(i).getMatchesPlayed().remove(   // removing the match from the list
                            footballClubsListSeason.get(i).getMatchesPlayed().get(matchIndexLoop)
                    );
                }else{
                    matchIndexLoop++; // incrementing the index to skip that match which should not be removed
                }
            }
        }

        // setting th position value to "00" if all the clubs didnt play for the given season
        for (FootballClub footballClub: footballClubsListSeason) {
            if(footballClub.getClubStatistics().getTotalMatchesPlayed() != 0){
                // then we can give positions to all the clubs
                matchedAdded = true;
                break;
            }else{
                matchedAdded = false;
            }
        }

        return footballClubsListSeason;
    }

    // Display the premier league table in a well structured format
    private void structuredTable(int lengthOfClubNameTable, ArrayList<FootballClub> seasonFilteredClubs) {
        /*
         *  We take the length of the largest club name, then use this to create the main table width
         */
        StringBuilder HORIZONTAL_DASHES = new StringBuilder();
        StringBuilder PREMIER_LEAGUE_SPACE_TILE = new StringBuilder();

        if(lengthOfClubNameTable != 0){

            // creating the table with data
            int clubNameColSpace = lengthOfClubNameTable + 2;
            int leftClubColSpace = clubNameColSpace/2;
            int rightClubColSpace = clubNameColSpace - leftClubColSpace;

            StringBuilder PREMIER_LEAGUE_SPACE_TILE_LEFT = new StringBuilder();
            StringBuilder PREMIER_LEAGUE_SPACE_TILE_RIGHT = new StringBuilder();
            StringBuilder LEFT_CLUB_COL_SPACE = new StringBuilder();
            StringBuilder RIGHT_CLUB_COL_SPACE = new StringBuilder();

            for (int i = 0; i < 107+lengthOfClubNameTable; i++) {
                HORIZONTAL_DASHES.append("-");
            }
            for (int i = 0; i < 39 + (lengthOfClubNameTable/2); i++) {
                PREMIER_LEAGUE_SPACE_TILE_LEFT.append(" ");
            }
            for (int i = 0; i < 39 + (lengthOfClubNameTable - (lengthOfClubNameTable/2)); i++) {
                PREMIER_LEAGUE_SPACE_TILE_RIGHT.append(" ");
            }
            for (int i = 0; i < leftClubColSpace; i++) {
                LEFT_CLUB_COL_SPACE.append(" ");
            }
            for (int i = 0; i < rightClubColSpace; i++) {
                RIGHT_CLUB_COL_SPACE.append(" ");
            }

            System.out.println("\n"+HORIZONTAL_DASHES);
            System.out.println("|" + PREMIER_LEAGUE_SPACE_TILE_LEFT + "P R E M I E R - L E A G U E" +
                    PREMIER_LEAGUE_SPACE_TILE_RIGHT + "|");
            System.out.println(HORIZONTAL_DASHES);
            System.out.println("| Position |" + LEFT_CLUB_COL_SPACE +"Club" + RIGHT_CLUB_COL_SPACE +
                    "| Played | Won | Drawn | Lost | Goal-Scored | Goal-Received " +
                    "| Goal-Difference  | Points |");
            System.out.println(HORIZONTAL_DASHES);

            // display the content of the premierLeagueFootball List
            for (int i = 0; i < seasonFilteredClubs.size(); i++) {
                StringBuilder clubNameEndSpace = new StringBuilder();

                for (int j = 0; j < 3; j++) {
                    clubNameEndSpace.append(" ");
                }

                // changing the width of the club name for each row
                if(seasonFilteredClubs.get(i).getName().length() != lengthOfClubNameTable){
                    // the length of the name will anyways be less than lengthOfClubNameTable
                    int difference = lengthOfClubNameTable - seasonFilteredClubs.get(i).getName().length();
//                  clubNameEndSpace.append(" ".repeat(difference));
                    for (int j = 0; j < difference; j++) {
                        clubNameEndSpace.append(" ");
                    }
                }

                /*
                 *  creating an arraylist with organised data for the table
                 *  The content structure is [position, played match, won, drawn, lost, goal scored, goal received, points,
                 *  goal difference]
                 */
                ArrayList<String> organisedResultList = new ArrayList<>();
                if(i<9){
                    organisedResultList.add("0"+(i+1));
                }else{
                    organisedResultList.add(String.valueOf(i+1));
                }

                // getting the stats into an arraylist to organise it
                for (int j = 0; j < seasonFilteredClubs.get(i).getMainStatistics().size(); j++) {

                    if(j==7){  // working with the goal difference
                        if(seasonFilteredClubs.get(i).getMainStatistics().get(j)>-1){
                            if(seasonFilteredClubs.get(i).getMainStatistics().get(j)<10) {
                                organisedResultList.add("+0"+seasonFilteredClubs.get(i).getMainStatistics().get(j));
                            }else{
                                organisedResultList.add("+"+seasonFilteredClubs.get(i).getMainStatistics().get(j));
                            }
                        }else{
                            if(seasonFilteredClubs.get(i).getMainStatistics().get(j)>-10) {
                                organisedResultList.add("-0"+Math.abs(seasonFilteredClubs.get(i)
                                        .getMainStatistics().get(j)));
                            }else{
                                organisedResultList.add(String.valueOf(seasonFilteredClubs.get(i)
                                        .getMainStatistics().get(j)));
                            }
                        }
                    }else{
                        if(seasonFilteredClubs.get(i).getMainStatistics().get(j)<10){
                            organisedResultList.add("0"+seasonFilteredClubs.get(i).getMainStatistics().get(j));
                        }else{
                            organisedResultList.add(String.valueOf(seasonFilteredClubs.get(i)
                                    .getMainStatistics().get(j)));
                        }
                    }
                }

                // if not matches are added then fixed positions cannot be given for any club until they play a match
                if(!matchedAdded){
                    organisedResultList.set(0, "00");
                }

                // this is were the table is created
                System.out.println("|    "+organisedResultList.get(0)+ "    |   "+ seasonFilteredClubs.get(i).getName()
                        + clubNameEndSpace + "|   "+organisedResultList.get(1)+
                        "   | "+organisedResultList.get(2)+"  |  "+
                        organisedResultList.get(3)+"   |  "+
                        organisedResultList.get(4)+"  |     "+
                        organisedResultList.get(5)+"      |      "+
                        organisedResultList.get(6)+"       |        "+
                        organisedResultList.get(8)+"       |   "+
                        organisedResultList.get(7)+"   |");
            }

        }else{
            // creating the empty table
            for (int j = 0; j < 106; j++) {
                HORIZONTAL_DASHES.append("-");
            }
            for (int j = 0; j < 38; j++) {
                PREMIER_LEAGUE_SPACE_TILE.append(" ");
            }

            System.out.println("\n"+HORIZONTAL_DASHES);
            System.out.println("|" + PREMIER_LEAGUE_SPACE_TILE + " P R E M I E R - L E A G U E" + PREMIER_LEAGUE_SPACE_TILE + "|");
            System.out.println(HORIZONTAL_DASHES);
            System.out.println("| Position |         Club         | Played | Won | Drawn | Lost | Goal-Scored " +
                    "| Goal-Difference | Points |");
            System.out.println(HORIZONTAL_DASHES);

            //  creating the empty rows
            for (int i = 0; i < 10; i++) {
                System.out.println("|          |                      |        |     |       |      |           " +
                        "  |                 |        |");
            }
        }
        System.out.println("\n\n");
    }

    // Overriding the addPlayedMatch method from the interface
    @Override
    public String addPlayedMatch(String seasonPlayed, String clubName_01, String clubName_02,int numberGoalScored_club_1,
                                 int numberGoalScored_club_2, DateMatch dateOfMatch, String matchType) {

        allSeasonAdded.add(seasonPlayed);

        // check if the enter clubs are real and display msg
        boolean club01 = false;
        boolean club02 = false;
        for (FootballClub footballClub : premierLeagueFootballClubList) {
            if(footballClub.getName().equalsIgnoreCase(clubName_01)) club01=true;
            if(footballClub.getName().equalsIgnoreCase(clubName_02)) club02=true;
        }

        // Displaying the error to the user
        if(club01 && club02){
            // valid club names so calculating the statistics and add them
            calculatingStatistics(clubName_01, clubName_02, numberGoalScored_club_1, numberGoalScored_club_2,
                    dateOfMatch,seasonPlayed, matchType);
            return "\n Match Successfully added! \n";

        }else{

            if(!club01 && !club02){
                return "\n Sorry,there are no clubs with the names '" + clubName_01 + "' and '" +
                        clubName_02 + "'";

            }else {
                if(!club01){
                    System.out.println();
                    return "\n Sorry,there is no club with the given name '" + clubName_01 + "'";

                }
            }
        }

        return "\n Sorry,there is no club with the given name '" + clubName_02 + "'";

    }



    private void calculatingStatistics(String clubName_01, String clubName_02, int numberGoalScored_club_1,
                                       int numberGoalScored_club_2, DateMatch date, String seasonPlayed,
                                       String matchType) {
        /*
        * This methods uses the input match details to update the stats for the football clubs respectively
        * Stats include No of matches, No of wins, No of draws, No of defeats, Current Points, Goal Difference,
        * Total yellow cards, total red cards, Goal scored and Goal Received
         */

        // Number of matches has to get incremented to both the clubs
        for (FootballClub footballClub : premierLeagueFootballClubList) {
            if(footballClub.getName().equalsIgnoreCase(clubName_01)
                    || footballClub.getName().equalsIgnoreCase(clubName_02)){

                // Number of matches has to get incremented to both the clubs and the session
                footballClub.getClubStatistics().setTotalMatchesPlayed(footballClub
                        .getClubStatistics().getTotalMatchesPlayed() + 1);
            }

            // calculate & update the goal received and goal scored for each club played
            int goalDifference = 0;
            int scored = 0;
            int received = 0;

            if(footballClub.getName().equalsIgnoreCase(clubName_01)){
                scored = numberGoalScored_club_1;
                received = numberGoalScored_club_2;
                // calculating the goal difference to club 01
                goalDifference = numberGoalScored_club_1 - numberGoalScored_club_2;

            }else if(footballClub.getName().equalsIgnoreCase(clubName_02)){
                scored = numberGoalScored_club_2;
                received = numberGoalScored_club_1;
                // calculating the goal difference to club 02
                goalDifference = numberGoalScored_club_2 - numberGoalScored_club_1;
            }
            // setting goals received and scored
            footballClub.setTotalGoalsScored(footballClub.getTotalGoalsScored() + scored);
            footballClub.setTotalGoalsReceived(footballClub.getTotalGoalsReceived() + received);

            // setting the goal difference
            footballClub.setTotalGoalsDifference(footballClub.getTotalGoalsDifference() + goalDifference);
        }

        // calculate & update the wins, draws and defeats for each club played
        if(numberGoalScored_club_1 == numberGoalScored_club_2){
            for (FootballClub footballClub : premierLeagueFootballClubList) {
                if(footballClub.getName().equalsIgnoreCase(clubName_01)
                        || footballClub.getName().equalsIgnoreCase(clubName_02)){
                    footballClub.getClubStatistics().setTotalDraws(footballClub.getClubStatistics().getTotalDraws() + 1);
                }
            }
        }else if(numberGoalScored_club_1 > numberGoalScored_club_2){
            updatingWinsDefeats(clubName_02, clubName_01);
        }else{
            updatingWinsDefeats(clubName_01, clubName_02);
        }

        // calculate & update the current score and goal difference for the clubs
        for (FootballClub footballClub: premierLeagueFootballClubList) {
            int totalScore = footballClub.getClubStatistics().getTotalWins() * 3 +
                    footballClub.getClubStatistics().getTotalDraws();
            footballClub.getClubStatistics().setTotalPointsScored(totalScore);
        }

        // creating the Match object and adding for both the clubs played with their own scores
        for (FootballClub footballClub: premierLeagueFootballClubList) {
            // we have added the matched played by each club to their respective list of matches
            if(footballClub.getName().equalsIgnoreCase(clubName_01)){
                addPlayedMatchToClub(clubName_02, clubName_01, numberGoalScored_club_2, numberGoalScored_club_1, date,
                        seasonPlayed, footballClub, matchType);

            }else if(footballClub.getName().equalsIgnoreCase(clubName_02)){
                addPlayedMatchToClub(clubName_01,clubName_02, numberGoalScored_club_1, numberGoalScored_club_2, date,
                        seasonPlayed, footballClub, matchType);
            }
        }
    }

    private void addPlayedMatchToClub(String clubName_01, String clubName_02, int numberGoalScored_club_1,
                                      int numberGoalScored_club_2, DateMatch date, String seasonPlayed,
                                      FootballClub footballClub, String matchType) {

        MatchStats matchStats = getStatsOfMatch(footballClub);
        Match matchPlayed = new Match(numberGoalScored_club_2, numberGoalScored_club_1, matchStats, date,
                clubName_01, seasonPlayed,matchType, clubName_02);
        footballClub.getMatchesPlayed().add(matchPlayed);  // adding the played match into the list of matches
    }

    private MatchStats getStatsOfMatch(FootballClub footballClub) {
        Random random = new Random();
        int numberOfYellowCards = random.nextInt(5);
        int numberOfRedCards = random.nextInt(5);
        int shots = random.nextInt(20);
        int shotsOfTarget = random.nextInt(20);
        int offSides = random.nextInt(30);
        int fouls = random.nextInt(30);
        int corners = random.nextInt(30);
        int passes = random.nextInt(30);
        double passAccuracy = Math.round(random.nextDouble()*1000)/10.0;
        double possession = Math.round(random.nextDouble()*1000)/10.0;

        // updating the total red and yellow cards for the club
        footballClub.setTotalYellowCards((footballClub.getTotalYellowCards() + numberOfYellowCards));
        footballClub.setTotalRedCards(footballClub.getTotalRedCards() + numberOfRedCards);

        // return the matchStat obj with the data parameters
        return new MatchStats(numberOfYellowCards, numberOfRedCards, shots, shotsOfTarget, offSides
        ,fouls, corners, passes, passAccuracy, possession);
    }

    // updates the wins and defeats of the played club matches
    private void updatingWinsDefeats(String clubName_01, String clubName_02) {
        for (FootballClub footballClub : premierLeagueFootballClubList) {
            if(footballClub.getName().equalsIgnoreCase(clubName_02)){
                footballClub.getClubStatistics().setTotalWins(footballClub.getClubStatistics().getTotalWins() + 1);
            }
            if(footballClub.getName().equalsIgnoreCase(clubName_01)){
                footballClub.getClubStatistics().setTotalDefeats(footballClub.getClubStatistics().getTotalDefeats() + 1);
            }
        }
    }


    // Overriding the saveDataIntoFile method from the interface
    @Override

    public String saveDataIntoFile() {
        /*
         * If we need to write and object of a Class into a file, we have to make that class to implement the interface
         * Serializable.
         * This is because Serializable interface gives the permission to save the objects
         */

        File file = new File("objectDataFile.txt");
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(premierLeagueFootballClubList);
            objectOutputStream.writeObject(matchedAdded);
            objectOutputStream.writeObject(allSeasonAdded);

        }
        catch (FileNotFoundException fileNotFoundException) {
            return " File not found exception occurred!";

        }
        catch (IOException ioException) {
            return " Exception when performing read/write operations to the file!" +
                    "\n No permission to read/write from or to the file";

        }
        catch (Exception e){
            return " An exception occurred!";

        }
        finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            }
            catch (IOException e) {
                return " Exception when performing read/write operations to the file!" +
                        "\n No permission to read/write from or to the file";
            }
        }

        return "\n Saving the data . . .\n Successfully saved!";

    }

    // Overriding the readDataFromFile method from the interface
    @Override
    public String clearDataFile() {
        // If the user needs to empty the text file details he has the option to do it as well
        /*
         * This makes sure that the file is empty, by overriding the content of the file with a single ""
         */
        FileWriter file = null;
        try {
            file = new FileWriter("objectDataFile.txt");
            file.write("");

        }
        catch (FileNotFoundException fileNotFoundException) {
            return " File not found exception occurred!";
        }
        catch (IOException ioException) {
            return " Exception when performing read/write operations to the file!" +
                    "\n No permission to read/write from or to the file";

        }
        catch (Exception e){
            return " An exception occurred!";

        }
        finally {
            try {
                if (file != null) {
                    file.close();
                }
            }
            catch (IOException e) {
                return " Exception when performing read/write operations to the file!" +
                        "\n No permission to read/write from or to the file";
            }
        }

        return "\n Clearing the contents of the file . . .\n Successfully cleared the file details!";

    }

    public static ArrayList<String> sortingTheSeasonsInAscendingOrder() {
        Comparator<String> comparator = (season1, season2) -> {
            if(Integer.parseInt(season1.split("-")[0]) > Integer.parseInt(season2.split("-")[0])){
                return 1;
            }
            return -1;
        };

        allSeasonAdded = (ArrayList<String>)allSeasonAdded.stream().distinct().collect(Collectors.toList());
        allSeasonAdded.sort(comparator);

        return allSeasonAdded;
    }

    // Generating a random match between 2 random clubs and update the txt file
    // Note that all the new random data should not be same as previous
    public void generateRandomMatch(String selectedSeason) {
        allSeasonAdded.add(selectedSeason);
        Random random = new Random();
        // step 01: randomly select 2 clubs
        int randomClub_01 = random.nextInt(seasonFilteredClubs.size());
        FootballClub selectedClub_O1 = seasonFilteredClubs.get(randomClub_01);

        int randomClub_02 = random.nextInt(seasonFilteredClubs.size());

        while (randomClub_02==randomClub_01){
            randomClub_02 = random.nextInt(seasonFilteredClubs.size());
        }
        FootballClub selectedClub_O2 = seasonFilteredClubs.get(randomClub_02);


        // step 02: randomly generate the necessary data
        int numberGoalScored_club_1 = random.nextInt(7);
        int numberGoalScored_club_2 = random.nextInt(7);

        while (numberGoalScored_club_1==numberGoalScored_club_2){
            numberGoalScored_club_2 = random.nextInt(7);
        }

        // setting the random date
        int[] possibleYears = new int[2];

        int seasonYear = Integer.parseInt(selectedSeason.split("-")[0]);

        int lastTwoDigitsOfTheYear = Integer.parseInt(String.valueOf(seasonYear).substring(2));
        possibleYears[0] = seasonYear;
        possibleYears[1] = seasonYear + 1;


        int day = random.nextInt(30)+1;
        int month = random.nextInt(12)+1;
//        System.out.println(seasonYear);
        int year = possibleYears[random.nextInt(2)];

        DateMatch date = new DateMatch(day, month, year);
        String[] matchTypes = new String[]{"Home", "Away"};
        String matchType = matchTypes[random.nextInt(2)];

        // step 03: call the calculatingStatistics() wisely by passing all the generated random data
        calculatingStatistics(selectedClub_O1.getName(), selectedClub_O2.getName(), numberGoalScored_club_1,
                numberGoalScored_club_2, date, selectedSeason, matchType);


        // step 04: call the save file method
        saveDataIntoFile();

        // step 05: call the load file method
        loadingData();

        // step 06: update the seasonBasedClubs[0] in the GUI class by calling the display table method and recalling that
        //          variable

    }

    // This will return an arraylist which will filter all the matches of the club by date
    public static ArrayList<FootballClub> filterMatchesByDate(ArrayList<FootballClub> seasonBasedClub, String dateEntered)
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
                    String matchDate = matchDay + "/" + matchMonth + "/" + matchYear;

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
}