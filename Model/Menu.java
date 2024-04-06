



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
        scanner = new Scanner(System.in).useDelimiter("\n");
        claimManager = new ClaimManager();
        fileHandler = new FileHandler();
    }

    public void display_menu() {
        System.out.println("1. New claim");
        System.out.println("2. Exit");
    }

    public void display_error(String msg) {
        System.out.println("\033[0;31m" + msg + "\033[0m");
    }

    public void display_new_claim_form() {

        String insuredPersonFullName;
        String cardNumber;
        String examDate;
        String documentName;
        ArrayList<String> documents = new ArrayList<String>();
        String bankName;
        String bankHolderName;
        String bankHolderNumber;

        System.out.println("New claim");

        System.out.print("Insured Person Full Name: ");
        insuredPersonFullName = scanner.next();

        System.out.print("Card Number: ");
        cardNumber = scanner.next();

        System.out.print("Exam date: ");
        examDate = scanner.next();

        System.out.print("Any documents to attach? (Y/n): ");
        if (scanner.next().equals("Y")) {
            System.out.print("Document name: ");
            documentName = scanner.next();
            if (!documentName.isEmpty()) {
                documents.add(documentName);
                System.out.println("Document added");
            }
            System.out.print("Do you want to add a new document? (Y/n): ");
            while (scanner.next().equals("Y")) {
                System.out.print("Document name: ");
                documentName = scanner.next();
                if (!documentName.isEmpty()) {
                    documents.add(documentName);
                    System.out.println("Document added");
                }
                System.out.print("Do you want to add a new document? (Y/n): ");
            }
        }

        System.out.println("Receiver Banking Info: ");
        System.out.print("Bank Name: ");
        bankName = scanner.next();
        System.out.print("Bank Holder Name: ");
        bankHolderName = scanner.next();
        System.out.print("Bank Holder Number: ");
        bankHolderNumber = scanner.next();

        System.out.println("RECAP");
        System.out.println("Insured Person Full Name: " + insuredPersonFullName);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Exam Date: " + examDate);
        System.out.println("Documents: ");
        for (String document: documents) {
            System.out.println("- " + document);
        }
        System.out.println("Banking Info:");
        System.out.println("Bank Name: " + bankName);
        System.out.println("Bank Holder Name: " + bankHolderName);
        System.out.println("Bank Holder Number: " + bankHolderNumber);

        System.out.print("Do you want to confirm the creation of the claim? (Y/n): ");
        if (scanner.next().equals("Y")) {
            Claim claim = new Claim()
        }
    }

    public void select_option() {
        System.out.print("Selection: ");
        switch (scanner.nextInt()) {
            case 1:
                display_new_claim_form();
                display_menu();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                display_error("This option doesn't exist");
                break;
        }

    }

    /**
     *
     */
    public void start() {
//        List<Customer> customers = fileHandler.readCustomersFromFile("customers.txt");
//        List<InsuranceCard> insuranceCards = fileHandler.readInsuranceCardsFromFile("insurance_cards.txt");
        display_menu();
        while (true) {
            select_option();
        }
    }

    public void displayAllCustomers(List<Customer> customers) {
        for (Customer customer: customers) {
            System.out.println(customer.toString());
        }
    }
}