package controllers;
import entities.DateMatch;
import entities.FootballClub;
import entities.LeagueManager;
import services.PremierLeagueManager;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
 *   --> ALL FOOTBALL CLUBS SHOULD HAVE UNIQUE NAMES
 *   --> MOSTLY PREFERRED THAT MATCHES ARE PLAYING FOR 2020-21 AT FIRST BUT THERE IS NO ISSUE WITH STARTING WITH OTHER
 *       SEASONS
 */

public class ConsoleApplication {
    // Variable used
    private static final LeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

    // MAIN METHOD
    public static void main(String[] args) {
        displayMenu();
    }

    // THIS IS MENU METHOD
    public static void displayMenu() {
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
        "| (Option 6) Enter '6' to display the GUI                                                        |\n" +
        "| (Option 7) Enter '7' to save all the information entered into a file                           |\n" +
        "| (Option 8) Enter '8' to clear the data in the file                                             |\n" +
        "| (Option 9) Enter '9' to exit the program                                                       |\n" +
        "|________________________________________________________________________________________________|\n");

        // get user selected option
        int userSelectOption = validatingIntegers(" Enter an option (please enter only integers): ");
        String result;

        // Fires the appropriate method depending on the user selected option
        switch (userSelectOption)
        {
            case 1:
                PremierLeagueManager.loadingData();

                // method to get user inputs for creating the club
                creatingClub();

                premierLeagueManager.saveDataIntoFile();
                displayMenu();
                break;

            case 2:
                PremierLeagueManager.loadingData();

                // method to get user inputs for deleting a club
                deleteCLub();

                premierLeagueManager.saveDataIntoFile();
                displayMenu();
                break;
                
            case 3:
                PremierLeagueManager.loadingData();

                // method to get user inputs for displaying the club details
                displayStatistics();

                premierLeagueManager.saveDataIntoFile();
                displayMenu();
                break;

            case 4:
                PremierLeagueManager.loadingData();

                // gets the season entered by the user which is validated
                String seasonPlayed = validatingSeason();

                // method to display the CLI premier League table
                premierLeagueManager.displayLeagueTable(seasonPlayed);

                premierLeagueManager.saveDataIntoFile();
                displayMenu();
                break;

            case 5:
                PremierLeagueManager.loadingData();

                // method to get user inputs to add match played
                addPlayedMatch();

                premierLeagueManager.saveDataIntoFile();
                displayMenu();
                break;

            case 6:
                // used to open the external browser with the URL "http://localhost:4200" to open the GUI
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI(("http://localhost:4200")));
                    System.out.println(" Opening the GUI at localhost: 4200\n");
                    displayMenu();

                } catch (IOException | URISyntaxException ioException) {
                    // Handling caught exception
                    System.out.println("Error when opening the browser! ");
                    ioException.printStackTrace();

                }
                break;

            case 7:
                // method to save the data
                PremierLeagueManager.loadingData();

                result = premierLeagueManager.saveDataIntoFile();
                System.out.println(result);

                displayMenu();
                break;

            case 8:
                // method to clear the data from the txt file
                result = premierLeagueManager.clearDataFile();
                System.out.println(result + "\n");
                PremierLeagueManager.loadingData();

                displayMenu();
                break;

            case 9:
                // exiting section
                Scanner input = new Scanner(System.in);
                System.out.println(" Sure that you want to exist? ");
                System.out.print(" Enter 'y' to confirm or enter any other key to display menu: ");
                String confirmation = input.nextLine();

                if(confirmation.equalsIgnoreCase("y")){
                    // this section saves the data again and then exits
                    // NOTE that the data is saved from the backend when generated the match and for CLI its always
                    // saved after any execution so this message is just for a user satisfaction
                    System.out.println(" Saving data . . .");
                    System.out.println(" Exiting program . . .");   // quitting the program
                    System.exit(200);
                }
                // else we continue to display the menu
                displayMenu();
                break;

            default:
                // Re looping when the user has entered an invalid option
                System.out.println(" You have entered an invalid option!");
                System.out.println(" Please check the menu properly and re-enter!");
                displayMenu();

        }
    }


    public static void addPlayedMatch() {
         /* ADD A PLAYED MATCH WITH IT'S SCORE AND UPDATE THE STATISTICS AND LIST OF MATCHES FOR THE RESPECTIVE CLUBS
           PLAYED */

        // we have to check if there is at least 2 clubs or more present to add a match else we can't add a match
        if(PremierLeagueManager.getPremierLeagueFootballClubList().size() > 1){
            // If there is more than 1 club then only we proceed
            Scanner input = new Scanner(System.in);
            System.out.println("\n Enter details of the played match");

            // "checkingForValidClub()" checks if it is a valid club else throwing up and error and asking user to re-enter
            String clubName_01 = checkingForValidClub(" Enter club 1 name: ");
            clubName_01 = clubName_01.substring(0, 1).toUpperCase() + clubName_01.toLowerCase().substring(1);

            // validating the scores to make sure its an integer entered
            int numberGoalScored_club_1 = validatingIntegers(" Enter the number of goal scored: ");

            // "checkingForValidClub()" checks if it is a valid club else throwing up and error and asking user to re-enter
            String clubName_02 = checkingForValidClub(" Enter club 2 name: ");
            clubName_02 = clubName_02.substring(0, 1).toUpperCase() + clubName_02.toLowerCase().substring(1);

            // Checking if the user has entered the same club name again for the next team name
            while(clubName_01.equalsIgnoreCase(clubName_02)){
                System.out.println("\n There should be two different clubs to play a match and you have entered the same " +
                        "club twice!");
                System.out.println(" Please enter a different club name! ");
                clubName_02 = checkingForValidClub(" Enter club 2 name: ");
                clubName_02 = clubName_02.substring(0, 1).toUpperCase() + clubName_02.toLowerCase().substring(1);

            }

            // validating the scores to make sure its an integer entered
            int numberGoalScored_club_2 = validatingIntegers(" Enter the number of goal scored: ");

            // getting the date of the match played as the input from the user and validating if its a integer or not
            int day = validatingIntegers(" Enter the day (only integers are accepted): ");

            // validating the day entered which has to be in between 1 and 31
            while(day<1 || day>31){
                System.out.println(" Invalid day entered, day entered should be with in the range of (1 to 31)! \n");
                day = validatingIntegers(" Enter the day (only integers are accepted): ");
            }

            //  getting the month of the match played as the input from the user and validating if its a integer or not
            int month = validatingIntegers(" Enter the month (only integers are accepted): ");

            // validating the month entered which has to be in between 1 and 12
            while(month<1 || month>12){
                System.out.println(" Invalid month entered, month entered should be with in the range of (1 to 12)! \n");
                month = validatingIntegers(" Enter the month (only integers are accepted): ");
            }

            //  getting the year of the match played as the input from the user and validating if its a integer or not
            int year = validatingIntegers(" Enter the year (only integers are accepted): ");

            // validating the year entered
            while(year<1000 || year>3000){
                // Assuming that the minimum year is 1000 and maximum year is 3000
                System.out.println(" Invalid year entered, year entered should be with in the range of (1000 to 3000)! \n");
                year = validatingIntegers(" Enter the year (only integers are accepted): ");
            }

            // creating the date object for the match played
            DateMatch date = new DateMatch(day, month, year);

            // we are displaying the season options possible for the match played for the given date
            String[] possibleSeason = new String[2];
            System.out.println(" These are the possible seasons for the match played from the given date");

            int lastTwoDigitsOfTheYear = Integer.parseInt(String.valueOf(year).substring(2));

            possibleSeason[0] = (year-1) + "-" + (lastTwoDigitsOfTheYear);
            possibleSeason[1] = (year) + "-" + (lastTwoDigitsOfTheYear+1);

            // Displaying the season options for the entered year of the match
            for (int index = 0; index < possibleSeason.length; index++) {
                System.out.println("  " + (index+1) + ". " + possibleSeason[index]);
            }

            // getting the season user input an validating it to check if an integer is entered
            int seasonOption = validatingIntegers(" Please select a season from the given list (Enter '1' or '2') : ");

            // This is to validate if the user has entered a correct season option, (only enter 1 or 2 else we ask user
            // to re-enter)
            boolean invalidOption = true;
            while (invalidOption){
                if(seasonOption!=1 && seasonOption!=2){
                    System.out.println("\n Invalid Input, please only enter either '1' or '2' as the season option!");
                    seasonOption = validatingIntegers(" Please select a season from the given list (Enter '1' or '2') : ");
                }else{
                    invalidOption=false;
                }
            }

            String seasonPlayed = possibleSeason[seasonOption-1];

            // validating an asking the user to enter the type of match played, ("Home" or "Away")
            boolean validMatchEntered;
            String matchType;

            do{
                System.out.print(" Enter the type of match played (Home or Away): ");
                matchType = input.nextLine();
                matchType = matchType.substring(0, 1).toUpperCase() + matchType.toLowerCase().substring(1);
                validMatchEntered = matchType.equalsIgnoreCase("home") || matchType.equalsIgnoreCase("away");
                if(!validMatchEntered)
                    System.out.println("\n Invalid match input, please only enter either 'HOME' or 'AWAY' as the match type!");

            }while (!validMatchEntered);


            System.out.print(" Are you sure that the details entered are correct, if you need to re-enter," +
                    " enter 'Y' or 'y'" + " else enter any key to continue: ");
            input = new Scanner(System.in);
            String confirmation = input.nextLine();

            // This is to confirm if the user has entered correct details else the user is able to re enter from beginning
            if (confirmation.equalsIgnoreCase("y")) {
                System.out.println(" Please re-enter the details ");
                addPlayedMatch();

            }else{
                String result = premierLeagueManager.addPlayedMatch(seasonPlayed, clubName_01, clubName_02,
                        numberGoalScored_club_1, numberGoalScored_club_2, date, matchType);
                System.out.println(result);

            }
        }else{
            // We display a message to the user
            System.out.println(" Sorry there is only 1 club present currently, so a match can't be played!");
        }
    }

    public static String checkingForValidClub(String message) {
        // CHECKING FOR VALID CLUB ENTERED BY THE USER WHEN ADDING MATCH

        // getting the user input
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        String clubName = input.nextLine();

        // validation to check if the entered club name is valid
        boolean invalidClubName = true;
        while (invalidClubName){
            for (FootballClub footballClub: PremierLeagueManager.getPremierLeagueFootballClubList()) {
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

    // VALIDATING THE SEASON ENTERED BY THE USER, IT HAS TO BE IN THE FORMAT 20XX-XX ONLY
    public static String validatingSeason() {
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
                System.out.println("\n Given input is not in proper format, use this format please (0000-00) with integers only! ");
        }while (!validatingSeason);

        return seasonPlayed;
    }

    // THIS DEALS WITH DISPLAYING THE STATISTICS OF THE FOOTBALL CLUB
    public static void displayStatistics() {

        // Gets the club name from the user to display the statistics
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
    public static void deleteCLub() {

        // DELETING A CLUB (BY ITS NAME) FROM THE LIST OF CLUBS IN THE PREMIER LEAGUE
        // Gets the club name from the user to delete the club
        Scanner input = new Scanner(System.in);
        System.out.print(" Enter the name of the club you wish to remove from the premier league: ");
        String clubName = input.nextLine();

        String confirmation = "";
        boolean isValidClubName = false;

        // DISPLAY RESULT OF THE ITEM TO BE REMOVED
        for (int index = 0; index < PremierLeagueManager.getPremierLeagueFootballClubList().size(); index++) {
            if(PremierLeagueManager.getPremierLeagueFootballClubList().get(index).getName().equalsIgnoreCase(clubName)){
                System.out.println("\n These are some details of the club you wanted to be deleted \n");
                System.out.println(PremierLeagueManager.getPremierLeagueFootballClubList().get(index));
                isValidClubName = true;

                // ASK FOR CONFIRMATION
                System.out.print(" Enter 'y' or 'Y' to confirm the deletion or enter any other key to skip the deletion: ");
                confirmation = input.nextLine();
            }
        }

        // if the club name entered by the user is valid only the next step for deletion is carried on
        if(isValidClubName){

            //  ask for the confirmation from the user if he needs to delete the club or not
            if(confirmation.equalsIgnoreCase("y")){

                // GETTING THE REMOVED CLUB RESULT (MAY BE NULL OR THE CLUB REMOVED),
                // The Null won't be returned but it's for double validation
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
    public static void creatingClub() {
        Scanner insert = new Scanner(System.in);

        // Asking user the type of football club
        System.out.println(" Select the type of Football club: ");
        System.out.println(" ---------------------------------------- ");
        System.out.println("| (Option 1) Normal Football club        |");
        System.out.println("| (Option 2) University Football club    |");
        System.out.println("| (Option 3) School Football club        |");
        System.out.println(" ---------------------------------------- ");

        int userSelectOption;
        boolean notInRange = false;

        // getting user input with validation places to check if correct option is entered and if its a number as well
        do{
            if(notInRange) System.out.println(" \n The entered option is not valid!\n " +
                    "Available options are (1, 2, 3)\n");
            System.out.print(" Enter your option number (integers only accepted): ");
            while(!insert.hasNextInt()){
                String input = insert.next();
                System.out.println("\n '" + input + "' is an Invalid Integer, please enter only Integers!");
                System.out.print(" Enter your option number (integers only accepted): ");
            }
            userSelectOption = insert.nextInt();
            notInRange = true;
        }while (userSelectOption < 1 || userSelectOption > 3);

        insert = new Scanner(System.in);

        System.out.println("\n NOTE: ALL THE CLUB NAMES HAS TO BE UNIQUE" +
                "\n PLEASE ENTER A CLUB NAME WHICH IS NOT FROM THE GIVEN LIST BELOW !");

        // Displaying the list of club names which are currently available so that the user can enter a club name
        // which is unique and not in the list
        if(PremierLeagueManager.getPremierLeagueFootballClubList().size()!=0){
            System.out.println(" --------------------------------");
            for (FootballClub footballClub: PremierLeagueManager.getPremierLeagueFootballClubList()) {
                System.out.println(" * " + footballClub.getName());
            }
            System.out.println(" --------------------------------");
        }else{
            System.out.println(" * There are no club names created yet and you are the first one !\n");
        }

        // When a new footballClub is created all the stats are set to 0
        // We ask for club name, location, coach name from the user as the inputs
        String clubName = validateString(" Enter the club name: ");
        clubName = clubName.substring(0, 1).toUpperCase() + clubName.toLowerCase().substring(1);

        // Validation for club name, if there is a club name already present then we ask the user to enter another
        // unique club name
        boolean invalidClubName = true;
        while (invalidClubName){
            if(PremierLeagueManager.getPremierLeagueFootballClubList().size()!=0){
                for (FootballClub footballClub: PremierLeagueManager.getPremierLeagueFootballClubList()) {
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
                clubName = validateString(" Enter the club name: ");
                clubName = clubName.substring(0, 1).toUpperCase() + clubName.toLowerCase().substring(1);

            }
        }

        // location can have numbers also so no need validation even it can have symbols such as '/'
        System.out.print(" Enter the location: ");
        String location = insert.nextLine();

        // validating the coach Name
        String coachName = validateString(" Enter the coach name: ");
        coachName = coachName.substring(0, 1).toUpperCase() + coachName.toLowerCase().substring(1);

        // this switch case is to create the appropriate club with the user selected option
        // club may be a normal premier league football club, school or university football club
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
    public static String validateString(String message) {
        Scanner input = new Scanner(System.in);
        boolean validStringEntered;
        String userInput;

        do{
            validStringEntered = false;
            System.out.print(message);
            userInput = input.nextLine();
            if((userInput != null) && userInput.matches("^[a-z A-Z]*$") && (!userInput.equals("")))
                validStringEntered = true;
            else
                System.out.println("\n Given input is not in proper format, only include alphabets please! ");
        }while (!validStringEntered);

        return userInput;
    }

    // validates the Integers
    public static int validatingIntegers(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        while (!input.hasNextInt()) {
            System.out.println("\n Invalid input, please enter a valid integer!");
            System.out.print(message);
            input.next();
        }
        return input.nextInt();
    }
}