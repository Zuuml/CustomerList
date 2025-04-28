import java.io.IOException;
import java.util.logging.*;

public class TrhrowableExceptions {

    private static final Logger logger = Logger.getLogger(TrhrowableExceptions.class.getName());

    public static void main(String[] args) throws IOException {

        LogManager.getLogManager().reset();

        Handler infoHandler = new FileHandler("logs/queries.log", true);

        infoHandler.setLevel(Level.INFO); //про вот эту всю шнягу не говорилось в видосах... я более чем на 100% уверен... "респект" вообще преподу! умеет объяснять...
        infoHandler.setFormatter(new SimpleFormatter());
        infoHandler.setFilter(record -> record.getLevel() == Level.INFO);

        logger.addHandler(infoHandler);

        Handler errorHandler = new FileHandler("logs/error.log", true);

        errorHandler.setLevel(Level.WARNING);
        errorHandler.setFormatter(new SimpleFormatter());
        errorHandler.setFilter(record ->
                record.getLevel() == Level.WARNING || record.getLevel() == Level.SEVERE);

    }
}
