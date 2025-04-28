import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws Exception{
        Logger queryLogger = Logger.getLogger("queriesLogger");
        Logger errorLogger = Logger.getLogger("errorsLogger");

        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");

        if (components.length>4){
            errorLogger.severe("Превышено кол-во значений у пользователя - "+data);
            throw new TooMuchTokensException("Превышено кол-во возможных значений у пользователя");
        } else if (components.length<4) {
            errorLogger.severe("Не хватает значений у пользователя - "+data);
            throw new MissedTokensException("Не хватает значений у пользователя");
        } else if (!components[INDEX_EMAIL].contains("@")||!components[INDEX_EMAIL].contains(".")){
            errorLogger.severe("Ошибка в Email, проверьте данные на правильность - "+data);
            throw new WrongEmailException("Ошибка в Email");
        } else if (!components[INDEX_PHONE].matches("^\\++[0-9]{7,11}$")) {
            errorLogger.severe("Ошибка в номере, проверьте данные на правильность - "+data);
            throw new WrongNumberException("Ошибка в номере");
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}