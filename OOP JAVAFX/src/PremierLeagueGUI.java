import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
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

public class PremierLeagueGUI extends Application {

    private static final PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();;
    public ObservableList<String> options;
    public static ComboBox<String> comboBox = new ComboBox<>();
    public static boolean sortByGoalsClicked = false;
    public static boolean sortByWinsClicked = false;
    public static boolean sortByPointsClicked = false;

    @Override
    public void start(Stage primaryStage) {

        displayMenu(primaryStage);

    }

    public void displayMenu(Stage primaryStage) {
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
        int userSelectOption = validatingIntegers(" Enter your option (please enter only integers): ");
        String result;
        // directing the users option to appropriate methods
        switch (userSelectOption)
        {
            case 1:
                creatingClub();                 // THIS IS WERE I TAKE ALL THE USER INPUTS
                displayMenu(primaryStage);
                break;

            case 2:
                deleteCLub();                   // THIS IS WERE I TAKE ALL THE USER INPUTS
                displayMenu(primaryStage);
                break;

            case 3:
                displayStatistics();            // THIS IS WERE I TAKE ALL THE USER INPUTS
                displayMenu(primaryStage);
                break;

            case 4:
                String seasonPlayed = validatingSeason();        // DISPLAYING THE PREMIER LEAGUE TABLE
                premierLeagueManager.displayLeagueTable(seasonPlayed);
                displayMenu(primaryStage);
                break;

            case 5:
                addPlayedMatch();              // THIS IS WERE I TAKE ALL THE USER INPUTS
                displayMenu(primaryStage);
                break;

            case 6:
                result = premierLeagueManager.saveDataIntoFile();       // saving the data into a file
                System.out.println(result);

                displayMenu(primaryStage);
                break;

            case 7:
                result = premierLeagueManager.clearDataFile();          // clearing the file
                System.out.println(result);

                displayMenu(primaryStage);
                break;
            case 8:
                guiProgram(primaryStage);
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
                displayMenu(primaryStage);

        }
    }

