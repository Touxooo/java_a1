
import java.util.*;

/**
 * 
 */
public class InMemoryInsuranceCardDao implements InsuranceCardDao {

    /**
     *
     */
    private HashMap<String, InsuranceCard> insuranceCards;

    private static InMemoryInsuranceCardDao INSTANCE;

    /**
     * Default constructor
     */
    private InMemoryInsuranceCardDao() {
        insuranceCards = new HashMap<>();
    }

    public static InMemoryInsuranceCardDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InMemoryInsuranceCardDao();
        }
        return INSTANCE;
    }

    @Override
    public boolean add(InsuranceCard insuranceCard) {
        if (insuranceCards.put(insuranceCard.getNumber(), insuranceCard) == null) {
            return false;
        }
        return true;
    }

    @Override
    public InsuranceCard getOne(String id) {
        return insuranceCards.get(id);
    }

    @Override
    public HashMap<String, InsuranceCard> getAll() {
        return insuranceCards;
    }

    @Override
    public boolean update(InsuranceCard insuranceCard) {
        if (insuranceCards.replace(insuranceCard.getNumber(), insuranceCard) == null) {
            return false;
        }
        return true;
    }
}