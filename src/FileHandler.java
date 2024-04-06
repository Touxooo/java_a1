
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

/**
 *
 */
public class FileHandler {
    private InMemoryCustomerDao inMemoryCustomerDao;
    private InMemoryInsuranceCardDao inMemoryInsuranceCardDao;

    /**
     * Default constructor
     */
    public FileHandler() {
        inMemoryCustomerDao = InMemoryCustomerDao.getInstance();
        inMemoryInsuranceCardDao = InMemoryInsuranceCardDao.getInstance();
    }

    /**
     * @return
     */
    public void loadData() {
        loadCustomersFromFile("customers.txt");
        loadInsuranceCardsFromFile("insurance_cards.txt");
    }

    /**
     * @param filepath
     * @return
     */
    public void loadCustomersFromFile(String filepath) {
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
                } else {
                    throw new IOException("Customer type " + "'" + type + "'" + " is not valid");
                }

                inMemoryCustomerDao.add(customer);

            }
        } catch (IOException e) {
            System.err.println("Error reading customers file: " + e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * @param filepath
     * @return
     */
    public void loadInsuranceCardsFromFile(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            InsuranceCard insuranceCard;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                String number = values[0];
                String holderUid = values[1];
                String policyOwnerUid = values[2];
                String expirationDate = values[3];

                Customer holder = inMemoryCustomerDao.getOne(holderUid);

                if (holder == null) {
                    throw new IOException(number + ": The provided holderUid " + holderUid + "is invalid");
                }

                PolicyHolderCustomer policyOwner = (PolicyHolderCustomer) inMemoryCustomerDao.getOne(policyOwnerUid);

                if (policyOwner == null) {
                    throw new IOException(number + ": The provided policyOwnerUid " + policyOwnerUid + "is invalid");
                }

                LocalDate expDate = LocalDate.parse(expirationDate);

                insuranceCard = new InsuranceCard(number, holder, policyOwner, expDate);

                inMemoryInsuranceCardDao.add(insuranceCard);

                holder.setInsuranceCard(insuranceCard);
            }
        } catch (IOException e) {
            System.err.println("Error reading insurance cards file: " + e.getMessage());
            System.exit(-1);
        }
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