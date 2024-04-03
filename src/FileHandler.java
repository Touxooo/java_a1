
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 
 */
public class FileHandler {

    /**
     * Default constructor
     */
    public FileHandler() {
    }

    /**
     * @param fileName
     */
    public List<Customer> readCustomersFromFile(String fileName) {
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                String id = values[0];
                String fullName = values[1];

                PolicyHolderCustomer policyHolder = new PolicyHolderCustomer(id, fullName);
                customers.add(policyHolder);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return customers;
    }

    /**
     * @param fileName
     */
    public void readInsuranceCardsFromFile(String fileName) {
        // TODO implement here
    }

    /**
     * @param fileName
     */
    public List<Claim> readClaimsFromFile(String fileName) {
        return null;
    }

    /**
     * @param customers
     * @param fileName
     */
    public void writeCustomersToFile(List<Customer> customers, String fileName) {
        // TODO implement here
    }

    /**
     * @param insuranceCards
     * @param fileName
     */
    public void writeInsuranceCardsToFile(List<InsuranceCard> insuranceCards, String fileName) {
        // TODO implement here
    }

    /**
     * @param claims
     * @param fileName
     */
    public void writeClaimsToFile(List<Claim> claims, String fileName) {
        // TODO implement here
    }

}