/**
 * @author <Thomas Rabiet - s4031917>
 */

import java.util.*;

/**
 * 
 */
public abstract class Customer {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String fullName;

    /**
     * 
     */
    private InsuranceCard insuranceCard;

    /**
     * 
     */
    private ArrayList<Claim> claimsList;

    /**
     * Default constructor
     */
    public Customer(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = null;
        this.claimsList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }


    public void addClaim(Claim claim) {
        this.claimsList.add(claim);
    }

    @Override
    public String toString() {
        String base = "| ID: " + id + "\n| Full Name: " + fullName;

        if (insuranceCard == null) {
            return base + "\n| Insurance Card: None";
        } else {
            return base + "\n| Insurance Card: " + insuranceCard.getNumber();
        }
    }
}