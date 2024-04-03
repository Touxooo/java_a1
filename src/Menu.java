
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
     * 
     */
    private ClaimManager claimManager;

    /**
     * 
     */
    private FileHandler fileHandler;

    /**
     * 
     */
    public Menu() {
        scanner = new Scanner(System.in);
        claimManager = new ClaimManager();
        fileHandler = new FileHandler();
    }

    /**
     * 
     */
    public void start() {
        List<Customer> customers = fileHandler.readCustomersFromFile("customers.txt");

        for (Customer customer: customers) {
            System.out.println(customer.toString());
        }
    }

}