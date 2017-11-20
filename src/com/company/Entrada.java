package com.company;

import java.util.Scanner;

public class Entrada {
    private static Entrada ourInstance = new Entrada();

    private Scanner scan;

    public static Entrada getInstance() {
        return ourInstance;
    }

    private Entrada() {
        scan = new Scanner(System.in);
    }

    public char leChar() {
        char in = scan.next().charAt(0);
        scan.nextLine();
        return in;
    }

    public String leString() {
        return scan.nextLine();
    }

    public Scanner getScan() {
        return scan;
    }
}
