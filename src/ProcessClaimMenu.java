
import java.io.IOException;
import java.util.*;

/**
 * 
 */
public class ProcessClaimMenu extends Menu {
    float amount;
    Status status;
    Claim claim;

    /**
     * Default constructor
     */
    public ProcessClaimMenu() {
        amount = 0.0f;
        status = Status.New;
    }

    /**
     * 
     */
    public void display() {
       System.out.println("Process a claim");

        claim = getClaim();
        status = getStatus();
        if (status == Status.Done) {
            amount = getAmount();
        }

        if (!getConfirmation()) {
            return;
        }

        claim.setStatus(status);
        if (status == Status.Done) {
            claim.setAmount(amount);
        }
    }

    public Claim getClaim() {
        String claimId = null;
        Claim claim = null;

        System.out.print("Claim ID: ");
        claimId = scanner.next();

        claim = inMemoryClaimDao.getOne(claimId);

        if (claim == null) {
            displayError("The claim with ID " + claimId + " doesn't exist");
            return getClaim();
        }

        return claim;
    }

    /**
     * @return
     */
    public float getAmount() {
        String strAmount;
        float amount;

        System.out.print("Claim amount: ");
        strAmount = scanner.next();

        try {
            amount = Float.parseFloat(strAmount);
        } catch (NumberFormatException e) {
            displayError("The provided claim amount is invalid");
            return getAmount();
        }

        return amount;
    }

    /**
     * @return
     */
    public Status getStatus() {
        String strStatus;
        Status status;

        System.out.println("Status:");
        System.out.println("1. Processing");
        System.out.println("2. Done");
        System.out.print("Selection: ");
        strStatus = scanner.next();

        if (strStatus.equals("1")) {
            status = Status.Processing;
        } else if (strStatus.equals("2")) {
            status = Status.Done;
        } else {
            displayError("The provided status is incorrect");
            return getStatus();
        }

        return status;
    }

    /**
     * @return
     */
    public boolean getConfirmation() {
        if (status == Status.Processing) {
            System.out.print("Are you sure to process the claim to processing? (Y/n): ");
        } else {
            System.out.print("Are you sure to process the claim to done with the amount " + amount + "? (Y/n): ");
        }

        return scanner.next().equals("Y");
    }

}