import java.io.Serializable;

/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */

public class Player implements Serializable
{
    private static final long serialVersionUID = 1011601660849490401L;

    // variables used for the Players
    private String name;
    private String preferredFoot;
    private double shootingAccuracy;
    private int goalScoredPerMatch;
    private int passesPerMatch;

    // The Default Constructor
    public Player() {

    }

    // Argument Constructor
    public Player(String name, String preferredFoot, double shootingAccuracy,
                  int goalScoredPerMatch, int passesPerMatch) {
        this.name = name;
        this.preferredFoot = preferredFoot;
        this.shootingAccuracy = shootingAccuracy;
        this.goalScoredPerMatch = goalScoredPerMatch;
        this.passesPerMatch = passesPerMatch;
    }

    // GETTERS and SETTERS used
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreferredFoot() {
        return preferredFoot;
    }

    public void setPreferredFoot(String preferredFoot) {
        this.preferredFoot = preferredFoot;
    }

    public double getShootingAccuracy() {
        return shootingAccuracy;
    }

    public void setShootingAccuracy(double shootingAccuracy) {
        this.shootingAccuracy = shootingAccuracy;
    }

    public int getGoalScoredPerMatch() {
        return goalScoredPerMatch;
    }

    public void setGoalScoredPerMatch(int goalScoredPerMatch) {
        this.goalScoredPerMatch = goalScoredPerMatch;
    }

    public int getPassesPerMatch() {
        return passesPerMatch;
    }

    public void setPassesPerMatch(int passesPerMatch) {
        this.passesPerMatch = passesPerMatch;
    }

    // overriding the toString() method to display the details of the players
    @Override
    public String toString() {
        return  " ==> * Name = '" + name + '\'' +
                "\n ==> * Preferred Foot = '" + preferredFoot + '\'' +
                "\n ==> * Shooting Accuracy = " + shootingAccuracy + " %" +
                "\n ==> * Rate Of Goals Scored per Match = " + goalScoredPerMatch +
                "\n ==> * Rate of Passes per Match = " + passesPerMatch + "\n";
    }
}
