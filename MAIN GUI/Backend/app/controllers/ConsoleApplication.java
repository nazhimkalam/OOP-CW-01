package controllers;

import java.util.Scanner;

/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */

/*
 *   ASSUMPTIONS:
 *   --> ALL THE FOOTBALL CLUB TEAMS CREATED SHOULD HAVE UNIQUE TEAM NAMES
 */

public class ConsoleApplication {
    // VARIABLES
    private static final LeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

    // MAIN METHOD
    public static void main(String[] args) {

        displayMenu();

    }

    // THIS IS MENU METHOD
    private static void displayMenu() {
        System.out.println(" ________________________________________________________________________________________________\n" +
        "|                                        W E L C O M E                                           |\n" +
        "|________________________________________________________________________________________________|\n" +
        "|                                      M A I N   M E N U                                         |\n" +
        "|________________________________________________________________________________________________|\n" +
        "| (Option 1) Enter '1' to create a new football club and to add it in the Premier League         |\n" +
        "| (Option 2) Enter '2' to delete an existing club from the Premier League                        |\n" +
        "| (Option 3) Enter '3' to display the various statistics for a selected club                     |\n" +
        "| (Option 4) Enter '4' to display the Premier League table                                       |\n" +
        "| (Option 5) Enter '5' to add a played match                                                     |\n" +
        "| (Option 6) Enter '6' to save all the information entered into a file                           |\n" +
        "| (Option 7) Enter '7' to clear the data in the file                                             |\n" +
        "| (Option 8) Enter '8' to exit the program                                                       |\n" +
        "|________________________________________________________________________________________________|\n");

        // getting the users input
        int userSelectOption = validatingIntegers(" Enter your option (please enter only integers): ");
        String result;
        // directing the users option to appropriate methods
        switch (userSelectOption)
        {
            case 1:
                creatingClub();                 // THIS IS WERE I TAKE ALL THE USER INPUTS
                displayMenu();
                break;

            case 2:
                deleteCLub();                   // THIS IS WERE I TAKE ALL THE USER INPUTS
                displayMenu();
                break;
                
            case 3:
                displayStatistics();            // THIS IS WERE I TAKE ALL THE USER INPUTS
                displayMenu();
                break;

            case 4:
                String seasonPlayed = validatingSeason();        // DISPLAYING THE PREMIER LEAGUE TABLE
                premierLeagueManager.displayLeagueTable(seasonPlayed);
                displayMenu();
                break;

            case 5:
                addPlayedMatch();              // THIS IS WERE I TAKE ALL THE USER INPUTS
                displayMenu();
                break;

            case 6:
                result = premierLeagueManager.saveDataIntoFile();       // saving the data into a file
                System.out.println(result);

                displayMenu();
                break;

            case 7:
                result = premierLeagueManager.clearDataFile();          // clearing the file
                System.out.println(result);

                displayMenu();
                break;
            case 8:
                // Asking the user if he needs to save before exiting or not
                Scanner input = new Scanner(System.in);
                System.out.println("\n Do you want to save before exiting? ");
                System.out.print(" Enter 'Y' or 'y' to save else enter any other key to exit: ");
                String saveOrExit = input.nextLine();

                if(saveOrExit.equals("y") || saveOrExit.equals("Y")){
                    premierLeagueManager.saveDataIntoFile();       // saving
                }

                System.out.println(" Exiting program . . .");   // quitting the program
                System.exit(200);

            default:
                System.out.println(" You have entered an invalid option!");
                System.out.println(" Please check the menu properly and re-enter!");
                displayMenu();

        }
    }


