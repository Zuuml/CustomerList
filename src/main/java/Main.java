import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.*;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Main {
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        Logger queryLogger = Logger.getLogger("queriesLogger");
        Logger errorLogger = Logger.getLogger("errorsLogger");



        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);

//            try {
//                FileHandler queriesHandler = new FileHandler("logs/queries.log", true);
//                queriesHandler.setLevel(Level.INFO);
//                queriesHandler.setFormatter(new SimpleFormatter());
//
//                FileHandler errorsHandler = new FileHandler("logs/errors.log", true);
//                errorsHandler.setLevel(Level.SEVERE);
//                errorsHandler.setFormatter(new SimpleFormatter());
//
//                queryLogger.addHandler(queriesHandler);
//                errorLogger.addHandler(errorsHandler);
//
//            } catch (IOException e) {
//                throw new RuntimeException("Ошибка логирования: "+e);
//            }

            try {
                queryLogger.info("Получена команда: "+command);
                if (tokens[0].equals("add")) {
                    executor.addCustomer(tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                } else if (tokens[0].equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                } else {
                    System.out.println(COMMAND_ERROR);
                    errorLogger.severe("Ошибка команды: "+command);
                }
            }catch (Exception e){
                errorLogger.severe("Ошибка при выполнении команды: " + command + "\n" + e.getMessage());
            }

        }
    }
}
