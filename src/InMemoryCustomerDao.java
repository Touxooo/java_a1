
import java.util.*;

/**
 * 
 */
public class InMemoryCustomerDao implements CustomerDao {
    /**
     *
     */
    private HashMap<String,Customer> customers;

    private static InMemoryCustomerDao INSTANCE;

    /**
     * Default constructor
     */
    private InMemoryCustomerDao() {
        customers = new HashMap<>();
    }

    public static InMemoryCustomerDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InMemoryCustomerDao();
        }

        return INSTANCE;
    }

    @Override
    public boolean add(Customer customer) {
        if (customers.put(customer.getId(), customer) == null) {
            return false;
        }
        return true;
    }

    @Override
    public Customer getOne(String id) {
        return customers.get(id);
    }

    @Override
    public HashMap<String, Customer> getAll() {
        return customers;
    }

    @Override
    public boolean update(Customer customer) {
        if (customers.replace(customer.getId(), customer) == null) {
            return false;
        }
        return true;
    }
}