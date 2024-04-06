
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
    private InMemoryInsuranceCardDao inMemoryInsuranceCardDao;
    private InMemoryClaimDao inMemoryClaimDao;

    /**
     * Default constructor
     */
    public Menu() {
        scanner = new Scanner(System.in).useDelimiter("\n");
        inMemoryCustomerDao = InMemoryCustomerDao.getInstance();
        inMemoryInsuranceCardDao = InMemoryInsuranceCardDao.getInstance();
        inMemoryClaimDao = InMemoryClaimDao.getInstance();
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
        HashMap<String, InsuranceCard> insuranceCards = inMemoryInsuranceCardDao.getAll();

        for (Map.Entry<String, InsuranceCard> set : insuranceCards.entrySet()) {
            System.out.println(set.getValue().toString());
        }
    }

    /**
     *
     */
    public void displayClaims() {
        HashMap<String, Claim> claims = inMemoryClaimDao.getAll();

        for (Map.Entry<String, Claim> set : claims.entrySet()) {
            System.out.println(set.getValue().toString());
        }
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
        System.out.println("2. Display all insurance cards");
        System.out.println("3. Display all claims");
        System.out.println("3. New claim");
        System.out.println("7. Exit");
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
                displayInsuranceCards();
                break;
            case 3:
                displayClaims();
                break;
            case 4:
                displayCreateClaimMenu();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                displayError("This option doesn't exist");
                break;
        }
    }

}