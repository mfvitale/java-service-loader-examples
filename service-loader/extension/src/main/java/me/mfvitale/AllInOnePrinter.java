package me.mfvitale;

import me.mfvitale.spi.Printer;
import me.mfvitale.spi.Scanner;

public class AllInOnePrinter implements Printer, Scanner {

    @Override
    public void print() {
        System.out.println("Printing from " + this);
    }

    @Override
    public void scan() {
        System.out.println("Scanning from " + this);
    }
}
