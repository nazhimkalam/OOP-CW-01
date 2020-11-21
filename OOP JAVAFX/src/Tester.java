import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tester {
    public static void main(String[] args) {

        ArrayList<Date> dates = new ArrayList<>();
        dates.add(new Date(12,4,2020));
        dates.add(new Date(18,4,2020));
        dates.add(new Date(6,10,2020));
        dates.add(new Date(4,1,2020));

        for (Date date: dates) {
            System.out.println(date);
        }
//        Comparator<Date> sortByDate = (Date1, Date2) -> {
//            // adding my special logic to sort any dates easily
//
//            // for Date 1
//            String day01 = "";
//            String month01 = "";
//            String year01 = "";
//
//            day01+= Date1.getDay();
//            day01+= Date1.getDay();
//
//            month01+= Date1.getMonth();
//            month01+= Date1.getMonth();
//
//            year01 += Date1.getYear();
//
//
//            // for Date 2
//            String day02 = "";
//            String month02 = "";
//            String year02 = "";
//
//            day02+= Date2.getDay();
//            day02+= Date2.getDay();
//
//            month02+= Date2.getMonth();
//            month02+= Date2.getMonth();
//
//            year02 += Date2.getYear();
//            System.out.println(day02 + month02 + year02);
//
//            if ( Long.parseLong(day01 + month01 + year01) < Long.parseLong(day02 + month02 + year02) ){
//               return 1;
//            }
//            return -1;
//        };

        Comparator<Date> sortByDate = (Date1, Date2) -> {
            if ( Date1.getMonth() == Date2.getMonth()){
                if(Date1.getDay() > Date2.getDay()){
                    return 1;
                }
            }else if ( Date1.getMonth() > Date2.getMonth()){
               return 1;
            }
            return -1;
        };

        dates.sort(sortByDate);

        System.out.println();
        for (Date date: dates) {
            System.out.println(date);
        }

    }
}

class Date{
    int day;
    int month;
    int year;

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
        return "[ day=" + day + ", month=" + month + ", year=" + year + "]";
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
}