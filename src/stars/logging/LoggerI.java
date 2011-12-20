package stars.logging;

import stars.logging.LogEntry;

public interface LoggerI {
    /**
     * Log a message.
     * @param logEntry
     */
    public void log(LogEntry logEntry);
}
