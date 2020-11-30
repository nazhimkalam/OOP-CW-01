package controllers;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a day: ");
        int number = input.nextInt();
        while(number<1 || number>31){
            System.out.println(" Invalid day entered! ");
            System.out.print("Enter a day: ");
            number = input.nextInt();

        }
    }
}
