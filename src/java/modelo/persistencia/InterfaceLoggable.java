package modelo.persistencia;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface InterfaceLoggable {
    
    public Logger getLogger();

    default void logFiner(String message, Object... params) {
        getLogger().log(Level.FINER, message, params);
    }

    default void logFine(String message, Object... params) {
        getLogger().log(Level.FINE, message, params);
    }

    default void logInfo(String message, Object... params) {
        getLogger().log(Level.INFO, message, params);
    }

    default void logWarning(String message, Object... params) {
        getLogger().log(Level.WARNING, message, params);
    }

    default void logSevere(String message, Object... params) {
        getLogger().log(Level.SEVERE, message, params);
    }

    default void logConfig(String message, Object... params) {
        getLogger().log(Level.CONFIG, message, params);
    }

    default void logAll(String message, Object... params) {
        getLogger().log(Level.ALL, message, params);
    }

    default void logOff(String message, Object... params) {
        getLogger().log(Level.OFF, message, params);
    }
}

