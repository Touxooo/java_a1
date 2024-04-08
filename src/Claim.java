/**
 * @author <Thomas Rabiet - s4031917>
 */

import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Claim {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private LocalDate date;

    /**
     * 
     */
    private Customer insuredPerson;


    /**
     * 
     */
    private String cardNumber;

    /**
     * 
     */
    private LocalDate examDate;

    /**
     * 
     */
    private List<String> docsList;

    /**
     * 
     */
    private float amount;

    /**
     * 
     */
    private Status status;

    /**
     * 
     */
    private ReceiverBankingInfo receiverBankingInfo;

    public Claim(Customer insuredPerson, String cardNumber, LocalDate examDate, List<String> docsList, ReceiverBankingInfo receiverBankingInfo) {
        this.date = LocalDate.now();
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.docsList = docsList;
        this.receiverBankingInfo = receiverBankingInfo;
        this.status = Status.New;
    }

    public Claim(String id, LocalDate date, Customer insuredPerson, String cardNumber, LocalDate examDate, List<String> docsList, ReceiverBankingInfo receiverBankingInfo, Status status) {
        this.id = id;
        this.date = date;
        this.examDate = examDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.docsList = docsList;
        this.receiverBankingInfo = receiverBankingInfo;
        this.status = status;
    }

    public Claim(String id, LocalDate date, Customer insuredPerson, String cardNumber, LocalDate examDate, List<String> docsList, ReceiverBankingInfo receiverBankingInfo, Status status, float amount) {
        this.id = id;
        this.date = date;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.docsList = docsList;
        this.amount = amount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(Customer insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setInsuranceCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public List<String> getDocsList() {
        return docsList;
    }

    public void setDocsList(List<String> docsList) {
        this.docsList = docsList;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ReceiverBankingInfo getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    public void setReceiverBankingInfo(ReceiverBankingInfo receiverBankingInfo) {
        this.receiverBankingInfo = receiverBankingInfo;
    }

    @Override
    public String toString() {
        String baseClaim = "| ID: " + id
                + "\n| Date: " + date.toString()
                + "\n| Insured Person: " + insuredPerson.getFullName()
                + "\n| Card Number: " + cardNumber
                + "\n| Exam date: " + examDate.toString()
                + "\n| Docs List: "+ docsList.toString()
                + "\n| Receiver Banking Info: " + receiverBankingInfo.toString()
                + "\n| Status: " + status;

        if (status == Status.Done) {
            return baseClaim + "\n| Amount: " + amount;
        } else {
            return baseClaim;
        }
    }
}