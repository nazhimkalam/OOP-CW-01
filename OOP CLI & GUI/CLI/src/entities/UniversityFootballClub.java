package entities;

/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */

// Inheritance with the FootballClub
public class UniversityFootballClub extends FootballClub {

    // These are the private variables for Encapsulation
    private String universityName;

    // Default constructor (when ever you create an object the default constructor is called for instantiation)
    public UniversityFootballClub() {

    }

    // Argument Constructor
    public UniversityFootballClub(String name, String location, String coachName, String universityName) {

        super(name, location, coachName);
        this.universityName = universityName;

    }

    // GETTERS AND SETTERS FOR THE CLASS
    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    // overriding the toString() method to display the details of the university
    @Override
    public String toString() {
        return  super.toString() + " * University Name = '" + universityName + "'";
    }

}
