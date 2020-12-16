import java.io.Serializable;
import java.util.Objects;

/*
 * @author Nazhim Kalam
 * w1761265
 * Java version 8 or higher required
 * SE2019281
 */
public class SportClub implements Serializable{

    // variables used
    private String name;
    private String location;
    private ClubStats clubStatistics;
    private String coachName;


    // Default constructor (when ever you create an object the default constructor is called for instantiation)
    public SportClub(){
        name = "";
        location = "";
        clubStatistics = new ClubStats();
        coachName = "";
    }

    // Argument Constructor
    public SportClub(String name, String location, ClubStats clubStatistics, String coachName) {
        this.name = name;
        this.location = location;
        this.clubStatistics = clubStatistics;
        this.coachName = coachName;
    }

    // toString() method to get details of the object in a String format
    @Override
    public String toString() {
        return  "name='" + name + ", location='" + location + ", " +
                "clubStatistics=" + clubStatistics + ", coachName='" + coachName;
    }

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

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportClub sportClub = (SportClub) o;
        return name.equals(sportClub.name) &&
                location.equals(sportClub.location) &&
                clubStatistics.equals(sportClub.clubStatistics) &&
                coachName.equals(sportClub.coachName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, clubStatistics, coachName);
    }
}
