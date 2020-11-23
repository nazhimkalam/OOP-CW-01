
/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */

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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return  super.toString() + " * School Name = '" + schoolName + "' ";
    }

}
