package me.mfvitale;

import me.mfvitale.spi.Printer;
import me.mfvitale.spi.Scanner;

import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        Printer printer = ServiceLoader.load(Printer.class).findFirst()
                .orElseThrow();
        Scanner scanner = ServiceLoader.load(Scanner.class).findFirst()
                .orElseThrow();

        System.out.println(printer == scanner);
        printer.print();
        scanner.scan();

    }
}
