/**
 * @author <Thomas Rabiet - s4031917>
 */

public class Main {
    public static void main(String[] args) {

        FileHandler fileHandler = new FileHandler();

        fileHandler.loadData();

        Thread savingDataHook = new Thread(fileHandler::saveData);
        Runtime.getRuntime().addShutdownHook(savingDataHook);

        Menu menu = new Menu();

        menu.display();

    }
}