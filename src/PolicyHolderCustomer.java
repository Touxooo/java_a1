
import java.util.*;

/**
 * 
 */
public class PolicyHolderCustomer extends Customer {

    /**
     * Default constructor
     */
    public PolicyHolderCustomer(String id, String fullName) {
        super(id, fullName);
    }

    /**
     * 
     */
    private List<DependentCustomer> dependentsList;

}