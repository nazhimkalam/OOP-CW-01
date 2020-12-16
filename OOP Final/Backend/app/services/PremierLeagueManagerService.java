package services;
import entities.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */

public class PremierLeagueManagerService implements LeagueManager {
    // Following the Singleton design pattern, this is because we need to only create a single instance of the
    // PremierLeagueManager class

    // private variables used
    private static ArrayList<FootballClub> premierLeagueFootballClubList;
    private static boolean matchedAdded;
    private static ArrayList<String> allSeasonAdded;
    private static final int MAXIMUM_NUMBER_OF_CLUBS = 20;
    private static final int MAXIMUM_NUMBER_OF_MATCHES_PER_TEAM = 38;

    // Setters and Getters for the premierLeagueFootballClubList
    public static ArrayList<FootballClub> getPremierLeagueFootballClubList() {
        return premierLeagueFootballClubList;
    }

    public static void setPremierLeagueFootballClubList(ArrayList<FootballClub> premierLeagueFootballClubList) {
        PremierLeagueManagerService.premierLeagueFootballClubList = premierLeagueFootballClubList;
    }

    // We are using the Singleton design pattern because we only need one instance of PremierLeagueManager and not many
    // used for the singleton design pattern, this is set to "null" for lazy initialization, so we only created the
    // instance when required only," ---> non lazy way LeagueManager manager = new PremierLeagueManager(); "
    private static LeagueManager manager = null;

    // Constructor
    private PremierLeagueManagerService(){
        // initializing the variables
        matchedAdded = false;
        allSeasonAdded = new ArrayList<>();
        premierLeagueFootballClubList= new ArrayList<>();

        // load the previously saved data from the file
        loadingData();
    }

    // This method is used for the Singleton Design Pattern, inorder to get the single instance of the class
    public static LeagueManager getInstance(){
        // Double checked locking (due to the double If condition)

        if(manager==null){
            // This is to check if an instance of the manager has already been created or not (For the first time
            // when the instance needed to be created), before adding the synchronized lock

            synchronized (PremierLeagueManagerService.class){
                // makes sure Thread Safe, if 2 instance are to be created at the same time

                if(manager==null){
                    // This is for ensuring and checking if another created instance when created it checks with this
                    // null and only return the reference of the first instance than creating another one.

                    manager = new PremierLeagueManagerService();
                }
            }
        }
        return manager;
    }

