import java.io.Serializable;
import java.util.Objects;

/*
 * @author Nazhim Kalam
 * w1761265
 * Java version 8 or higher required
 * SE2019281
 */
public class DateMatch implements Serializable
{
    // variables used
    private int day;
    private int month;
    private int year;

    // Default constructor (when ever you create an object the default constructor is called for instantiation)
    public DateMatch(){
        day = 0;
        month = 0;
        year = 0;
    }

    // Argument Constructor
    public DateMatch(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // getter for day
    public int getDay() {
        return day;
    }

    // setter for day
    public void setDay(int day) {
        this.day = day;
    }

    // getter for month
    public int getMonth() {
        return month;
    }

    // setter for month
    public void setMonth(int month) {
        this.month = month;
    }

    // getter for year
    public int getYear() {
        return year;
    }

    // setter for year
    public void setYear(int year) {
        this.year = year;
    }

    // overriding the toString()
    @Override
    public String toString() {
        return "Day = '" + day + "', Month = '" + month + "', Year = '" + year + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateMatch dateMatch = (DateMatch) o;
        return day == dateMatch.day &&
                month == dateMatch.month &&
                year == dateMatch.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
