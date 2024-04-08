/**
 * @author <Thomas Rabiet - s4031917>
 */

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class CreateClaimMenu extends Menu {
    private Customer insuredPerson;
    private String insuranceCardNumber;
    private LocalDate examDate;
    private ArrayList<String> docsList;
    private ReceiverBankingInfo receiverBankingInfo;
    private Claim claim;



    /**
     * Default constructor
     */
    public CreateClaimMenu() {
        insuredPerson = null;
        insuranceCardNumber = null;
        examDate = null;
        docsList = null;
        receiverBankingInfo = null;
        claim = null;
    }

    /**
     * 
     */
    public void display() {
        System.out.println("New claim");

        insuredPerson = getInsuredPerson();
        insuranceCardNumber = getInsuranceCardNumber(insuredPerson);
        examDate = getExamDate();
        docsList = getDocsList();
        receiverBankingInfo = getReceiverBankingInfo();

        if (!getConfirmation()) {
            return;
        }

        claim = new Claim(insuredPerson, insuranceCardNumber, examDate, docsList, receiverBankingInfo);

        try {
            inMemoryClaimDao.add(claim);
        } catch (IOException e) {
            displayError("The Claim hasn't been created due to an error");
        }
    }

    /**
     * @return
     */
    public Customer getInsuredPerson() {
        String insuredPersonUid = null;
        Customer insuredPerson = null;

        System.out.print("Insured Person UID: ");
        insuredPersonUid = scanner.next();

        insuredPerson = inMemoryCustomerDao.getOne(insuredPersonUid);

        if (insuredPerson == null) {
            displayError("The insured person with ID " + insuredPersonUid + " doesn't exists");
            return getInsuredPerson();
        }

        return insuredPerson;
    }

    /**
     * @return
     */
    public String getInsuranceCardNumber(Customer insuredPerson) {
        String insuranceCardId = null;

        System.out.print("Insurance Card Number: ");
        insuranceCardId = scanner.next();

        if (!insuredPerson.getInsuranceCard().getNumber().equals(insuranceCardId)) {
            displayError("The insurance card number with number " + insuranceCardId
                    + " doesn't match with the insured person insurance card");
            return  getInsuranceCardNumber(insuredPerson);
        }

        return insuranceCardId;
    }

    /**
     * @return
     */
    public LocalDate getExamDate() {
        String examDateStr = null;
        LocalDate examDate = null;

        System.out.print("Exam Date (Format yyyy-MM-dd): ");
        examDateStr = scanner.next();

        try {
           examDate = LocalDate.parse(examDateStr);
        } catch (DateTimeException e) {
            displayError("The provided exam date is incorrect");
            return getExamDate();
        }

        return examDate;
    }

    /**
     * @return
     */
    public ArrayList<String> getDocsList() {
        ArrayList<String> docsList = new ArrayList<>();
        String documentName = null;

        System.out.print("Any documents to attach? (Y/n): ");
        if (scanner.next().equals("Y")) {
            System.out.print("Document name: ");
            documentName = scanner.next();
            if (!documentName.isEmpty()) {
                docsList.add(documentName);
                System.out.println("Document added");
            }
            System.out.print("Do you want to add a new document? (Y/n): ");
            while (scanner.next().equals("Y")) {
                System.out.print("Document name: ");
                documentName = scanner.next();
                if (!documentName.isEmpty()) {
                    docsList.add(documentName);
                    System.out.println("Document added");
                }
                System.out.print("Do you want to add a new document? (Y/n): ");
            }
        }
        return docsList;
    }

    /**
     * @return
     */
    public ReceiverBankingInfo getReceiverBankingInfo() {
        String bankName;
        String bankHolderName;
        String bankHolderNumber;
        ReceiverBankingInfo receiverBankingInfo = null;

        System.out.println("Receiver Banking Info: ");
        System.out.print("Bank Name: ");
        bankName = scanner.next();
        System.out.print("Bank Holder Name: ");
        bankHolderName = scanner.next();
        System.out.print("Bank Holder Number: ");
        bankHolderNumber = scanner.next();

        receiverBankingInfo = new ReceiverBankingInfo(bankHolderName, bankName, bankHolderNumber);

        return receiverBankingInfo;
    }

    /**
     * @return
     */
    public boolean getConfirmation() {
        System.out.println("RECAP");
        System.out.println("Insured Person: " + insuredPerson.toString());
        System.out.println("Card Number: " + insuranceCardNumber);
        System.out.println("Exam Date: " + examDate.toString());
        System.out.println("Documents: ");
        for (String doc: docsList) {
            System.out.println("- " + doc);
        }
        System.out.println("Receiver Banking Info: " + receiverBankingInfo.toString());

        System.out.print("Do you want to confirm the creation of the claim? (Y/n): ");

        return scanner.next().equals("Y");
    }

}