    // this method is for loading the data from the file
    public static void loadingData() {
        // Serializing means converting a state into a byte stream

        // text file path
        File file = new File("public/resources/objectDataFile.txt");

        // used to read the byte stream data from a source which in this case is a txt file
        FileInputStream fileInputStream = null;

        // used to read object data when its serialized
        ObjectInputStream objectInputStream = null;

        // Cleaning the loading variables before use (this is mainly done for clearing the file problem)
        premierLeagueFootballClubList = new ArrayList<>();
        matchedAdded = false;
        allSeasonAdded = new ArrayList<>();


        // handling the exceptions and loading the data from the file
        try {
            // At first we read the bytes of data from the file using the FileInputStream and then its filtered
            // though the ObjectInputStream which converts these bytes into Java Objects

            // creating an instance of FileInputStream and ObjectInputStream
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);

            try {
                // reading from the file
                // we typecast because when reading the object because it doesn't know what type is the object read
                // from the file
                premierLeagueFootballClubList = (ArrayList<FootballClub>) objectInputStream.readObject();
                matchedAdded = (boolean) objectInputStream.readObject();
                setAllSeasonAdded((ArrayList<String>) objectInputStream.readObject());

            } catch (ClassNotFoundException e) {
                // Handles exception
                // System.out.println(" ClassNotFoundException occurred Not able to find the class");;
            }


        }
        catch (FileNotFoundException fileNotFoundException){
            // Handles exception
            // System.out.println(" File not found exception occurred!");
        }
        catch (IOException ioException) {
            // Handles exception
            // System.out.println( " Exception when performing read/write operations to the file!" +
               //      "\n No permission to read/write from or to the file");

        }
        finally {
            // closing the file once all the data is loaded
            try{
                // making sure that it is not null, to be closed
                if (fileInputStream != null) {
                    fileInputStream.close();
                }

                // making sure that it is not null, to be closed
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            }
            catch (IOException ioException) {
                // Handles exception
                // System.out.println( " Exception when performing read/write operations to the file!" +
                   //     "\n No permission to read/write from or to the file");

            }
        }
        // System.out.println( "\n Successfully loaded all the data\n");
    }

    // getter for allSeasonsAdded list
    public static ArrayList<String> getAllSeasonAdded() {
        return allSeasonAdded;
    }

    // setter for allSeasonsAdded list
    public static void setAllSeasonAdded(ArrayList<String> allSeasonAdded) {
        PremierLeagueManagerService.allSeasonAdded = allSeasonAdded;
    }

    // Overriding the createClub method from the interface
    @Override
    public String createClub(String clubName, String location, String coachName, String universitySchoolName,
                             String clubType) {

        // variable used
        FootballClub club = null;

        // this is to create the appropriate instance depending on the user input
        switch (clubType) {
            case "normal":
                club = new FootballClub(clubName, location, coachName);
                break;

            case "university":
                club = new UniversityFootballClub(clubName, location, coachName, universitySchoolName);
                break;

            case "school":
                club = new SchoolFootballClub(clubName, location, coachName, universitySchoolName);
                break;
        }

        // Checking if the maximum number of clubs created limit has been reached to add the club or not
        if(premierLeagueFootballClubList.size()<MAXIMUM_NUMBER_OF_CLUBS)
        {
            premierLeagueFootballClubList.add(club);
            return " Clubs Successfully added!";
        }
        return " Sorry there is no room for a new club, the maximum number of club limit has been reached!";

    }

    // Overriding the deleteCLub method from the interface
    @Override
    public FootballClub deleteCLub(String clubName) {

        // This loop searches for the club and deletes it from the list
        for (int index = 0; index < premierLeagueFootballClubList.size(); index++) {
            if(premierLeagueFootballClubList.get(index).getName().equalsIgnoreCase(clubName)){

                // if the club name is present it is removed
                return premierLeagueFootballClubList.remove(index);
            }
        }

        // returns null if there is not club present with the given name
        return null;

    }

    // Overriding the displayStats method from the interface
    @Override
    public String displayStats(String clubName) {

        // variable for checking if the club name is valid or not
        boolean clubNameAvailable = false;

        // This loop searches for the club and displays it's statistics
        for (FootballClub footballClub : premierLeagueFootballClubList) {
            if (footballClub.getName().equalsIgnoreCase(clubName)) {

                // checks if the club name entered is present in the club list
                clubNameAvailable = true;

                System.out.println("\n ===============> S T A T I S T I C S <===============");
                System.out.println("\n =============> PLAYERS - STATISTICS <=============\n");

                // loops and displays the player details
                for (int index = 0; index < footballClub.getPlayersList().size(); index++) {
                    System.out.println(" <------------ Player " + ( index + 1 ) + " ---------------->\n");
                    System.out.println(footballClub.getPlayersList().get(index));
                }

                // displays the total statistics together from all the seasons together
                System.out.println("\n =============>  FROM  ALL  SEASONS  <=============\n");
                System.out.println(footballClub.toString());


                // sorting the seasons in ascending
                Comparator<String> comparator = (season1, season2) -> {
                    if(Integer.parseInt(season1.split("-")[0]) > Integer.parseInt(season2.split("-")[0])){
                        return 1;
                    }
                    return -1;
                };

                // filters the seasons by getting the distinct seasons and sorting them using the comparator, this
                // will be useful when displaying the GUI for the drop down menu
                setAllSeasonAdded((ArrayList<String>) getAllSeasonAdded().stream().distinct()
                        .collect(Collectors.toList()));
                getAllSeasonAdded().sort(comparator);

                // Display the total stats by the clubs played in season wise
                for (String season : getAllSeasonAdded()) {
                    System.out.println("\n =============> FOR SEASON (" + season + ") <=============\n");
                    ArrayList<FootballClub> seasonFilteredClubs = new ArrayList<>();
                    try {
                        // gets the list of football clubs with the filtered matches by season
                        seasonFilteredClubs = seasonFilteredFootballCLubList(season);

                    } catch (CloneNotSupportedException e) {
                        // handles exception
                        e.printStackTrace();

                    }

                    for (FootballClub club: seasonFilteredClubs){

                        if(club.getName().equalsIgnoreCase(clubName)) {
                            // we search for the club with the name user have given and display the result
                            System.out.println(club);
                        }
                    }

                }

                // variable
                int number = 0;

                // looping through each played match and displaying their stats
                if(footballClub.getMatchesPlayed().size()!=0){

                    // displaying the statistics
                    System.out.println(" =============>  FROM  ALL  SEASONS  <=============\n");
                    System.out.println(" => Statistics of all the matches played by '"+ clubName + "' so far! <=");

                    for (Match match:footballClub.getMatchesPlayed()) {
                        String matchResult = "\n <===============> Match "+ (++number) +" <================>\n "
                                + "* Opponent team name: '" + match.getOpponentClubName() + "'" + match.getDate()
                                + "\n * Season: " + match.getSeason() + "\n\n * Match Type: '" +
                                match.getMatchType() + "'"
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

        // checking if the given club name is valid or not and return the appropriate message
        if(!clubNameAvailable){
            return "\n Sorry, there is no club with the given name '" + clubName + "'";
        }

        return " Result Displayed";
    }

    // Overriding the displayLeagueTable method from the interface
    @Override
    public void displayLeagueTable(String seasonPlayed) {
        // This method is used to display the Premier League Table in the CLI

        // we add all the football clubs with all the necessary matches related to the season and other removed.
        ArrayList<FootballClub> seasonFilteredClubs = new ArrayList<>();

        try {
            // Gets the filtered football clubs by season entered
            seasonFilteredClubs = seasonFilteredFootballCLubList(seasonPlayed);

        } catch (CloneNotSupportedException e) {
            // handles the exception
            e.printStackTrace();

        }

        // This mainly depends on the length of the club name the rest are normal and fixed
        if (seasonFilteredClubs.size() != 0){

            // getting maximum length club name from the list.
            int maxClubNameLength = seasonFilteredClubs.get(0).getName().length();

            for (FootballClub footballClub : seasonFilteredClubs) {
                // we find the maximum length of the club names from the list of football clubs

                if(footballClub.getName().length() > maxClubNameLength){
                    // this is also used for the CLI table structure because when the club name changes in length
                    // the CLI table will also get spoilt so to prevent this we get the max length of the string
                    // and solve the issue
                    maxClubNameLength = footballClub.getName().length();
                }
            }

            // Implementing the comparator for sorting
            /*
             * Comparator is an interface in java which is
             * used to sort collections using two objects as its parameter
             * inputs.
             */
            // here we are using an anonymous class to create the comparator.
            // Sorting the points and goals in descending order for the football clubs
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

            // sorting the list with a new arrayList
            seasonFilteredClubs.sort(comparator);  // sorting the clubs

            // function for creating the structure of the table
            structuredTable(maxClubNameLength, seasonFilteredClubs);
        }else{
            // creating the empty table when there are no clubs present
            structuredTable(0, seasonFilteredClubs);
        }

    }

    // This method returns a list of football clubs filtered by season with updated stats for that season only.
    public static ArrayList<FootballClub> seasonFilteredFootballCLubList(String seasonPlayed)
            throws CloneNotSupportedException {

        // creating a new Football arraylist to collect football clubs for a particular season
        ArrayList<FootballClub> footballClubsListSeason = new ArrayList<>();

        // we add all the clubs, before adding the club remove the matches which aren't related
        for (int index = 0; index < premierLeagueFootballClubList.size(); index++) {

            // here we are cloning the football club in every loop
            footballClubsListSeason.add((FootballClub) premierLeagueFootballClubList.get(index).clone());

            int matchIndexLoop = 0;

            // this loops runs for every single match in each of the football club
            while ( matchIndexLoop < footballClubsListSeason.get(index).getMatchesPlayed().size() ){

                // checks if the match season is equal to the season entered by the user as well and then we proceed
                if(!footballClubsListSeason.get(index).getMatchesPlayed().get(matchIndexLoop).getSeason()
                        .equalsIgnoreCase(seasonPlayed)){

                    // update the stats before removing the match
                    int goalScored = footballClubsListSeason.get(index).getMatchesPlayed().get(matchIndexLoop)
                            .getGoalScored();
                    int goalReceived = footballClubsListSeason.get(index).getMatchesPlayed().get(matchIndexLoop)
                            .getGoalReceived();

                    // updating total goal difference
                    footballClubsListSeason.get(index).setTotalGoalsDifference(
                            footballClubsListSeason.get(index).getTotalGoalsDifference() - (goalScored - goalReceived)
                    );

                    // updating total goal scored
                    footballClubsListSeason.get(index).setTotalGoalsScored(
                            footballClubsListSeason.get(index).getTotalGoalsScored() -
                                    footballClubsListSeason.get(index).getMatchesPlayed().get(matchIndexLoop)
                                            .getGoalScored()
                    );

                    // updating total goal received
                    footballClubsListSeason.get(index).setTotalGoalsReceived(
                            footballClubsListSeason.get(index).getTotalGoalsReceived() -
                                    footballClubsListSeason.get(index).getMatchesPlayed().get(matchIndexLoop)
                                            .getGoalReceived()
                    );

                    // updating total yellow cards
                    footballClubsListSeason.get(index).setTotalYellowCards(
                            footballClubsListSeason.get(index).getTotalYellowCards() -
                                    footballClubsListSeason.get(index).getMatchesPlayed().get(matchIndexLoop)
                                            .getMatchStats().getYellowCards()
                    );

                    // updating total red cards
                    footballClubsListSeason.get(index).setTotalRedCards(
                            footballClubsListSeason.get(index).getTotalRedCards() -
                                    footballClubsListSeason.get(index).getMatchesPlayed().get(matchIndexLoop)
                                            .getMatchStats().getRedCards()
                    );

                    // update number of matches
                    footballClubsListSeason.get(index).getClubStatistics().setTotalMatchesPlayed(
                            footballClubsListSeason.get(index).getClubStatistics().getTotalMatchesPlayed() - 1
                    );

                    if(goalScored > goalReceived){

                        // update wins and points scored
                        footballClubsListSeason.get(index).getClubStatistics().setTotalWins(
                                footballClubsListSeason.get(index).getClubStatistics().getTotalWins() - 1
                        );

                        footballClubsListSeason.get(index).getClubStatistics().setTotalPointsScored(
                                footballClubsListSeason.get(index).getClubStatistics().getTotalPointsScored() - 3
                        );

                    }else if (goalReceived > goalScored){
                        // update defeats
                        footballClubsListSeason.get(index).getClubStatistics().setTotalDefeats(
                                footballClubsListSeason.get(index).getClubStatistics().getTotalDefeats() - 1
                        );

                    }else{

                        // update draws and points scored
                        footballClubsListSeason.get(index).getClubStatistics().setTotalDraws(
                                footballClubsListSeason.get(index).getClubStatistics().getTotalDraws() - 1
                        );

                        footballClubsListSeason.get(index).getClubStatistics().setTotalPointsScored(
                                footballClubsListSeason.get(index).getClubStatistics().getTotalPointsScored() - 1
                        );
                    }

                    // removing the match from the list
                    footballClubsListSeason.get(index).getMatchesPlayed().remove(
                            footballClubsListSeason.get(index).getMatchesPlayed().get(matchIndexLoop)
                    );
                }else{
                    // incrementing the index to skip that match which should not be removed
                    matchIndexLoop++;
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
          // These variables are used to create the structure of the table
          int clubNameColSpace = lengthOfClubNameTable + 2;
          int leftClubColSpace = clubNameColSpace/2;
          int rightClubColSpace = clubNameColSpace - leftClubColSpace;

          StringBuilder PREMIER_LEAGUE_SPACE_TILE_LEFT = new StringBuilder();
          StringBuilder PREMIER_LEAGUE_SPACE_TILE_RIGHT = new StringBuilder();
          StringBuilder LEFT_CLUB_COL_SPACE = new StringBuilder();
          StringBuilder RIGHT_CLUB_COL_SPACE = new StringBuilder();

          // All these loops and code block are to just create the CLI table
          for (int index = 0; index < 107+lengthOfClubNameTable; index++) {
              HORIZONTAL_DASHES.append("-");
          }
          for (int index = 0; index < 39 + (lengthOfClubNameTable/2); index++) {
              PREMIER_LEAGUE_SPACE_TILE_LEFT.append(" ");
          }
          for (int index = 0; index < 39 + (lengthOfClubNameTable - (lengthOfClubNameTable/2)); index++) {
              PREMIER_LEAGUE_SPACE_TILE_RIGHT.append(" ");
          }
          for (int index = 0; index < leftClubColSpace; index++) {
              LEFT_CLUB_COL_SPACE.append(" ");
          }
          for (int index = 0; index < rightClubColSpace; index++) {
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
          for (int index = 0; index < seasonFilteredClubs.size(); index++) {
              StringBuilder clubNameEndSpace = new StringBuilder();

              for (int innerIndex = 0; innerIndex < 3; innerIndex++) {
                  clubNameEndSpace.append(" ");
              }

              // changing the width of the club name for each row
              if(seasonFilteredClubs.get(index).getName().length() != lengthOfClubNameTable){

                  // the length of the name will anyways be less than lengthOfClubNameTable
                  int difference = lengthOfClubNameTable - seasonFilteredClubs.get(index).getName().length();
                  for (int innerIndex = 0; innerIndex < difference; innerIndex++) {
                      clubNameEndSpace.append(" ");
                  }

              }

              /*
              *  creating an arraylist with organised data for the table
              *  The content structure is [position, played match, won, drawn, lost, goal scored, goal received, points,
              *  goal difference]
              */
              ArrayList<String> organisedResultList = new ArrayList<>();
              if(index<9){
                  organisedResultList.add("0"+(index+1));
              }else{
                  organisedResultList.add(String.valueOf(index+1));
              }

              // getting the stats into an arraylist to organise it
              for (int innerIndex = 0; innerIndex < seasonFilteredClubs.get(index).getMainStatistics().size();
                   innerIndex++) {

                  if(innerIndex==7){

                      // working with the goal difference
                      if(seasonFilteredClubs.get(index).getMainStatistics().get(innerIndex)>-1){

                          // organising the data for the CLI table
                          if(seasonFilteredClubs.get(index).getMainStatistics().get(innerIndex)<10) {
                              organisedResultList.add("+0"+seasonFilteredClubs.get(index).getMainStatistics()
                                      .get(innerIndex));

                          }else{
                              organisedResultList.add("+"+seasonFilteredClubs.get(index).getMainStatistics()
                                      .get(innerIndex));

                          }
                      }else{

                          // organising the data for the CLI table
                          if(seasonFilteredClubs.get(index).getMainStatistics().get(innerIndex)>-10) {
                              organisedResultList.add("-0"+Math.abs(seasonFilteredClubs.get(index)
                                      .getMainStatistics().get(innerIndex)));

                          }else{
                              organisedResultList.add(String.valueOf(seasonFilteredClubs.get(index)
                                      .getMainStatistics().get(innerIndex)));

                          }
                      }
                  }else{
                      // organising the data for the CLI table
                      if(seasonFilteredClubs.get(index).getMainStatistics().get(innerIndex)<10){
                          organisedResultList.add("0"+seasonFilteredClubs.get(index).getMainStatistics()
                                  .get(innerIndex));

                      }else{
                          organisedResultList.add(String.valueOf(seasonFilteredClubs.get(index)
                                  .getMainStatistics().get(innerIndex)));

                      }

                  }
              }

              // if not matches are added then fixed positions cannot be given for any club until they play a match
              if(!matchedAdded){
                  organisedResultList.set(0, "00");
              }

              // this is were the table is created
              System.out.println("|    "+organisedResultList.get(0)+ "    |   "+ seasonFilteredClubs.get(index).getName()
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
          for (int innerIndex = 0; innerIndex < 106; innerIndex++) {
              HORIZONTAL_DASHES.append("-");
          }
          for (int innerIndex = 0; innerIndex < 38; innerIndex++) {
              PREMIER_LEAGUE_SPACE_TILE.append(" ");
          }

          // print the table
          System.out.println("\n"+HORIZONTAL_DASHES);
          System.out.println("|" + PREMIER_LEAGUE_SPACE_TILE + " P R E M I E R - L E A G U E" + PREMIER_LEAGUE_SPACE_TILE + "|");
          System.out.println(HORIZONTAL_DASHES);
          System.out.println("| Position |         Club         | Played | Won | Drawn | Lost | Goal-Scored " +
                  "| Goal-Difference | Points |");
          System.out.println(HORIZONTAL_DASHES);

          //  creating the empty rows
          for (int index = 0; index < 10; index++) {
              System.out.println("|          |                      |        |     |       |      |           " +
                      "  |                 |        |");
          }
      }
        System.out.println("\n\n");
    }

    // Overriding the addPlayedMatch method from the interface
    @Override
    public String addPlayedMatch(String seasonPlayed, String clubName_01, String clubName_02,
                                 int numberGoalScored_club_1, int numberGoalScored_club_2, DateMatch dateOfMatch,
                                 String matchType) {

        // checking if the maximum number of matches has been reached or not, even if either club reached to the max
        // then the match is cancelled
        boolean club1ReachedMaximumMatches = false;
        boolean club2ReachedMaximumMatches = false;
        FootballClub club1 = null;
        FootballClub club2 = null;
        int matchCounter = 0;

        // getting the clubs from the name of club received as the parameter
        for (FootballClub club: premierLeagueFootballClubList) {

            if(club.getName().equalsIgnoreCase(clubName_01)){
                club1 = club;

            }else if(club.getName().equalsIgnoreCase(clubName_02)){
                club2 = club;

            }

        }

        // if both the entered clubs are valid only we continue
        if(club1!=null && club2!=null){

            // we are checking if the club will reach the maximum limit of matches played per club for (club1)
            for (Match match: club1.getMatchesPlayed()) {

                if(match.getSeason().equals(seasonPlayed)){
                    matchCounter++;
                    club1ReachedMaximumMatches = matchCounter >= MAXIMUM_NUMBER_OF_MATCHES_PER_TEAM;
                }

            }

            matchCounter = 0;
            // we are checking if the club will reach the maximum limit of matches played per club for (club2)
            for (Match match: club2.getMatchesPlayed()) {

                if(match.getSeason().equals(seasonPlayed)){
                    matchCounter++;
                    club2ReachedMaximumMatches = matchCounter >= MAXIMUM_NUMBER_OF_MATCHES_PER_TEAM;
                }

            }

        }

        // If both of the clubs didn't the max number to matches limit only we then add the match
        if( !club2ReachedMaximumMatches && !club1ReachedMaximumMatches){

            // Adding the played season
            getAllSeasonAdded().add(seasonPlayed);

            // check if the enter clubs are real and display msg
            boolean club01 = false;
            boolean club02 = false;

            // checking if the clubs entered are valid
            for (FootballClub footballClub : premierLeagueFootballClubList) {
                if(footballClub.getName().equalsIgnoreCase(clubName_01)) club01=true;
                if(footballClub.getName().equalsIgnoreCase(clubName_02)) club02=true;
            }

            // Checking if the entered club names are valid to further proceed
            if(club01 && club02){

                // valid club names so calculating the statistics and add them
                calculatingStatistics(clubName_01, clubName_02, numberGoalScored_club_1, numberGoalScored_club_2,
                        dateOfMatch,seasonPlayed, matchType);
                return "\n Match Successfully added! \n";

            }else{

                // If in valid club names we return an appropriate message to the user
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

        // if maximum number of matches limit has reaches we return an appropriate message to the user
        if(club1ReachedMaximumMatches && club2ReachedMaximumMatches){
            return "\n Sorry, both the clubs have reached the maximum number of matches played!";

        }else if(club1ReachedMaximumMatches){
            return "\n Sorry, '" + clubName_01 + "' has reached the maximum number of matches played!";

        }

        // returns appropriate message
        return "\n Sorry, '" + clubName_02 + "' has reached the maximum number of matches played!";

    }

    // This method is used to calculate the statistics
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

                    footballClub.getClubStatistics().setTotalDraws(footballClub.getClubStatistics()
                            .getTotalDraws() + 1);
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
                addPlayedMatchToClub(clubName_01, clubName_02, numberGoalScored_club_1, numberGoalScored_club_2, date,
                        seasonPlayed, footballClub, matchType);

            }
        }
    }

    // This method is used to add the played match to the club
    private void addPlayedMatchToClub(String clubName_01, String clubName_02, int numberGoalScored_club_1,
                                      int numberGoalScored_club_2, DateMatch date, String seasonPlayed,
                                      FootballClub footballClub, String matchType) {

        // creating the match statistics object with the data to be stored
        MatchStats matchStats = getStatsOfMatch(footballClub);

        // creating a match object with the data to be stored
        Match matchPlayed = new Match(numberGoalScored_club_2, numberGoalScored_club_1, matchStats, date,
                clubName_01, seasonPlayed,matchType, clubName_02);

        // adding the played match into the list of matches
        footballClub.getMatchesPlayed().add(matchPlayed);

    }

    // This method is used to get the match statistics which are randomly generated
    private MatchStats getStatsOfMatch(FootballClub footballClub) {
        Random random = new Random();

        // variables with the random data set to be used for the match statistics
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

        // Serializing means converting a state into a byte stream

        // getting the path to save the data
        File file = new File("public/resources/objectDataFile.txt");

        // This is an out stream which is used to write data into a file
        FileOutputStream fileOutputStream = null;

        // This encodes the java objects into byte streams which can be stored into the file
        ObjectOutputStream objectOutputStream = null;

        // handling the exceptions and saving the data from the file
        try {
            // saving the data into the file

            // creating an instance of FileInputStream and ObjectInputStream
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // writing the objects into the file
            objectOutputStream.writeObject(premierLeagueFootballClubList);
            objectOutputStream.writeObject(matchedAdded);
            objectOutputStream.writeObject(getAllSeasonAdded());

        }
        catch (FileNotFoundException fileNotFoundException) {
            // Handles the exception
            return " File not found exception occurred when saving!";

        }
        catch (IOException ioException) {
            // Handles the exception
            return " Exception when performing read/write operations to the file!" +
                    "\n No permission to read/write from or to the file";

        }
        catch (Exception e){
            // Handles the exception
            return " An exception occurred!";

        }
        finally {
            // once all the data is saved into the file we close it

            try {
                // making sure that it is not null, to be closed
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }

                // making sure that it is not null, to be closed
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            }
            catch (IOException e) {
                // Handles the exception
                return " Exception when performing read/write operations to the file!" +
                        "\n No permission to read/write from or to the file";

            }
        }

        // returns a success message if everything goes well
        return "\n Saving the data . . .\n Successfully saved!";
    }

    // Overriding the readDataFromFile method from the interface
    @Override
    public String clearDataFile() {
        // If the user needs to empty the text file details he has the option to do it as well
        /*
         * This makes sure that the file is empty, by overriding the content of the file with a single ""
         */

        // using file write the data won't be converted into any byte stream it will directly set the exact string what
        // you are setting
        FileWriter file = null;
        try {
            file = new FileWriter("public/resources/objectDataFile.txt");

            // clearing the content of the file by overriding with an empty string
            file.write("");

        }
        catch (FileNotFoundException fileNotFoundException) {
            // Handles the exception
            return " File not found exception occurred when clearing the file!";

        }
        catch (IOException ioException) {
            // Handles the exception
            return " Exception when performing read/write operations to the file!" +
                    "\n No permission to read/write from or to the file";


        }
        catch (Exception e){
            // Handles the exception
            return " An exception occurred!";

        }
        finally {
            // closes the file once all the operations are completed
            try {
                if (file != null) {
                    file.close();
                }
            }
            catch (IOException e) {
                // Handles the exception
                return " Exception when performing read/write operations to the file!" +
                        "\n No permission to read/write from or to the file";
            }
        }

        // returns a success message if everything goes well
        return "\n Clearing the contents of the file . . .\n Successfully cleared the file details!";

    }
}
