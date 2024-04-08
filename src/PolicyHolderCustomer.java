/**
 * @author <Thomas Rabiet - s4031917>
 */

import java.util.*;

public class PolicyHolderCustomer extends Customer {

    /**
     *
     */
    private HashMap<String, DependentCustomer> dependentsList;

    /**
     * Default constructor
     */
    public PolicyHolderCustomer(String id, String fullName) {
        super(id, fullName);
        dependentsList = new HashMap<>();
    }

    public boolean addDependentCustomer(DependentCustomer dependentCustomer) {
        if (dependentsList.put(dependentCustomer.getId(), dependentCustomer) == null) {
            return false;
        }
        return true;
    }

    public String getDependentsCustomersString() {
        if (dependentsList.isEmpty()) {
            return "(No dependents)";
        }
        StringBuilder res = new StringBuilder("(");

        for (Map.Entry<String, DependentCustomer> set : dependentsList.entrySet()) {
            res.append(set.getKey()).append(" ").append(set.getValue().getFullName()).append(", ");
        }

        res = new StringBuilder(res.substring(0, res.length() - 2) + ") ");

        return res.toString();
    }

    @Override
    public String toString() {
        return super.toString() + " PolicyHolder " + getDependentsCustomersString();
    }
}