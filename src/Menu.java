
import java.util.*;

/**
 *
 */
public class Menu {
    /**
     *
     */
    private Scanner scanner;

    /**
     * Default constructor
     */
    public Menu() {
        scanner = new Scanner(System.in).useDelimiter("\n");
    }

    /**
     *
     */
    public void display() {
        displayOptions();
        while (true) {
            getSelection();
        }
    }

    public void display_error(String msg) {
        System.out.println("\033[0;31m" + msg + "\033[0m");
    }


    /**
     *
     */
    public void displayUsers() {
        // TODO implement here
    }

    /**
     *
     */
    public void displayInsuranceCards() {
        // TODO implement here
    }

    /**
     *
     */
    public void displayClaims() {
        // TODO implement here
    }

    /**
     *
     */
    public void displayCreateClaimMenu() {
        // TODO implement here
    }

    /**
     *
     */
    public void displayProcessClaimMenu() {
        // TODO implement here
    }

    /**
     *
     */
    public void displayDeleteClaimMenu() {
        // TODO implement here
    }

    public void displayOptions() {
        System.out.println("1. New claim");
        System.out.println("2. Exit");
    }

    /**
     *
     */
    public void getSelection() {
        System.out.print("Selection: ");
        switch (scanner.nextInt()) {
            case 1:
                displayCreateClaimMenu();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                display_error("This option doesn't exist");
                break;
        }
    }

}