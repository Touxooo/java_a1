
import java.util.*;

/**
 *
 */
public class Menu {
    /**
     *
     */
    private Scanner scanner;
    private InMemoryCustomerDao inMemoryCustomerDao;

    /**
     * Default constructor
     */
    public Menu() {
        scanner = new Scanner(System.in).useDelimiter("\n");
        inMemoryCustomerDao = InMemoryCustomerDao.getInstance();
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

    public void displayError(String msg) {
        System.out.println("\033[0;31m" + msg + "\033[0m");
    }


    /**
     *
     */
    public void displayCustomers() {
        HashMap<String, Customer> customers = inMemoryCustomerDao.getAll();

        for (Map.Entry<String, Customer> set : customers.entrySet()) {
            System.out.println(set.getValue().toString());
        }
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
        System.out.println("1. Display all customers");
        System.out.println("2. New claim");
        System.out.println("3. Exit");
    }

    /**
     *
     */
    public void getSelection() {
        System.out.print("Selection: ");
        switch (scanner.nextInt()) {
            case 1:
                displayCustomers();
                break;
            case 2:
                displayCreateClaimMenu();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                displayError("This option doesn't exist");
                break;
        }
    }

}