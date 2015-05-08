package stars.logging;

import java.util.ArrayList;

public class Logger {
    public final static String TARGETLOG_NONE = "test"; 
    private static Logger _instance = null;

    private LogEntry _le = new LogEntry();
    private ArrayList<ILogger> _loggers = new ArrayList<ILogger>();
    
    
    private Logger() { }
    
    
    public static synchronized Logger getInstance() {
        if (_instance == null) {
            _instance = new Logger();
        }

        return _instance;
    }
    
    public synchronized void addLogger(ILogger loggerPane) {
        _loggers.add(loggerPane);
    }
    
    public synchronized void removeLogger(ILogger loggerPane) {
        _loggers.remove(loggerPane);
    }
    
    public synchronized void log(String msg, String targetLib) {
        _le._setNewMessage(msg, targetLib);
        
        for (int x = 0; x < _loggers.size(); x++) {
            ILogger p = (ILogger)_loggers.get(x);
            
            if (p != null) {
                p.log(_le);
            }
        }
    }
    
    public void log(Exception ex, String targetLib) {
        log(ex.getMessage(), targetLib);
    }
    
    public void log(Throwable t, String targetLib) {
        log(t.getMessage(), targetLib);
    }
}
