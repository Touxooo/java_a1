/**
 * @author <Thomas Rabiet - s4031917>
 */

import java.util.*;

/**
 * 
 */
public class DeleteClaimMenu extends Menu {
    private Claim claim;

    /**
     * Default constructor
     */
    public DeleteClaimMenu() {
        claim = null;
    }

    /**
     * 
     */
    public void display() {
        System.out.println("Delete a claim");
        claim = getClaim();

        if (!getConfirmation()) {
            return;
        }

        inMemoryClaimDao.delete(claim);
    }

    /**
     * @return
     */
    public Claim getClaim() {
        String claimId;
        Claim claim;

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
    public boolean getConfirmation() {
        System.out.println(claim.toString());
        System.out.print("Are you sure to delete this claim? (Y/n): ");
        return scanner.next().equals("Y");
    }

}