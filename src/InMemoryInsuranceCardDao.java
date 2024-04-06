
import java.util.*;

/**
 * 
 */
public class InMemoryInsuranceCardDao implements InsuranceCardDao {

    /**
     * Default constructor
     */
    public InMemoryInsuranceCardDao() {
    }

    /**
     * 
     */
    private HashMap<String, InsuranceCard> insuranceCards;

    @Override
    public boolean add(InsuranceCard insuranceCard) {
        return false;
    }

    @Override
    public InsuranceCard getOne(String id) {
        return null;
    }

    @Override
    public ArrayList<InsuranceCard> getAll() {
        return null;
    }

    @Override
    public boolean update(InsuranceCard insuranceCard) {
        return false;
    }
}