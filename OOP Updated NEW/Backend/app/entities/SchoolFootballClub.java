package entities;
/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */


// Inheritance with the FootballClub
public class SchoolFootballClub extends FootballClub {

    // These are the private variables for Encapsulation
    private String schoolName;

    // Default constructor (when ever you create an object the default constructor is called for instantiation)
    public SchoolFootballClub() {

    }

    // Argument Constructor
    public SchoolFootballClub(String name, String location,  String coachName, String schoolName) {
        super(name, location, coachName);
        this.schoolName = schoolName;
    }

    // GETTERS AND SETTERS FOR THE CLASS
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    // overriding the toString() method to display the details of the school
    @Override
    public String toString() {
        return  super.toString() + " * School Name = '" + schoolName + "' ";
    }

}