    private static void addPlayedMatch() {
         /* ADD A PLAYED MATCH WITH IT'S SCORE AND UPDATE THE STATISTICS AND LIST OF MATCHES FOR THE RESPECTIVE CLUBS
           PLAYED */

        Scanner input = new Scanner(System.in);
        System.out.println("\n Enter details of the played match");

        // Checking if it is a valid club else throwing up and error and asking user to re-enter
        String clubName_01 = checkingForValidClub(" Enter club 1 name: ");
        clubName_01 = clubName_01.substring(0, 1).toUpperCase() + clubName_01.toLowerCase().substring(1);


        // validation for the scores
        int numberGoalScored_club_1 = validatingIntegers(" Enter the number of goal scored: ");

        // Checking if it is a valid club else throwing up and error and asking user to re-enter
        String clubName_02 = checkingForValidClub(" Enter club 2 name: ");
        clubName_02 = clubName_02.substring(0, 1).toUpperCase() + clubName_02.toLowerCase().substring(1);

        // Checking if the user has entered duplicated club names
        while(clubName_01.equalsIgnoreCase(clubName_02)){
            System.out.println("\n Please enter a different club name ");
            clubName_02 = checkingForValidClub(" Enter club 2 name: ");
            clubName_02 = clubName_02.substring(0, 1).toUpperCase() + clubName_02.toLowerCase().substring(1);

        }

        // validation for the scores and some stats
        int numberGoalScored_club_2 = validatingIntegers(" Enter the number of goal scored: ");

        // getting the date of the match played as the input from the user
        int day = validatingIntegers(" Enter the day (only integers are accepted): ");
        // validating the day entered
        while(day<1 || day>31){
            System.out.println(" Invalid day entered! \n");
            day = validatingIntegers(" Enter the day (only integers are accepted): ");
        }


        int month = validatingIntegers(" Enter the month (only integers are accepted): ");
        // validating the month entered
        while(month<1 || month>12){
            System.out.println(" Invalid month entered! \n");
            month = validatingIntegers(" Enter the month (only integers are accepted): ");
        }

        int year = validatingIntegers(" Enter the year (only integers are accepted): ");
        // validating the year entered
        while(year<1000 || year>3000){
            System.out.println(" Invalid year entered! \n");
            year = validatingIntegers(" Enter the year (only integers are accepted): ");
        }

        DateMatch date = new DateMatch(day, month, year);
        // we are displaying the season options possible for the match played for the given date
        String[] possibleSeason = new String[2];
        System.out.println(" These are the possible seasons for the match played from the given date");

        int lastTwoDigitsOfTheYear = Integer.parseInt(String.valueOf(year).substring(2));

        possibleSeason[0] = (year-1) + "-" + (lastTwoDigitsOfTheYear);
        possibleSeason[1] = (year) + "-" + (lastTwoDigitsOfTheYear+1);

        for (int i = 0; i < possibleSeason.length; i++) {
            System.out.println("  " + (i+1) + ". " + possibleSeason[i]);
        }

        int seasonOption = validatingIntegers(" Please select a season from the given list (Enter '1' or '2') : ");


        boolean invalidOption = true;
        while (invalidOption){                           // validating the inputs
            if(seasonOption!=1 && seasonOption!=2){
                System.out.println(" Invalid Input!");
                seasonOption = validatingIntegers(" Please select a season from the given list (Enter '1' or '2') : ");
            }else{
                invalidOption=false;
            }
        }
        String seasonPlayed = possibleSeason[seasonOption-1];

        // type of match played 'Home' or 'Away'
        boolean validMatchEntered;
        String matchType;

        do{
            System.out.print(" Enter the type of match played (Home or Away): ");
            matchType = input.nextLine();
            validMatchEntered = matchType.equalsIgnoreCase("home") || matchType.equalsIgnoreCase("away");
            if(!validMatchEntered)
                System.out.println("Invalid match input!");

        }while (!validMatchEntered);


        System.out.print(" Are you sure that the details entered are correct, if you need to re-enter," +
                " enter 'Y' or 'y'" + " else enter any key to continue: ");
        input = new Scanner(System.in);
        String confirmation = input.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            System.out.println(" Please re-enter the details ");
            addPlayedMatch();

        }else{
            String result = premierLeagueManager.addPlayedMatch(seasonPlayed, clubName_01, clubName_02, numberGoalScored_club_1,
                    numberGoalScored_club_2, date, matchType);
            System.out.println(result);

        }

    }

    private static String checkingForValidClub(String message) {
        // CHECKING FOR VALID CLUB ENTERED BY THE USER WHEN ADDING MATCH

        Scanner input = new Scanner(System.in);
        System.out.print(message);
        String clubName = input.nextLine();

        boolean invalidClubName = true;
        while (invalidClubName){
            for (FootballClub footballClub: PremierLeagueManager.premierLeagueFootballClubList) {
                if (footballClub.getName().equalsIgnoreCase(clubName)) {
                    invalidClubName = false;
                    break;
                }
            }
            if(invalidClubName){
                System.out.println(" There is no team with the name '" + clubName + "', please enter another name\n");
                System.out.print(message);
                clubName = input.nextLine();
            }
        }
        return clubName;
    }

    // VALIDATING THE SEASON
    private static String validatingSeason() {
        String seasonPlayed = "";
        Scanner input = new Scanner(System.in);
        boolean validatingSeason;
        do{
            validatingSeason = false;
            System.out.print(" Season played (eg:- '2018-19')\n Enter the season of the match played: ");
            seasonPlayed = input.nextLine();
            if(seasonPlayed.matches("\\d{4}-\\d{2}"))
                validatingSeason = true;
            else
                System.out.println("\n Given input is not in proper format! ");
        }while (!validatingSeason);

        return seasonPlayed;
    }

    // THIS DEALS WITH DISPLAYING THE STATISTICS OF THE FOOTBALL CLUB
    private static void displayStatistics() {

        // DISPLAYING THE STATISTICS OF A SELECTED CLUB BY THE USER ITSELF
        Scanner input = new Scanner(System.in);
        System.out.print(" Enter the club name of which you need to display the statistics: ");
        String clubName = input.nextLine();

        String result = premierLeagueManager.displayStats(clubName);

        // DISPLAYING THE RESULT IF THERE WAS NO CLUB WITH THE GIVEN NAME
        if(!result.equals(" Result Displayed")) {
            System.out.println(result);

        }

    }

    // THIS DEALS WITH DELETING THE FOOTBALL CLUB FROM THE LIST
    private static void deleteCLub() {

        // DELETING A CLUB (BY ITS NAME) FROM THE LIST OF CLUBS IN THE PREMIER LEAGUE
        Scanner input = new Scanner(System.in);
        System.out.print(" Enter the name of the club you wish to remove from the premier league: ");
        String clubName = input.nextLine();

        String confirmation = "";
        boolean isValidClubName = false;
        // DISPLAY RESULT OF THE ITEM TO BE REMOVED
        for (int i = 0; i < PremierLeagueManager.premierLeagueFootballClubList.size(); i++) {
            if(PremierLeagueManager.premierLeagueFootballClubList.get(i).getName().equalsIgnoreCase(clubName)){
                System.out.println("\n These are some details of the club you wanted to be deleted \n");
                System.out.println(PremierLeagueManager.premierLeagueFootballClubList.get(i));
                isValidClubName = true;

                // ASK FOR CONFIRMATION
                System.out.print(" Enter 'y' or 'Y' to confirm the deletion or enter any other key to skip the deletion: ");
                confirmation = input.nextLine();
            }
        }

        if(isValidClubName){
            if(confirmation.equalsIgnoreCase("y")){

                // GETTING THE REMOVED CLUB RESULT (MAY BE NULL OR THE CLUB REMOVED)
                FootballClub removedClub = (FootballClub) premierLeagueManager.deleteCLub(clubName);

                // THIS GIVES THE OUTPUT TO THE USER INDICATING IF THE ITEM WAS SUCCESSFULLY REMOVED OR NOT
                if(removedClub != null){
                    System.out.println("\n The club with the name '" + clubName + "' is successfully removed!\n");
                    System.out.println(" Here are some details related to the deleted club ");
                    System.out.println(removedClub);
                }else{
                    System.out.println("\n Sorry, there is no club with the given name '" + clubName + "'");
                }

            }else{
                System.out.println(" Successfully cancelled the deletion request for club '" + clubName + "'");

            }
        }else{
            System.out.println("\n Sorry, there is no club with the given name '" + clubName + "'");

        }


    }

    // THIS DEALS WITH CREATING THE FOOTBALL CLUB FOR THE LIST
    private static void creatingClub() {

        // MAKE SURE THAT EACH CLUB NAMES ARE UNIQUE FROM EACH OTHER
        Scanner insert = new Scanner(System.in);

        // Asking user the type of football club
        System.out.println(" Select the type of Football club: ");
        System.out.println(" ---------------------------------------- ");
        System.out.println("| (Option 1) Normal Football club        |");
        System.out.println("| (Option 2) University Football club    |");
        System.out.println("| (Option 3) School Football club        |");
        System.out.println(" ---------------------------------------- ");

        // getting the users input with validation to check if its an integer entered
        int userSelectOption;
        boolean notInRange = false;

        do{
            if(notInRange) System.out.println(" \n The entered option is not valid!\n " +
                    "Available options are (1, 2, 3)\n");
            System.out.print(" Enter your option number (integers only accepted): ");
            while(!insert.hasNextInt()){
                String input = insert.next();
                System.out.println("\n '" + input + "' is an Invalid input!");
                System.out.print(" Enter your option number (integers only accepted): ");
            }
            userSelectOption = insert.nextInt();
            notInRange = true;
        }while (userSelectOption < 1 || userSelectOption > 3);

        insert = new Scanner(System.in);


        System.out.println("\n NOTE: ALL THE CLUB NAMES HAS TO BE UNIQUE" +
                "\n PLEASE ENTER A CLUB NAME WHICH IS NOT FROM THE GIVEN LIST BELOW !");

        if(PremierLeagueManager.premierLeagueFootballClubList.size()!=0){
            System.out.println(" --------------------------------");
            for (FootballClub footballClub: PremierLeagueManager.premierLeagueFootballClubList) {
                System.out.println(" * " + footballClub.getName());
            }
            System.out.println(" --------------------------------");
        }else{
            System.out.println(" * There are no club names created yet and you are the first one !\n");
        }

        // When a new footballClub is created all the stats are set to 0
        // We ask for club name, location, coach name
        System.out.print(" Enter the club name: ");
        String clubName = insert.nextLine();
        clubName = clubName.substring(0, 1).toUpperCase() + clubName.toLowerCase().substring(1);

        // Validation for club name
        boolean invalidClubName = true;
        while (invalidClubName){
            if(PremierLeagueManager.premierLeagueFootballClubList.size()!=0){
                for (FootballClub footballClub: PremierLeagueManager.premierLeagueFootballClubList) {
                    if(footballClub.getName().equalsIgnoreCase(clubName)){
                        invalidClubName = true;
                        break;
                    }else{
                        invalidClubName = false;
                    }
                }
            }else{
                invalidClubName = false;
            }

            if(invalidClubName){
                System.out.println(" There is already a team with the name '" + clubName + "', please enter another name\n");
                System.out.print(" Enter the club name: ");
                clubName = insert.nextLine();
                clubName = clubName.substring(0, 1).toUpperCase() + clubName.toLowerCase().substring(1);

            }
        }


        // location can have numbers also so no need validation even it can have symbols such as '/'
        System.out.print(" Enter the location: ");
        String location = insert.nextLine();


        // validating the coach Name
        String coachName = validateString(" Enter the coach name: ");
        coachName = coachName.substring(0, 1).toUpperCase() + clubName.toLowerCase().substring(1);


        // this switch case is to create the appropriate club with the user selected option
        String result;
        switch (userSelectOption){
            case 1:
                // creating an instance of the new footballClub and adding it into the premierClub list
                result = premierLeagueManager.createClub(clubName, location, coachName, null,
                "normal");
                break;

            case 2:
                // getting the university name
                String universityName = validateString(" Enter the university name: ");

                // creating an instance of the new universityFootballClub and adding it into the premierClub list
                result = premierLeagueManager.createClub(clubName, location,
                        coachName, universityName,"university");
                break;

            case 3:
                // getting the school name
                String schoolName = validateString(" Enter the school name: ");

                // creating an instance of the new schoolFootballClub and adding it into the premierClub list
                result = premierLeagueManager.createClub(clubName, location, coachName,schoolName,"school");
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + userSelectOption);

        }

        System.out.println(result);     // display the result

    }


    // validate strings that should only have alphabets and return the result
    private static String validateString(String message) {
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

    // validates the Integers
    public static int validatingIntegers(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        while (!input.hasNextInt()) {
            System.out.println("\n Invalid input!");
            System.out.print(message);
            input.next();
        }
        return input.nextInt();
    }
}