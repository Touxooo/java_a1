
import java.util.*;

/**
 * 
 */
public interface ClaimProcessManager {

    /**
     * @param Claim
     */
    public void add(void Claim);

    /**
     * @param Claim
     */
    public void update(void Claim);

    /**
     * @param Claim
     */
    public void delete(void Claim);

    /**
     * @param String 
     * @return
     */
    public Claim getOne(void String);

    /**
     * @return
     */
    public List<Claim> getAll();

}