import java.io.Serializable;

public class DateMatch implements Serializable {
    // this class is used to handle the day, month and year
    private int day;
    private int month;
    private int year;

    public DateMatch(){

    }

    public DateMatch(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

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

    @Override
    public String toString() {
        return  "\n * Day Played = " + day +
                "\n * Month Played = " + month +
                "\n * Year Played = " + year ;

    }

}
