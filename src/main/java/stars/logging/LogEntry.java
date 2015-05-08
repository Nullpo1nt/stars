package stars.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {
    protected SimpleDateFormat df = new SimpleDateFormat("[yy/MM/dd HH:mm:ss]"); 
    protected String _message = "";
    protected String _targetLog = "";
    protected String _timeStampString = "";
    protected Date   _timeStamp = new Date();
    
    protected void _setNewMessage(String message, String targetLog) {
        _message = message;
        _targetLog = targetLog;
        _timeStamp = new Date();
        _timeStampString = df.format(_timeStamp);
    }
    
    public String getMessage() { return _message; }
    public String getTargetLog() { return _targetLog; }
    public String getTimeStampString() { return _timeStampString; }
    public Date getTimeStamp() { return _timeStamp; }

    public String toString() {
        return _timeStampString + " " + _targetLog + "  " + _message; 
    }
}
