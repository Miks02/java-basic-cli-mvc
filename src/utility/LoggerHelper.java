package utility;

import java.io.IOException;
import java.util.logging.*;

public class LoggerHelper {
    private static final Logger logger = Logger.getLogger(LoggerHelper.class.getName());
    static {
        try {
            FileHandler fileHandler = new FileHandler("logs", true);
            ConsoleHandler consoleHandler = new ConsoleHandler();

            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);

            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.ALL);

            logger.addHandler(fileHandler);
            //logger.addHandler(consoleHandler);

            logger.setUseParentHandlers(false);

        }
        catch (IOException e) {
            logger.severe("*Failed to initialize Logger class*\nMessage: " + e.getMessage());
        }
    }

    public static Logger getLogger() {return logger;}
}
