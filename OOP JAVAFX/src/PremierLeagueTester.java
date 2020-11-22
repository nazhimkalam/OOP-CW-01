import javafx.application.Application;
import javafx.application.Platform;

import java.util.Scanner;

public class PremierLeagueTester {
    public static void main(String[] args) {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        displayMenu(premierLeagueManager);
    }

    public static void displayMenu(PremierLeagueManager premierLeagueManager) {
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
        "| (Option 8) Enter '8' to display the GUI                                                        |\n" +
        "| (Option 9) Enter '9' to exit the program                                                       |\n" +
        "|________________________________________________________________________________________________|\n");

        // getting the users input
        int userSelectOption = premierLeagueManager.validatingIntegers(" Enter your option (please enter only integers): ");

        // directing the users option to appropriate methods
        switch (userSelectOption)
        {
            case 1:
                premierLeagueManager.createClub();             // this method creates a new football club and add it in the premier league
                displayMenu(premierLeagueManager);
                break;
            case 2:
                premierLeagueManager.deleteCLub();             // this method deletes an existing club
                displayMenu(premierLeagueManager);
                break;
            case 3:
                premierLeagueManager.displayStats();           // displaying various statistics for a selected club
                displayMenu(premierLeagueManager);
                break;
            case 4:
                premierLeagueManager.displayLeagueTable();     // displaying the Premier League Table
                displayMenu(premierLeagueManager);
                break;
            case 5:
                premierLeagueManager.addPlayedMatch();         // adding a played match
                displayMenu(premierLeagueManager);
                break;
            case 6:
                premierLeagueManager.saveDataIntoFile();       // saving the data into a file
                displayMenu(premierLeagueManager);
                break;
            case 7:
                premierLeagueManager.clearDataFile();          // clearing the file
                displayMenu(premierLeagueManager);
                break;
            case 8:
                Application.launch(PremierLeagueGUI.class);
                break;
            case 9:
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
                displayMenu(premierLeagueManager);

        }
    }
}