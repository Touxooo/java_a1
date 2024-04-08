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
        StringBuilder res = new StringBuilder();
        res.append("\n| Dependents:");
        if (dependentsList.isEmpty()) {
            return "\n\t No dependents)";
        }
        for (Map.Entry<String, DependentCustomer> set : dependentsList.entrySet()) {
            res.append("\n|\t- ").append(set.getValue().getFullName()).append(" (").append(set.getKey()).append(")");
        }

        return res.toString();
    }

    @Override
    public String toString() {
        return super.toString() + "\n| Customer Type: PolicyHolder " + getDependentsCustomersString();
    }
}