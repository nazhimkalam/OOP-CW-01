import java.io.Serializable;
import java.util.Objects;

/*
 * @author Nazhim Kalam
 * w1761265
 * Java version 8 or higher required
 * SE2019281
 */

public class MatchStats implements Serializable
{
    // variables
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
        yellowCards = 0;
        redCards = 0;
        shots = 0;
        shotsOfTarget = 0;
        offSides = 0;
        fouls = 0;
        corners = 0;
        passes = 0;
        passAccuracy = 0.0d;
        possession = 0.0d;
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

    @Override
    public String toString() {
        return  "yellowCards=" + yellowCards + ", redCards=" + redCards + ", shots=" + shots +
                ", shotsOfTarget=" + shotsOfTarget + ", offSides=" + offSides + ", fouls=" + fouls +
                ", corners=" + corners + ", passes=" + passes + ", passAccuracy=" + passAccuracy +
                ", possession=" + possession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchStats that = (MatchStats) o;
        return yellowCards == that.yellowCards &&
                redCards == that.redCards &&
                shots == that.shots &&
                shotsOfTarget == that.shotsOfTarget &&
                offSides == that.offSides &&
                fouls == that.fouls &&
                corners == that.corners &&
                passes == that.passes &&
                Double.compare(that.passAccuracy, passAccuracy) == 0 &&
                Double.compare(that.possession, possession) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yellowCards, redCards, shots, shotsOfTarget, offSides, fouls, corners, passes,
                passAccuracy, possession);
    }

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
