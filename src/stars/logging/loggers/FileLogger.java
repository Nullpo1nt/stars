package stars.logging.loggers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import stars.logging.ILogger;
import stars.logging.LogEntry;
import stars.logging.Logger;

public class FileLogger implements ILogger {
    protected String _newline; 
    protected Hashtable<String, BufferedWriter> _outputBuffers = new Hashtable<String, BufferedWriter>();
    protected File _outputDirectory;
    protected boolean _append = true;
    
    
    public FileLogger(String outputDirectory) {
        this(new File(outputDirectory));
    }
    
    public FileLogger(File outputDirectory) {
        setOutputDirectory(outputDirectory);
        //_newline = System.getProperty("");
        _newline = "\n";
    }
    
    
    public void setOutputDirectory(File outputDirectory) {
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
        
        if (!outputDirectory.isDirectory()) {
            outputDirectory = outputDirectory.getParentFile();
        }
        
        _outputDirectory = outputDirectory;
    }
    
    
    protected BufferedWriter getBuffer(String bufferName) {
        BufferedWriter buffer = (BufferedWriter)_outputBuffers.get(bufferName);
        
        if (buffer == null) {
            //Logger.getInstance().log("Testing", "");
            System.out.println("Creating buffer for " + bufferName);
            try {
                File outputFile = new File(_outputDirectory, bufferName + ".log");
                
                buffer = new BufferedWriter(new FileWriter(outputFile, _append));
                buffer.write("-----------------------------------------------------------" + _newline);

                System.out.println("Output file: " + outputFile.getAbsolutePath());
                _outputBuffers.put(bufferName, buffer);
            } catch(IOException ex) {
                //Logger.getInstance().log(ex, Logger.TARGETLOG_NONE);
                ex.printStackTrace();
                return null;
            }
        }
        
        return buffer;
    }
    
    public void close() {
        Logger.getInstance().removeLogger(this);
        
        Iterator<BufferedWriter> i = _outputBuffers.values().iterator();
        
        while (i.hasNext()) {
            BufferedWriter buffer = i.next();
            
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException ex) {
                    //Logger.getInstance().log(ex);
                    ex.printStackTrace();
                }
            }
        }
        
        _outputBuffers.clear();
    }
    
    public void log(LogEntry logEntry) {
        BufferedWriter out = getBuffer(logEntry.getTargetLog());
        
        if (out != null) {
            try {
                out.write(logEntry.toString() + _newline);
                out.flush();
            } catch(IOException ex) {
                //Logger.getInstance().log(ex, Logger.TARGETLOG_NONE);
                ex.printStackTrace();
            }
        }
    }
}
