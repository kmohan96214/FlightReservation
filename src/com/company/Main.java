package com.company;

import com.company.input.Admin;
import com.company.input.InputScanner;
import com.company.input.User;

import java.io.IOException;
import java.util.Scanner;

/*
    Takes the first input starts the flow. either admin or user
 */

public class Main {

    private static String YES = "Y";
    private static String NO = "N";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello please enter the option 1. User 2. Admin: ");
        Scanner scanner = InputScanner.getInstance();

        Integer option = scanner.nextInt();

        // choose the flow based on the input
        while (Boolean.TRUE) {
            switch (option) {
                case 1: new User().flow(); break;
                case 2: new Admin().flow(); break;
                default: break;
            }
            System.out.println("Do you want to continue (Y/N) :");
            String input = scanner.next();
            if (input.toUpperCase().contains(NO)) {
                break;
            }
        }
    }

}
