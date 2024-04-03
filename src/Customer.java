
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
    private List<Claim> claimsList;

    /**
     * Default constructor
     */
    public Customer(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}