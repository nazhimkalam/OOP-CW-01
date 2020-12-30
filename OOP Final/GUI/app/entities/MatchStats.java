package entities;
import java.io.Serializable;

/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */


public class MatchStats implements Serializable
{
    // These are the variables
    private int yellowCards;
    private int redCards;
    private int shots;
    private int shotsOfTarget;
    private int offSides;
    private int fouls;
    private int corners;
    private int passes;
    private double passAccuracy;
    private double possession;

    // Default constructor
    public MatchStats() {

    }

    // Args constructor
    public MatchStats(int yellowCards, int redCards, int shots, int shotsOfTarget, int offSides, int fouls,
                      int corners, int passes, double passAccuracy, double possession) {

        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.shots = shots;
        this.shotsOfTarget = shotsOfTarget;
        this.offSides = offSides;
        this.fouls = fouls;
        this.corners = corners;
        this.passes = passes;
        this.passAccuracy = passAccuracy;
        this.possession = possession;

    }

    // overriding the toString() to display the details of the statistics of the match
    @Override
    public String toString() {

        return
                "\n Number of yellow cards = " + yellowCards +
                        "\n Number of red cards = " + redCards +
                        "\n Number of shots = " + shots +
                        "\n Number of target shots = " + shotsOfTarget +
                        "\n Number of offsides = " + offSides +
                        "\n Number of fouls = " + fouls +
                        "\n Number of corner kicks = " + corners +
                        "\n Number of passes = " + passes +
                        "\n Pass Accuracy = " + passAccuracy + "%" +
                        "\n Possession = " + possession + "%";

    }

    // SETTERS AND GETTERS
    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int getShotsOfTarget() {
        return shotsOfTarget;
    }

    public void setShotsOfTarget(int shotsOfTarget) {
        this.shotsOfTarget = shotsOfTarget;
    }

    public int getOffSides() {
        return offSides;
    }

    public void setOffSides(int offSides) {
        this.offSides = offSides;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public int getCorners() {
        return corners;
    }

    public void setCorners(int corners) {
        this.corners = corners;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

    public double getPassAccuracy() {
        return passAccuracy;
    }

    public void setPassAccuracy(double passAccuracy) {
        this.passAccuracy = passAccuracy;
    }

    public double getPossession() {
        return possession;
    }

    public void setPossession(double possession) {
        this.possession = possession;
    }

}
