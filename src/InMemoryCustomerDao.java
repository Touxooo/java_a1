
import java.util.*;

/**
 * 
 */
public class InMemoryCustomerDao implements CustomerDao {

    /**
     * Default constructor
     */
    public InMemoryCustomerDao() {
    }

    /**
     * 
     */
    private HashMap<String,Customer> customers;

    @Override
    public boolean add(Customer customer) {
        return false;
    }

    @Override
    public Customer getOne(String id) {
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() {
        return null;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }
}