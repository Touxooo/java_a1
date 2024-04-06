
import java.util.*;

/**
 * 
 */
public interface InsuranceCardDao {

    /**
     * @param insuranceCard
     * @return
     */
    public boolean add(InsuranceCard insuranceCard);

    /**
     * @param id
     * @return
     */
    public InsuranceCard getOne(String id);

    /**
     * @return
     */
    public ArrayList<InsuranceCard> getAll();

    /**
     * @param insuranceCard
     * @return
     */
    public boolean update(InsuranceCard insuranceCard);

}