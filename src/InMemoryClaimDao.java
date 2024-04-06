
import java.io.IOException;
import java.util.*;

/**
 * 
 */
public class InMemoryClaimDao implements ClaimDao {
    /**
     *
     */
    private HashMap<String,Claim> claims;
    private static InMemoryClaimDao INSTANCE;

    /**
     * Default constructor
     */
    private InMemoryClaimDao() {
        claims = new HashMap<>();
    }

    public static InMemoryClaimDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InMemoryClaimDao();
        }
        return INSTANCE;
    }

    /**
     * @param claim claim
     */
    public void add(Claim claim) throws IOException {
        System.out.println(claim.toString());
        if (claims.put(claim.getId(), claim) == null) {
            throw new IOException("The claim with ID " + claim.getId() + " already exists or is invalid to add");
        }
    }

    /**
     * @param claim claim
     */
    public void update(Claim claim) throws IOException {
        if (claims.replace(claim.getId(), claim) == null) {
            throw new IOException("The claim with ID " + claim.getId() + " doesn't exists");
        }
    }

    /**
     * @param claim claim
     */
    public void delete(Claim claim) {
        
    }

    /**
     * @param id id
     * @return
     */
    public Claim getOne(String id) {
        return claims.get(id);
    }

    /**
     * @return
     */
    public HashMap<String, Claim> getAll() {
        return claims;
    }

}