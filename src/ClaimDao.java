
import java.util.*;

/**
 * 
 */
public interface ClaimDao {

    /**
     * @param claim claim
     */
    public void add(Claim claim);

    /**
     * @param claim claim
     */
    public void update(Claim claim);

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
    public ArrayList<Claim> getAll();

}