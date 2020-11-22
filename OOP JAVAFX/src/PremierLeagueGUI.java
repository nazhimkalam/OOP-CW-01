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

public class PremierLeagueGUI extends Application {

    PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
    public ObservableList<String> options;
    public static ComboBox<String> comboBox = new ComboBox<>();
    public static boolean sortByGoalsClicked = false;
    public static boolean sortByWinsClicked = false;
    public static boolean sortByPointsClicked = false;

    @Override
    public void start(Stage primaryStage) {

        displayMenu(premierLeagueManager, primaryStage);

    }

    public void displayMenu(PremierLeagueManager premierLeagueManager, Stage primaryStage) {
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
                displayMenu(premierLeagueManager, primaryStage);
                break;
            case 2:
                premierLeagueManager.deleteCLub();             // this method deletes an existing club
                displayMenu(premierLeagueManager, primaryStage);
                break;
            case 3:
                premierLeagueManager.displayStats();           // displaying various statistics for a selected club
                displayMenu(premierLeagueManager, primaryStage);
                break;
            case 4:
                premierLeagueManager.displayLeagueTable();     // displaying the Premier League Table
                displayMenu(premierLeagueManager, primaryStage);
                break;
            case 5:
                premierLeagueManager.addPlayedMatch();         // adding a played match
                displayMenu(premierLeagueManager, primaryStage);
                break;
            case 6:
                premierLeagueManager.saveDataIntoFile();       // saving the data into a file
                displayMenu(premierLeagueManager, primaryStage);
                break;
            case 7:
                premierLeagueManager.clearDataFile();          // clearing the file
                displayMenu(premierLeagueManager, primaryStage);
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
                displayMenu(premierLeagueManager, primaryStage);

        }
    }

    // this is the GUI main program code
    private void guiProgram(Stage primaryStage) {
        // call the load method from the main class
        premierLeagueManager.loadingData();

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
        ArrayList<String> orderSeasons = premierLeagueManager.sortingTheSeasonsInAscendingOrder();
        // update the dropdown
        options.addAll(orderSeasons);

        comboBox = new ComboBox<>(options);
        comboBox.setValue("2020-21");
        comboBox.setId("comboBox");
        comboBox.setStyle("");
        comboBox.setLayoutX(85);
        comboBox.setLayoutY(130);

        //
        premierLeagueManager.displayLeagueTableGUI();
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

            premierLeagueManager.displayLeagueTableGUI();  // we run this to refresh/ update the seasonBasedClubs list
            ObservableList<HBox> matches = FXCollections.observableArrayList();

            // generating random match for the club
            generateMatchBTN.setOnAction(generateEvent -> {
                premierLeagueManager.generateRandomMatch(matchPlayedComboBox.getValue());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                premierLeagueManager.displayLeagueTableGUI();
                seasonBasedClubs[0] = PremierLeagueManager.seasonFilteredClubs;
                creatingTheMatchesRows(seasonBasedClubs[0], matches);


            });

            // searching matches by date
            searchByDateBTN.setOnAction(searchEvent -> {
                // filter the matches and display by date
                ArrayList<FootballClub> footballClubsWithFilteredMatchByDate =
                        seasonBasedClubs[0];  // seasonBasedClubs[0] to prevent the null safety problem
                try {
                    footballClubsWithFilteredMatchByDate = premierLeagueManager.filterMatchesByDate(seasonBasedClubs[0],
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
                premierLeagueManager.displayLeagueTableGUI();
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
            displayMenu(premierLeagueManager, primaryStage);                      //calls the displayMenu method
        }
    }

    // updating the table content
    private void updatingTableContent(ObservableList<FootballClub> data, ArrayList<FootballClub> seasonBasedClub) {
        data.clear();
        premierLeagueManager.displayLeagueTableGUI();
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


    public static void main(String[] args) {
        launch(args);
    }
}
