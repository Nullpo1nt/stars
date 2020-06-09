package stars.logging.loggers;

import java.io.PrintStream;
import stars.logging.ILogger;
import stars.logging.LogEntry;

public class PrintStreamLogger implements ILogger {
    protected PrintStream _stream;
    
    public PrintStreamLogger(PrintStream stream) {
        _stream = stream;
    }
    
    public void log(LogEntry logEntry) {
        _stream.println(logEntry.getMessage());
    }
}
