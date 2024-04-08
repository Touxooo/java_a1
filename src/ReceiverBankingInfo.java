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

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    @Override
    public String toString() {
        return bankName + " - " + receiverName + " - " + bankNumber;
    }
}