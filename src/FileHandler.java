
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
    private InMemoryClaimDao inMemoryClaimDao;

    /**
     * Default constructor
     */
    public FileHandler() {
        inMemoryCustomerDao = InMemoryCustomerDao.getInstance();
        inMemoryInsuranceCardDao = InMemoryInsuranceCardDao.getInstance();
        inMemoryClaimDao = InMemoryClaimDao.getInstance();
    }

    /**
     * @return
     */
    public void loadData() {
        loadCustomersFromFile("customers.txt");
        loadInsuranceCardsFromFile("insurance_cards.txt");
        loadClaimsFromFile("claims.txt");
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
    public void loadClaimsFromFile(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Claim claim;
                String[] values = line.split(",");

                String id = values[0];
                String date = values[1];
                String insuredPersonUid = values[2];
                String cardNumber = values[3];
                String[] docsList = values[4].split("-");
                String receiverBankingInfoBank = values[5];
                String receiverBankingInfoName = values[6];
                String receiverBankingInfoNumber = values[7];
                String status = values[8];

                LocalDate fDate = LocalDate.parse(date);

                Customer insuredPerson = inMemoryCustomerDao.getOne(insuredPersonUid);

                if (insuredPerson == null) {
                    throw new IOException(id + ": The provided insuredPersonUid " + insuredPersonUid + "is invalid");
                }

                if (!cardNumber.equals(insuredPerson.getInsuranceCard().getNumber())) {
                    throw new IOException(id + ": The provided cardNumber " + cardNumber + "is invalid");
                }

                ArrayList<String> docsArrList = new ArrayList<String>(List.of(docsList));

                ReceiverBankingInfo receiverBankingInfo = new ReceiverBankingInfo(receiverBankingInfoName, receiverBankingInfoBank, receiverBankingInfoNumber);

                Status enumStatus = null;
                if (status.equals("New")) {
                    enumStatus = Status.New;
                } else if (status.equals("Processing")) {
                    enumStatus = Status.Processing;
                } else if (status.equals("Done")) {
                    enumStatus = Status.Done;
                } else {
                    throw new IOException(id + ": The provided status " + status + "is invalid");
                }

                claim = new Claim(id, fDate, insuredPerson, cardNumber, docsArrList, receiverBankingInfo, enumStatus);

                System.out.println(claim.toString());

                inMemoryClaimDao.add(claim);
//                insuredPerson.addClaim(claim);
            }
        } catch (IOException e) {
            System.err.println("Error reading claims file: " + e.getMessage());
            System.exit(-1);
        }
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