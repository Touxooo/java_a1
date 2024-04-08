/**
 * @author <Thomas Rabiet - s4031917>
 */

import java.io.*;
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
                String examDate = values[4];
                String[] docsList = values[5].split("-");
                String receiverBankingInfoBank = values[6];
                String receiverBankingInfoName = values[7];
                String receiverBankingInfoNumber = values[8];
                String status = values[9];

                LocalDate fDate = LocalDate.parse(date);
                LocalDate fExamDate = LocalDate.parse(examDate);

                Customer insuredPerson = inMemoryCustomerDao.getOne(insuredPersonUid);

                if (insuredPerson == null) {
                    throw new IOException(id + ": The provided insuredPersonUid " + insuredPersonUid + "is invalid");
                }

                if (!cardNumber.equals(insuredPerson.getInsuranceCard().getNumber())) {
                    throw new IOException(id + ": The provided cardNumber " + cardNumber + "is invalid");
                }

                ArrayList<String> docsArrList = new ArrayList<String>(List.of(docsList));

                ReceiverBankingInfo receiverBankingInfo = new ReceiverBankingInfo(receiverBankingInfoName, receiverBankingInfoBank, receiverBankingInfoNumber);

                float amount = -1;

                Status enumStatus = null;
                if (status.equals("New")) {
                    enumStatus = Status.New;
                } else if (status.equals("Processing")) {
                    enumStatus = Status.Processing;
                } else if (status.equals("Done")) {
                    enumStatus = Status.Done;
                    String strAmount = values[10];
                    amount = Float.parseFloat(strAmount);
                } else {
                    throw new IOException(id + ": The provided status " + status + "is invalid");
                }

                if (amount != -1) {
                    claim = new Claim(id, fDate, insuredPerson, cardNumber, fExamDate, docsArrList, receiverBankingInfo, enumStatus, amount);
                } else {
                    claim = new Claim(id, fDate, insuredPerson, cardNumber, fExamDate, docsArrList, receiverBankingInfo, enumStatus);
                }



                inMemoryClaimDao.add(claim);
            }
        } catch (IOException e) {
            System.err.println("Error reading claims file: " + e.getMessage());
            System.exit(-1);
        }
    }

    /**
     *
     */
    public void saveData() {
        saveClaimsToFile("claims.txt");
    }

    /**
     * @param filepath
     */
    public void saveClaimsToFile(String filepath) {
        StringBuilder data = new StringBuilder();
        HashMap<String, Claim> claims = inMemoryClaimDao.getAll();

        for (Map.Entry<String, Claim> set : claims.entrySet()) {
            Claim claim = set.getValue();
            data.append(claim.getId()).append(",")
                    .append(claim.getDate()).append(",")
                    .append(claim.getInsuredPerson().getId()).append(",")
                    .append(claim.getCardNumber()).append(",")
                    .append(claim.getExamDate()).append(",")
                    .append(claim.getDocsList().toString().replace("[", "").replace("]", "").replace(",", "-").replace(" ", "")).append(",")
                    .append(claim.getReceiverBankingInfo().getBankName()).append(",")
                    .append(claim.getReceiverBankingInfo().getReceiverName()).append(",")
                    .append(claim.getReceiverBankingInfo().getBankNumber()).append(",");
            if (claim.getStatus() == Status.New) {
                data.append("New");
            } else if (claim.getStatus() == Status.Processing) {
                data.append("Processing");
            } else {
                data.append("Done").append(",").append(Float.toString(claim.getAmount()));
            }
            data.append("\n");
        }

        try {
            PrintWriter writer = new PrintWriter(filepath);
            writer.print(data);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + e.getMessage());
        }
    }
}