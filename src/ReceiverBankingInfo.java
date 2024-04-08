/**
 * @author <Thomas Rabiet - s4031917>
 */
public class ReceiverBankingInfo {

    /**
     * Default constructor
     */
    public ReceiverBankingInfo(String receiverName, String bankName, String bankNumber) {
        this.receiverName = receiverName;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
    }

    /**
     * 
     */
    private String receiverName;

    /**
     * 
     */
    private String bankName;

    /**
     * 
     */
    private String bankNumber;

    @Override
    public String toString() {
        return bankName + " - " + receiverName + " - " + bankNumber;
    }
}