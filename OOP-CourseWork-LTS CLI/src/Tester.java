import java.util.Scanner;

public class Tester
{
    public static void main(String[] args) {

        int year = 2019;
        String[] possibleSeason = new String[2];
        int lastTwoDigitsOfTheYear = Integer.parseInt(String.valueOf(year).substring(2));

        possibleSeason[0] = (year-1) + "-" + (lastTwoDigitsOfTheYear);
        possibleSeason[1] = (year) + "-" + (lastTwoDigitsOfTheYear+1);

        for (int i = 0; i < possibleSeason.length; i++) {
            System.out.println("  " + (i+1) + ". " + possibleSeason[i]);
        }

        Scanner in = new Scanner(System.in);
        int seasonOption = in.nextInt();

        boolean invalidOption = false;

        if(seasonOption!=1 && seasonOption!=2){
            System.out.println("invalid input");
        }
    }
}

