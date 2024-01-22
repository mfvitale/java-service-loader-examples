import io.mfvitale.AllInOnePrinter;
import me.mfvitale.spi.Printer;
import me.mfvitale.spi.Scanner;

module io.mfvitale.extension {
    requires me.mfvitale.api;

    exports io.mfvitale;

    provides Scanner with AllInOnePrinter;
    provides Printer with AllInOnePrinter;

}