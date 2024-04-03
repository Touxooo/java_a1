
import java.util.*;

/**
 * 
 */
public interface ClaimProcessManager {

    /**
     * @param claim
     */
    public void add(Claim claim);

    /**
     * @param claim
     */
    public void update(Claim claim);

    /**
     * @param claim
     */
    public void delete(Claim claim);

    /**
     * @param id
     * @return
     */
    public Claim getOne(String id);

    /**
     * @return
     */
    public List<Claim> getAll();

}