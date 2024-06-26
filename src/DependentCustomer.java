/**
 * @author <Thomas Rabiet - s4031917>
 */

import java.util.*;

/**
 * 
 */
public class DependentCustomer extends Customer {

    /**
     * Default constructor
     */
    public DependentCustomer(String id, String fullName) {
        super(id, fullName);
    }

    @Override
    public String toString() {
        return super.toString() + "\n| Customer Type: Dependent";
    }
}