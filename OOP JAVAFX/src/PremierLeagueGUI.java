import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PremierLeagueGUI extends Application {

    PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
    public ObservableList<String> options;
    public static ComboBox<String> comboBox = new ComboBox<>();


    @Override
    public void start(Stage primaryStage) {
        // call the load method from the main class
        premierLeagueManager.loadingData();
        //

        // Main pane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId("premierLeague-pane");

        // Heading label
        Label mainTitle = new Label("PREMIER LEAGUE TABLE");
        mainTitle.setId("mainTitle");
        mainTitle.setLayoutX(360);
        mainTitle.setLayoutY(30);

       // Dropdown menu
        // Season Text
        Label seasonLabel = new Label("SEASON");
        seasonLabel.setId("seasonLabel");
        seasonLabel.setLayoutX(100);
        seasonLabel.setLayoutY(75);

        // Drop down
        options = FXCollections.observableArrayList();
        ArrayList<String> orderSeasons = premierLeagueManager.sortingTheSeasonsInAscendingOrder();
        System.out.println(orderSeasons);
        // update the dropdown
        options.addAll(orderSeasons);

        comboBox = new ComboBox<>(options);
        comboBox.setValue("2020-21");
        comboBox.setId("comboBox");
        comboBox.setStyle("");
        comboBox.setLayoutX(85);
        comboBox.setLayoutY(100);

       // sort by goals btn
        Button sortByGoalsBTN = new Button("SORT BY GOALS");
        sortByGoalsBTN.setId("sortByGoals");
        sortByGoalsBTN.setLayoutX(310);
        sortByGoalsBTN.setLayoutY(100);

       // sort by wins btn
        Button sortByWinsBTN = new Button("SORT BY WINS");
        sortByWinsBTN.setId("sortByWins");
        sortByWinsBTN.setLayoutX(590);
        sortByWinsBTN.setLayoutY(100);

       // view played matches btn
        Button playedMatchesBTN = new Button("PLAYED MATCHES");
        playedMatchesBTN.setId("playedMatched");
        playedMatchesBTN.setLayoutX(800);
        playedMatchesBTN.setLayoutY(100);

        sortByGoalsBTN.setOnAction(event -> {
           // sort the table by goals

        });

        sortByWinsBTN.setOnAction(event -> {
            // sort the table by wins

        });

        // Displaying the Premier League Table--------------------------

        //Creating a table view
        TableView<FootballClub> table = new TableView<>();
        table.setId("my-table");



        // loading the default season which is 2020-21 records
        final ObservableList<FootballClub> data = FXCollections.observableArrayList();

        final ArrayList<FootballClub>[] seasonBasedClubs = new ArrayList[]{premierLeagueManager.displayLeagueTable()};
        int position = 1;
//        System.out.println(seasonBasedClubs[0]);
        for (FootballClub club : seasonBasedClubs[0]) {

            ClubStats clubStats = new ClubStats(club.getClubStatistics().getTotalMatchesPlayed(),
                    club.getClubStatistics().getTotalWins(),club.getClubStatistics().getTotalDraws(),
                    club.getClubStatistics().getTotalDefeats(), club.getClubStatistics().getTotalPointsScored());

            data.add(new FootballClub(position, club.getName(), clubStats,
                    club.getTotalGoalsScored(), club.getTotalGoalsReceived(),
                    club.getTotalGoalsDifference()));
            position++;
        }
        //--------------------------------------------------

        // FUNCTIONALITY FOR THE PREMIER LEAGUE TABLE BUTTONS
        comboBox.setOnAction(event -> {
            // get the clicked season and change the contents on the premier table
            data.clear();
            seasonBasedClubs[0] = premierLeagueManager.displayLeagueTable();
            int NewPosition = 1;
            System.out.println(seasonBasedClubs[0]);
            for (FootballClub club : seasonBasedClubs[0]) {
                ClubStats clubStats = new ClubStats(club.getClubStatistics().getTotalMatchesPlayed(),
                        club.getClubStatistics().getTotalWins(),club.getClubStatistics().getTotalDraws(),
                        club.getClubStatistics().getTotalDefeats(), club.getClubStatistics().getTotalPointsScored());

                data.add(new FootballClub(NewPosition, club.getName(), clubStats,
                        club.getTotalGoalsScored(), club.getTotalGoalsReceived(),
                        club.getTotalGoalsDifference()));

                NewPosition++;
            }

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

        TableColumn<FootballClub, String> goalDifferenceCOL = new TableColumn<>("GOAL DIFFERENCE");
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
        table.setLayoutY(175);
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
            mainTitleMatches.setLayoutX(390);
            mainTitleMatches.setLayoutY(20);

            // Search textField
            TextField enteredDatedTxt = new TextField();
            enteredDatedTxt.setPromptText("day / month / year");
            enteredDatedTxt.setId("enteredDatedTxt");
            enteredDatedTxt.setLayoutX(85);
            enteredDatedTxt.setLayoutY(81);

            // Search btn
            Button searchByDateBTN = new Button("SEARCH");
            searchByDateBTN.setId("searchByDateBTN");
            searchByDateBTN.setLayoutX(240);
            searchByDateBTN.setLayoutY(80);

            // Go back btn
            Button backBTN = new Button("BACK");
            backBTN.setId("backBTN");
            backBTN.setLayoutX(730);
            backBTN.setLayoutY(80);


            // generate match btn
            Button generateMatchBTN = new Button("GENERATE MATCH");
            generateMatchBTN.setId("generateMatchBTN");
            generateMatchBTN.setLayoutX(800);
            generateMatchBTN.setLayoutY(80);


            // back btn functionality completed
            backBTN.setOnAction(backEvent -> {
                // go to the previous scene
                primaryStage.close();
                try {                                                       // displaying the stage delay
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                start(primaryStage);
            });



            // Displaying the list view of the matches and its details

            //list View for educational qualification
            ObservableList<HBox> matches = FXCollections.observableArrayList();
            for (int i = 0; i < 10; i++) {
                //Instantiating the HBox class
                HBox hbox = new HBox();
                hbox.setId("hboxMatches");

                VBox vboxClubOne = new VBox();
                vboxClubOne.setId("vboxClubOne");

                VBox versus = new VBox();
                versus.setId("versus");

                VBox vboxClubTwo = new VBox();
                vboxClubTwo.setId("vboxClubTwo");

                // Adding content for the VBox
                Label clubNameOneLBL = new Label("Juventus");
                clubNameOneLBL.setId("clubNameOneLBL");

                Label clubNameTwoLBL = new Label("Barca");
                clubNameTwoLBL.setId("clubNameTwoLBL");

                Label dateOneLBL = new Label("14/02/2015");
                Label matchTypeOneLBL = new Label("HOME");

                Label dateTwoLBL = new Label("14/02/2015");
                Label matchTypeTwoLBL = new Label("HOME");

                Label goalScoredByClubOneLBL = new Label("12");
                goalScoredByClubOneLBL.setId("goalScoredByClubOneLBL");

                Label goalScoredByClubTwoLBL = new Label("7");
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
            ListView<HBox> listView = new ListView<>(matches);
            listView.setId("listViewMatches");
            listView.setPrefSize(700, 450);
            listView.setLayoutX(150);
            listView.setLayoutY(150);

            // --- --- -- -- - ---------- ---------------- -------- ----

            searchByDateBTN.setOnAction(searchEvent -> {
                // filter the matches and display by date
                System.out.println(enteredDatedTxt.getText());   // gets the date entered by the user


            });

            anchorPaneMatches.getStylesheets().clear();
            anchorPaneMatches.getStylesheets().add(PremierLeagueGUI.class.getResource("styles.css").toExternalForm());
            anchorPaneMatches.getChildren().addAll(mainTitleMatches, searchByDateBTN, enteredDatedTxt, backBTN, generateMatchBTN, listView);
            primaryStage.setTitle("Played Matches");
            primaryStage.setScene(new Scene(anchorPaneMatches, 1000, 620));         //creating and setting scene
            primaryStage.show();
        });


        anchorPane.getStylesheets().clear();
        anchorPane.getStylesheets().add(PremierLeagueGUI.class.getResource("styles.css").toExternalForm());
        anchorPane.getChildren().addAll(mainTitle,seasonLabel,comboBox,sortByGoalsBTN, sortByWinsBTN, playedMatchesBTN
                , vbox, table );
        primaryStage.setTitle("Premier League Table");
        primaryStage.setScene(new Scene(anchorPane, 1000, 620));
        primaryStage.setResizable(false);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
