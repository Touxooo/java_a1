/**
 * @author <Thomas Rabiet - s4031917>
 */

import java.io.IOException;
import java.util.*;

/**
 * 
 */
public interface ClaimDao {

    /**
     * @param claim claim
     */
    public void add(Claim claim) throws IOException;

    /**
     * @param claim claim
     */
    public void update(Claim claim) throws IOException;

    /**
     * @param claim claim
     */
    public void delete(Claim claim);

    /**
     * @param id id
     * @return
     */
    public Claim getOne(String id);

    /**
     * @return
     */
    public HashMap<String, Claim> getAll();

}