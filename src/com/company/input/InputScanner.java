package com.company.input;

import java.util.Objects;
import java.util.Scanner;

public class InputScanner {

    private static Scanner scanner = null;

    private InputScanner() {
    }

    public static Scanner getInstance() {
        if (Objects.isNull(scanner)) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

}
