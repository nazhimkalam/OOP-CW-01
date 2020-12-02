package controllers;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        callMe();
    }

    public static String callMe() {
        Scanner input = new Scanner(System.in);
        System.out.println(" Enter your name: ");
        String name = input.nextLine();

        System.out.println(" My name is " + name);
        return name;
    }
}
