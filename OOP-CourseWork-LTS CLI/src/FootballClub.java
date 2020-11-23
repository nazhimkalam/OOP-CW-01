import java.util.ArrayList;
import java.util.Random;

/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */

// Using the abstract class SportClub
public class FootballClub extends SportClub{

    // These are the private variables for Encapsulation
    private String coachName;
    private int totalGoalsReceived;
    private int totalGoalsScored;
    private int totalGoalsDifference;
    private int totalYellowCards;
    private int totalRedCards;
    private ArrayList<Match> matchesPlayed;
    private ArrayList<Player> playersList;

    // Default constructor (when ever you create an object the default constructor is called for instantiation)
    public FootballClub() {

    }

    // Argument Constructor
    public FootballClub(String name, String location, String coachName) {
        super(name, location, new ClubStats());
        this.coachName = coachName;
        this.totalGoalsReceived = 0;
        this.totalGoalsScored = 0;
        this.totalGoalsDifference = 0;
        this.totalYellowCards = 0;
        this.totalRedCards = 0;
        this.matchesPlayed = new ArrayList<>();
        this.playersList = new ArrayList<>();

        autoGeneratePlayers();      // auto generating the players whenever you instantiate a club
    }

    @Override
    public String toString() {
        return  super.toString() +
                "\n * Coach Name = '" + coachName + "'" +
                "\n * Total Goals Received = " + totalGoalsReceived +
                "\n * Total Goals Scored = " + totalGoalsScored +
                "\n * Total Goal Difference = " + totalGoalsDifference +
                "\n * Total Yellow Cards = " + totalYellowCards +
                "\n * Total Red Cards = " + totalRedCards + "\n\n";
    }


    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getTotalGoalsReceived() {
        return totalGoalsReceived;
    }

    public void setTotalGoalsReceived(int totalGoalsReceived) {
        this.totalGoalsReceived = totalGoalsReceived;
    }

    public int getTotalGoalsScored() {
        return totalGoalsScored;
    }

    public void setTotalGoalsScored(int totalGoalsScored) {
        this.totalGoalsScored = totalGoalsScored;
    }

    public int getTotalGoalsDifference() {
        return totalGoalsDifference;
    }

    public void setTotalGoalsDifference(int totalGoalsDifference) {
        this.totalGoalsDifference = totalGoalsDifference;
    }

    public int getTotalYellowCards() {
        return totalYellowCards;
    }

    public void setTotalYellowCards(int totalYellowCards) {
        this.totalYellowCards = totalYellowCards;
    }

    public int getTotalRedCards() {
        return totalRedCards;
    }

    public void setTotalRedCards(int totalRedCards) {
        this.totalRedCards = totalRedCards;
    }

    public ArrayList<Match> getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(ArrayList<Match> matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public ArrayList<Integer> getMainStatistics(){

        //  [matches played, wins, draws, defeats, goals scored, goals received, points, goal difference]
        //          0         1      2      3           4              5           6           7

        ArrayList<Integer> overallStatistics = new ArrayList<>();
        overallStatistics.add(getClubStatistics().getTotalMatchesPlayed());
        overallStatistics.add(getClubStatistics().getTotalWins());
        overallStatistics.add(getClubStatistics().getTotalDraws());
        overallStatistics.add(getClubStatistics().getTotalDefeats());
        overallStatistics.add(totalGoalsScored);
        overallStatistics.add(totalGoalsReceived);
        overallStatistics.add(getClubStatistics().getTotalPointsScored());
        overallStatistics.add(totalGoalsDifference);

        return overallStatistics;
    }

    // cloning the matches
    @Override
    protected Object clone() throws CloneNotSupportedException {
        FootballClub cloned = (FootballClub) super.clone();
        cloned.setMatchesPlayed(FootballClub.cloneMatchList(this.matchesPlayed));
        cloned.setClubStatistics(FootballClub.cloneClubStatistics(this.clubStatistics));
        return cloned;
    }

    public static ArrayList<Match> cloneMatchList(ArrayList<Match> list) {
        ArrayList<Match>  cloneMatches = new ArrayList<>(list.size());
        for (Match match: list) {
            try {
                cloneMatches.add((Match) match.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return cloneMatches;
    }
    public static ClubStats cloneClubStatistics(ClubStats clubStatistics) {
        ClubStats cloneClubStats = new ClubStats();

        try {
            cloneClubStats = (ClubStats) clubStatistics.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return cloneClubStats;
    }


    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    public void autoGeneratePlayers(){

        String[] playerNames = {
                "Lionel Messi",
                "Diego Maradona",
                "Pele",
                "Cristiano Ronaldo",
                "Johan Cruyff",
                "Alfredo Di Stefano",
                "Franz Beckenbauer",
                "Zinedine Zidane",
                "Ferenc Puskas",
                "Mane Garrincha",
                "Ronaldo Nazario"
        };

        String[] foot = {"Left", "Right"};

        // adding 11 players to the list
        for (int i = 0; i < 11; i++) {
            Random random = new Random();

            Player player = new Player(playerNames[i],
                    foot[random.nextInt(2)],
                    Math.round(random.nextDouble()*1000)/10.0,
                    random.nextInt(10)+1,
                    random.nextInt(50)+1);

            playersList.add(player);
        }

    }
}
