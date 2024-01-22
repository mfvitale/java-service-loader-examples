/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.mfvitale;

import me.mfvitale.spi.Printer;
import me.mfvitale.spi.Scanner;

public class AllInOnePrinter implements Printer, Scanner {
    static final AllInOnePrinter INSTANCE = new AllInOnePrinter();
    private AllInOnePrinter() {}

    public static AllInOnePrinter provider() {
        return INSTANCE;
    }

    @Override
    public void print() {
        System.out.println("Printing from " + this);
    }

    @Override
    public void scan() {
        System.out.println("Scanning from " + this);
    }
}
