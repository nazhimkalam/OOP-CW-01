import java.util.ArrayList;
import java.util.Comparator;

public class Tester
{

    public static void main(String[] args) {
        ArrayList<Date> d = new ArrayList<>();

        d.add(new Date(14,10,2021));
        d.add(new Date(7,7,2020));
        d.add(new Date(7,2,2021));
        d.add(new Date(14,12,2020));

        for (Date date: d) {
            System.out.println(date);
        }

        Comparator<Date> sortByDate = (match1, match2) -> {
            if(match1.getYear() == match2.getYear()){
                if (match1.getMonth() == match2.getMonth()) {
                    if (match1.getDay() > match2.getDay()) {
                        return 1;
                    }
                } else if (match1.getMonth() > match2.getMonth()) {
                    return 1;
                }
            }else if (match1.getYear() > match2.getYear()) {
                return 1;
            }

            return -1;

        };

        System.out.println();

        d.sort(sortByDate);
        for (Date date: d) {
            System.out.println(date);
        }

    }

}


class Date{
    int day;
    int month;
    int year;

    public Date(int day, int month, int year) {
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
        return "[" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ']';
    }
}
