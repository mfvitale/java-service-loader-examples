import me.mfvitale.spi.Printer;
import me.mfvitale.spi.Scanner;

module me.mfvitale.app {
    requires me.mfvitale.api;

    uses Printer;
    uses Scanner;
}