    // this is the GUI main program code
    private void guiProgram(Stage primaryStage) {
        // call the load method from the main class
        PremierLeagueManager.loadingData();

        // Main pane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId("premierLeague-pane");

        // Heading Image
        //Creating an image
        Image image = null;
        Image playerImage = null;
        try {
            image = new Image(new FileInputStream("src/plh.png"));
            playerImage = new Image(new FileInputStream("src/ronaldo.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Setting the image view
        ImageView imageView = new ImageView(image);
        ImageView imageViewPlayer = new ImageView(playerImage);

        //Setting the position of the image
        imageView.setLayoutX(180);
        imageView.setLayoutY(-35);

        //setting the fit height and width of the image view
        imageView.setFitHeight(600);
        imageView.setFitWidth(650);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        // Adding player image
        //Setting the position of the image
        imageViewPlayer.setLayoutX(950);
        imageViewPlayer.setLayoutY(30);

        //setting the fit height and width of the image view
        imageViewPlayer.setFitHeight(600);
        imageViewPlayer.setFitWidth(600);

        //Setting the preserve ratio of the image view
        imageViewPlayer.setPreserveRatio(true);


        // Dropdown menu
        // Season Text
        Label seasonLabel = new Label("SEASON");
        seasonLabel.setId("seasonLabel");
        seasonLabel.setLayoutX(100);
        seasonLabel.setLayoutY(105);

        // Drop down
        options = FXCollections.observableArrayList();
        ArrayList<String> orderSeasons = PremierLeagueManager.sortingTheSeasonsInAscendingOrder();
        // update the dropdown
        options.addAll(orderSeasons);

        comboBox = new ComboBox<>(options);
        comboBox.setValue("2020-21");
        comboBox.setId("comboBox");
        comboBox.setStyle("");
        comboBox.setLayoutX(85);
        comboBox.setLayoutY(130);

        PremierLeagueManager.displayLeagueTableGUI();
        final ArrayList<FootballClub>[] seasonBasedClubs = new ArrayList[1];
        seasonBasedClubs[0] = PremierLeagueManager.seasonFilteredClubs;
        sortByPointsClicked = true;


        // sort by goals btn
        Button sortByGoalsBTN = new Button("SORT BY GOALS");
        sortByGoalsBTN.setId("sortByGoals");
        sortByGoalsBTN.setLayoutX(310);
        sortByGoalsBTN.setLayoutY(130);

        // sort by wins btn
        Button sortByWinsBTN = new Button("SORT BY WINS");
        sortByWinsBTN.setId("sortByWins");
        sortByWinsBTN.setLayoutX(590);
        sortByWinsBTN.setLayoutY(130);

        // view played matches btn
        Button playedMatchesBTN = new Button("PLAYED MATCHES");
        playedMatchesBTN.setId("playedMatched");
        playedMatchesBTN.setLayoutX(800);
        playedMatchesBTN.setLayoutY(130);


        // Displaying the Premier League Table--------------------------

        //Creating a table view
        TableView<FootballClub> table = new TableView<>();
        table.setId("my-table");


        // loading the default season which is 2020-21 records
        final ObservableList<FootballClub> data = FXCollections.observableArrayList();

        updatingTableContent(data, seasonBasedClubs[0]);

        //--------------------------------------------------

        // FUNCTIONALITY FOR THE PREMIER LEAGUE TABLE BUTTONS
        comboBox.setOnAction(event -> {
            // get the clicked season and change the contents on the premier table
            updatingTableContent(data, seasonBasedClubs[0]);
        });

        sortByGoalsBTN.setOnAction(event -> {
            sortByGoalsClicked = true;
            sortByWinsClicked = false;
            updatingTableContent(data, seasonBasedClubs[0]);
        });

        sortByWinsBTN.setOnAction(event -> {
            sortByWinsClicked = true;
            sortByGoalsClicked = false;
            updatingTableContent(data, seasonBasedClubs[0]);
        });

        //Creating columns
        TableColumn<FootballClub, String> positionCOL = new TableColumn<>("POS");
        positionCOL.setCellValueFactory(new PropertyValueFactory<>("position"));

        TableColumn<FootballClub, String> clubNameCOL = new TableColumn<>("CLUB NAME");
        clubNameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<FootballClub, String> matchedPlayedCOL = new TableColumn<>("NO. MATCHES");
        matchedPlayedCOL.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getClubStatistics().getTotalMatchesPlayed())));

        TableColumn<FootballClub, String> winsCOL = new TableColumn<>("WINS");
        winsCOL.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getClubStatistics().getTotalWins())));

        TableColumn<FootballClub, String> drawsCOL = new TableColumn<>("DRAWS");
        drawsCOL.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getClubStatistics().getTotalDraws())));

        TableColumn<FootballClub, String> lossesCOL = new TableColumn<>("LOSSES");
        lossesCOL.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getClubStatistics().getTotalDefeats())));

        TableColumn<FootballClub, String> goalScoredCOL = new TableColumn<>("GOAL SCORED");
        goalScoredCOL.setCellValueFactory(new PropertyValueFactory<>("totalGoalsScored"));

        TableColumn<FootballClub, String> goalReceivedCOL = new TableColumn<>("GOAL RECEIVED");
        goalReceivedCOL.setCellValueFactory(new PropertyValueFactory<>("totalGoalsReceived"));

        TableColumn<FootballClub, String> goalDifferenceCOL = new TableColumn<>("GOAL DIFFER");
        goalDifferenceCOL.setCellValueFactory(new PropertyValueFactory<>("totalGoalsDifference"));

        TableColumn<FootballClub, String> pointsCOL = new TableColumn<>("POINTS");
        pointsCOL.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getClubStatistics().getTotalPointsScored()))
        );

        TableColumn[] tableColumns = {positionCOL, clubNameCOL, matchedPlayedCOL, winsCOL, drawsCOL, lossesCOL, goalScoredCOL,
                goalReceivedCOL, goalDifferenceCOL, pointsCOL};

        for (TableColumn tableColumn : tableColumns) {
            tableColumn.setEditable(false);
            tableColumn.setResizable(false);
            tableColumn.impl_setReorderable(false);
            tableColumn.setSortable(false);
        }

        pointsCOL.setPrefWidth(70);
        winsCOL.setPrefWidth(60);
        drawsCOL.setPrefWidth(60);
        lossesCOL.setPrefWidth(60);
        positionCOL.setPrefWidth(50);
        clubNameCOL.setPrefWidth(150);
        matchedPlayedCOL.setPrefWidth(100);
        goalScoredCOL.setPrefWidth(120);
        goalReceivedCOL.setPrefWidth(120);
        goalDifferenceCOL.setPrefWidth(120);

        //Adding data to the table
        table.setItems(data);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(positionCOL, clubNameCOL, matchedPlayedCOL, winsCOL, drawsCOL, lossesCOL, goalScoredCOL,
                goalReceivedCOL, goalDifferenceCOL, pointsCOL);

        //Setting the size of the table
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 50, 50, 60));
        vbox.getChildren().addAll(table);
        table.setPrefWidth(925);
        table.setLayoutX(40);
        table.setLayoutY(205);
        // -----------------------------------


        playedMatchesBTN.setOnAction(event -> {
            // display the played matches table
            primaryStage.close();
            try {                                                       // displaying the stage delay
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            AnchorPane anchorPaneMatches = new AnchorPane();
            anchorPaneMatches.setId("matchesPlayedPane");

            // Heading label
            Label mainTitleMatches = new Label("PLAYED  MATCHES");
            mainTitleMatches.setId("mainTitleMatches");
            mainTitleMatches.setLayoutX(360);
            mainTitleMatches.setLayoutY(20);

            // Search textField
            TextField enteredDatedTxt = new TextField();
            enteredDatedTxt.setPromptText("day / month / year");
            enteredDatedTxt.setId("enteredDatedTxt");
            enteredDatedTxt.setLayoutX(85);
            enteredDatedTxt.setLayoutY(91);

            // Search btn
            Button searchByDateBTN = new Button("SEARCH");
            searchByDateBTN.setId("searchByDateBTN");
            searchByDateBTN.setLayoutX(240);
            searchByDateBTN.setLayoutY(90);

            // Go back btn
            Button backBTN = new Button("BACK");
            backBTN.setId("backBTN");
            backBTN.setLayoutX(730);
            backBTN.setLayoutY(90);


            // generate match btn
            Button generateMatchBTN = new Button("GENERATE MATCH");
            generateMatchBTN.setId("generateMatchBTN");
            generateMatchBTN.setLayoutX(800);
            generateMatchBTN.setLayoutY(90);

            // back btn functionality completed
            backBTN.setOnAction(backEvent -> {
                sortByWinsClicked = false;
                sortByGoalsClicked = false;

                // go to the previous scene
                primaryStage.close();
                try {                                                       // displaying the stage delay
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                guiProgram(primaryStage);
            });

            // Displaying the list view of the matches and its details
            ComboBox<String> matchPlayedComboBox = comboBox;
            matchPlayedComboBox.setValue("2020-21");
            matchPlayedComboBox.setLayoutX(450);
            matchPlayedComboBox.setLayoutY(95);

            PremierLeagueManager.displayLeagueTableGUI();  // we run this to refresh/ update the seasonBasedClubs list
            ObservableList<HBox> matches = FXCollections.observableArrayList();

            // generating random match for the club
            generateMatchBTN.setOnAction(generateEvent -> {
                premierLeagueManager.generateRandomMatch(matchPlayedComboBox.getValue());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PremierLeagueManager.displayLeagueTableGUI();
                seasonBasedClubs[0] = PremierLeagueManager.seasonFilteredClubs;
                creatingTheMatchesRows(seasonBasedClubs[0], matches);


            });

            // searching matches by date
            searchByDateBTN.setOnAction(searchEvent -> {
                // filter the matches and display by date
                ArrayList<FootballClub> footballClubsWithFilteredMatchByDate =
                        seasonBasedClubs[0];  // seasonBasedClubs[0] to prevent the null safety problem
                try {
                    footballClubsWithFilteredMatchByDate = PremierLeagueManager.filterMatchesByDate(seasonBasedClubs[0],
                            enteredDatedTxt.getText());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    System.out.println("Error when cloning the football club");
                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                creatingTheMatchesRows(footballClubsWithFilteredMatchByDate, matches);

            });

            // before coming to this lets display the matches played for 2020-21
            matchPlayedComboBox.setOnAction(eventCombo -> {
                PremierLeagueManager.displayLeagueTableGUI();
                seasonBasedClubs[0] = PremierLeagueManager.seasonFilteredClubs;
                creatingTheMatchesRows(seasonBasedClubs[0], matches);

            });

            creatingTheMatchesRows(seasonBasedClubs[0], matches);



            ListView<HBox> listView = new ListView<>(matches);
            listView.setId("listViewMatches");
            listView.setPrefSize(700, 450);
            listView.setLayoutX(150);
            listView.setLayoutY(165);

            // --- --- -- -- - ---------- ---------------- -------- ----



            anchorPaneMatches.getStylesheets().clear();
            anchorPaneMatches.getStylesheets().add(PremierLeagueGUI.class.getResource("styles.css").toExternalForm());
            anchorPaneMatches.getChildren().addAll(mainTitleMatches, searchByDateBTN, enteredDatedTxt, backBTN,
                    generateMatchBTN, listView, matchPlayedComboBox);
            primaryStage.setTitle("Played Matches");
            primaryStage.setScene(new Scene(anchorPaneMatches, 1000, 640));         //creating and setting scene
            primaryStage.show();
            primaryStage.setOnCloseRequest(event2 -> {      //on close request
                event2.consume();
                closeProgram(primaryStage);
            });
        });


        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(PremierLeagueGUI.class.getResource("styles.css").toExternalForm());
        anchorPane.getChildren().addAll(imageView,seasonLabel,comboBox,sortByGoalsBTN, sortByWinsBTN, playedMatchesBTN
                , vbox, table,imageViewPlayer );
        primaryStage.setTitle("Premier League Table");
        primaryStage.setScene(new Scene(anchorPane, 1200, 620));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event2 -> {      //on close request
            event2.consume();
            closeProgram(primaryStage);
        });

    }

    // when user close the program
    private void closeProgram(Stage primaryStage) {
        Alert confirmationBox = new Alert(Alert.AlertType.CONFIRMATION);         //creating the confirmation box
        confirmationBox.setTitle("Close program");                               //setting title for the confirmation box
        confirmationBox.setHeaderText("Are you sure you want to close ?");       //setting header for the confirmation box
        ButtonType yes = new ButtonType("Yes");                                  //creating the button YES
        ButtonType no = new ButtonType("No");                                    //creating the button NO
        confirmationBox.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = confirmationBox.showAndWait();             //displays the confirmation box
        if (result.get() == yes){                                                //checks if the user has clicked YES
            primaryStage.close();                                                       //closes the window
            displayMenu(primaryStage);                      //calls the displayMenu method
        }
    }

    // updating the table content
    private void updatingTableContent(ObservableList<FootballClub> data, ArrayList<FootballClub> seasonBasedClub) {
        data.clear();
        PremierLeagueManager.displayLeagueTableGUI();
        seasonBasedClub = PremierLeagueManager.seasonFilteredClubs;
        int NewPosition = 1;
        for (FootballClub club : seasonBasedClub) {
            ClubStats clubStats = new ClubStats(club.getClubStatistics().getTotalMatchesPlayed(),
                    club.getClubStatistics().getTotalWins(),club.getClubStatistics().getTotalDraws(),
                    club.getClubStatistics().getTotalDefeats(), club.getClubStatistics().getTotalPointsScored());

            data.add(new FootballClub(NewPosition, club.getName(), clubStats,
                    club.getTotalGoalsScored(), club.getTotalGoalsReceived(),
                    club.getTotalGoalsDifference()));

            NewPosition++;
        }
    }

    // creating the rows
    private void creatingTheMatchesRows(ArrayList<FootballClub> seasonBasedClub, ObservableList<HBox> matches) {
        matches.clear();

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

        // MAIN CODE FOR DISPLAYING THE MATCHES
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

            if (matchNotAvailable) {
                matchesDisplayed.add(match);

                //Instantiating the HBox class
                HBox hbox = new HBox();
                hbox.setId("hboxMatches");

                VBox vboxClubOne = new VBox();
                vboxClubOne.setId("vboxClubOne");

                VBox versus = new VBox();
                versus.setId("versus");

                VBox vboxClubTwo = new VBox();
                vboxClubTwo.setId("vboxClubTwo");

                // Adding content for the VBox (Main club)
                Label clubNameOneLBL = new Label(match.getParticipatedCLubName().toUpperCase());
                clubNameOneLBL.setId("clubNameOneLBL");

                // clubTwo is the opponent club
                Label clubNameTwoLBL = new Label(match.getOpponentClubName().toUpperCase());
                clubNameTwoLBL.setId("clubNameTwoLBL");

                Label dateOneLBL = new Label(match.getDate().getDay() + "/" +
                        match.getDate().getMonth() + "/" + match.getDate().getYear());

                Label matchTypeOneLBL = new Label(match.getMatchType().toUpperCase());

                Label dateTwoLBL = new Label(match.getDate().getDay() + "/" +
                        match.getDate().getMonth() + "/" + match.getDate().getYear());

                Label matchTypeTwoLBL = new Label(match.getMatchType().toUpperCase());

                // main club score
                Label goalScoredByClubOneLBL = new Label(match.getGoalScored() + "");
                goalScoredByClubOneLBL.setId("goalScoredByClubOneLBL");

                // opponent club score
                Label goalScoredByClubTwoLBL = new Label(match.getGoalReceived() + "");
                goalScoredByClubTwoLBL.setId("goalScoredByClubTwoLBL");

                Label versusLBL = new Label("VS");
                versus.setId("versusLBL");

                // setting the widths
                vboxClubOne.setPrefWidth(150);
                vboxClubTwo.setPrefWidth(150);
                versus.setPrefWidth(150);

                // adding content for vboxClubOne
                vboxClubOne.setSpacing(5);
                vboxClubOne.getChildren().addAll(clubNameOneLBL, goalScoredByClubOneLBL, matchTypeOneLBL, dateOneLBL);

                // adding content for versus
                versus.setSpacing(5);
                versus.getChildren().addAll(versusLBL);

                // adding content for vboxClubTwo
                vboxClubTwo.setSpacing(5);
                vboxClubTwo.getChildren().addAll(clubNameTwoLBL, goalScoredByClubTwoLBL, matchTypeTwoLBL, dateTwoLBL);

                // ending adding content for the VBox

                //Setting the space between the nodes of a HBox pane
                hbox.setSpacing(10);

                //Setting the margin to the nodes
                HBox.setMargin(vboxClubOne, new Insets(5));
                HBox.setMargin(versus, new Insets(5));
                HBox.setMargin(vboxClubTwo, new Insets(5));

                //retrieving the observable list of the HBox
                hbox.getChildren().addAll(vboxClubOne, versus, vboxClubTwo);

                matches.add(hbox);

            }

        }
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
            }
        }


        // location can have numbers also so no need validation even it can have symbols such as '/'
        System.out.print(" Enter the location: ");
        String location = insert.nextLine();


        // validating the coach Name
        String coachName = validateString(" Enter the coach name: ");


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

    // validating the season
    private String validatingSeason() {
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

    private static void addPlayedMatch() {
         /* ADD A PLAYED MATCH WITH IT'S SCORE AND UPDATE THE STATISTICS AND LIST OF MATCHES FOR THE RESPECTIVE CLUBS
           PLAYED */

        Scanner input = new Scanner(System.in);
        System.out.println("\n Enter details of the played match");

        // Checking if it is a valid club else throwing up and error and asking user to re-enter
        String clubName_01 = checkingForValidClub(" Enter club 1 name: ");

        // validation for the scores
        int numberGoalScored_club_1 = validatingIntegers(" Enter the number of goal scored: ");

        // Checking if it is a valid club else throwing up and error and asking user to re-enter
        String clubName_02 = checkingForValidClub(" Enter club 2 name: ");

        // Checking if the user has entered duplicated club names
        while(clubName_01.equalsIgnoreCase(clubName_02)){
            System.out.println("\n Please enter a different club name ");
            clubName_02 = checkingForValidClub(" Enter club 2 name: ");
        }

        // validation for the scores and some stats
        int numberGoalScored_club_2 = validatingIntegers(" Enter the number of goal scored: ");


        // getting the date of the match played as the input from the user
        int day = validatingIntegers(" Enter the day (only integers are accepted): ");
        int month = validatingIntegers(" Enter the month (only integers are accepted): ");
        int year = validatingIntegers(" Enter the year (only integers are accepted): ");
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



    public static void main(String[] args) {
        launch(args);
    }
}
