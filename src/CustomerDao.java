/**
 * @author <Thomas Rabiet - s4031917>
 */

import java.util.*;

/**
 * 
 */
public interface CustomerDao {

    /**
     * @param customer
     * @return
     */
    public boolean add(Customer customer);

    /**
     * @param id
     * @return
     */
    public Customer getOne(String id);

    /**
     * @return
     */
    public HashMap<String, Customer> getAll();

    /**
     * @param customer
     * @return
     */
    public boolean update(Customer customer);

}