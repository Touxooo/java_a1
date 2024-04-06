
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class InsuranceCard {

    /**
     * 
     */
    private String number;

    /**
     * 
     */
    private Customer holder;

    /**
     * 
     */
    private PolicyHolderCustomer policyOwner;

    /**
     * 
     */
    private LocalDate expirationDate;

    public InsuranceCard(String number, Customer holder, PolicyHolderCustomer policyOwner, LocalDate expirationDate) {
        this.number = number;
        this.holder = holder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Customer getHolder() {
        return holder;
    }

    public void setHolder(Customer holder) {
        this.holder = holder;
    }

    public PolicyHolderCustomer getPolicyOwner() {
        return policyOwner;
    }

    public void setPolicyOwner(PolicyHolderCustomer policyOwner) {
        this.policyOwner = policyOwner;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "ID: " + number
                + ", Holder: " + holder.getFullName()
                + ", Policy Owner: " + policyOwner.getFullName()
                + ", Expiration Date: " + expirationDate.toString();
    }
}