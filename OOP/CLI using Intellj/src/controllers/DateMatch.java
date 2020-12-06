package controllers;
import java.io.Serializable;

/*
 * @author Nazhim Kalam
 * @UowID: w1761265
 * @StudentID: SE2019281
 * OOP CW 01
 * Java version 8 or higher required
 */

public class DateMatch implements Serializable {
    // this class is used to handle the date for the match played

    // Variable used
    private int day;
    private int month;
    private int year;

    public DateMatch(){
        // default constructor
    }

    // Parameter constructor
    public DateMatch(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Getters and Setters
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // The toString method to display the date details
    @Override
    public String toString() {
        return  "\n * Day Played = " + day +
                "\n * Month Played = " + month +
                "\n * Year Played = " + year ;

    }

}
