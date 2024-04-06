
import java.util.*;

/**
 * 
 */
public abstract class Customer {

    /**
     * Default constructor
     */
    public Customer(String id, String fullName) {
       this.id = id;
       this.fullName = fullName;
       this.insuranceCard = null;
    }

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
    private List<Claim> claimsList;

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

    public List<Claim> getClaimsList() {
        return claimsList;
    }

    public void setClaimsList(List<Claim> claimsList) {
        this.claimsList = claimsList;
    }

    @Override
    public String toString() {
        String base = "ID: " + id + ", Full Name: " + fullName;

        if (insuranceCard == null) {
            return base + ", No Insurance Card";
        } else {
            return base + ", Insurance Card Number: " + insuranceCard.getNumber();
        }
    }
}