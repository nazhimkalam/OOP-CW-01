package controllers;
import java.io.Serializable;

/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */


// public abstract class SportClub, abstract because you can't make an object from the SportsClub class
public abstract class SportClub implements Serializable, Cloneable{

    // Variables used
    private String name;
    private String location;
    protected ClubStats clubStatistics;

    // Default constructor (when ever you create an object the default constructor is called for instantiation)
    public SportClub(){

    }

    // Argument Constructor
    public SportClub(String name, String location, ClubStats clubStatistics) {
        this.name = name;
        this.location = location;
        this.clubStatistics = clubStatistics;
    }

    // GETTERS AND SETTERS FOR THE CLASS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ClubStats getClubStatistics() {
        return clubStatistics;
    }

    public void setClubStatistics(ClubStats clubStatistics) {
        this.clubStatistics = clubStatistics;
    }

    // overriding the toString() method to display the details of the club
    @Override
    public String toString() {
        return  " * Club Name = '" + name + "'\n * Club Location = '" + location + "'" + clubStatistics.toString();
    }

}
