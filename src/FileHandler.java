
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class FileHandler {
    InMemoryCustomerDao inMemoryCustomerDao;

    /**
     * Default constructor
     */
    public FileHandler() {
        inMemoryCustomerDao = InMemoryCustomerDao.getInstance();
    }

    /**
     * @return
     */
    public boolean loadData() {
        loadCustomersFromFile("customers.txt");
        return true;
    }

    /**
     * @param filepath
     * @return
     */
    public boolean loadCustomersFromFile(String filepath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            Customer customer;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                String id = values[0];
                String fullName = values[1];
                String type = values[2];

                if (type.equals("policyHolder")) {
                    customer = new PolicyHolderCustomer(id, fullName);
                } else if (type.equals("dependent")) {
                    customer = new DependentCustomer(id, fullName);
                    String policyHolderUid = values[3];
                    if (policyHolderUid.isEmpty()) {
                        throw new IOException("Customer with id ' "+ id + "' dependent has invalid holder");
                    }
                    PolicyHolderCustomer policyHolderCustomer = (PolicyHolderCustomer) inMemoryCustomerDao.getOne(policyHolderUid);
                    policyHolderCustomer.addDependentCustomer((DependentCustomer) customer);
                    inMemoryCustomerDao.update(customer);
                } else {
                    throw new IOException("Customer type " + "'" + type + "'" + " is not valid");
                }

                inMemoryCustomerDao.add(customer);

            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(-1);
        }

        return true;
    }

    /**
     * @param filepath
     * @return
     */
    public boolean loadInsuranceCardsFromFile(String filepath) {
        // TODO implement here
        return true;
    }

    /**
     * @param filepath
     * @return
     */
    public boolean loadClaimsFromFile(String filepath) {
        // TODO implement here
        return true;
    }

    /**
     * @return
     */
    public boolean saveData() {
        // TODO implement here
        return true;
    }

    /**
     * @param filepath
     * @return
     */
    public boolean saveCustomersFromFile(String filepath) {
        // TODO implement here
        return true;
    }

    /**
     * @param filepath
     * @return
     */
    public boolean saveInsuranceCardsFromFile(String filepath) {
        // TODO implement here
        return true;
    }

    /**
     * @param filepath
     * @return
     */
    public boolean saveClaimsFromFile(String filepath) {
        // TODO implement here
        return true;
    }

}