package stars.ui;

import java.text.SimpleDateFormat;
import javax.swing.JTextArea;
import stars.logging.ILogger;
import stars.logging.LogEntry;

@SuppressWarnings("serial")
public class LoggerPane extends JTextArea implements ILogger {
    protected SimpleDateFormat sdf = new SimpleDateFormat("yy/dd/MM HH:mm:ss");
    protected int _maxLines = 50;


    public LoggerPane() {
        this.setName("Log");
    }

    
    public void setMaxLines(int maxLines) { _maxLines = maxLines; }
    public int getMaxLines() { return _maxLines; }

    public void log(LogEntry logEntry) {
        if (this.getLineCount() > _maxLines) {
            this.setText("");
        }
        
        this.append(logEntry.toString()+"\n");
        this.setCaretPosition(this.getText().length());
    }
}
