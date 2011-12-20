package stars.logging.loggers;

import java.io.PrintStream;
import stars.logging.LoggerI;
import stars.logging.LogEntry;

public class PrintStreamLogger implements LoggerI {
    protected PrintStream _stream;
    
    public PrintStreamLogger(PrintStream stream) {
        _stream = stream;
    }
    
    public void log(LogEntry logEntry) {
        _stream.println(logEntry.getMessage());
    }
}
