/**
 * @author <Thomas Rabiet - s4031917>
 */

import java.util.*;

/**
 * 
 */
public class Util {

    /**
     * Default constructor
     */
    public Util() {
    }

    public static String generateID(int n) {
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int digit = random.nextInt(10);
            id.append(digit);
        }

        return id.toString();
    }

//    /**
//     * @param List
//     */
//    public void sortEntities(void List) {
//        // TODO implement here
//    }
//
//    /**
//     * @param List
//     * @param fileName
//     */
//    public void saveReportToFile(void List, void fileName) {
//        // TODO implement here
//    }

}