package stars.physics;

import java.util.Vector;
import java.util.ArrayList;

public class NewtonObjectList {
    private Vector _data = new Vector();
    private ArrayList _addCache = new ArrayList(),
                      _remCache = new ArrayList();
    
    public synchronized void flush() {
        if (_addCache.size() > 0) {
            _data.addAll(_addCache);
            _addCache.clear();
        }
        if (_remCache.size() > 0) {
            _data.removeAll(_remCache);
            _remCache.clear();
        }
    }

    public Object get(int x) {
        return _data.get(x);
    }
    
    public synchronized int size() {
        return _data.size();
    }
    
    public synchronized boolean add(Object obj) {
        //Logger.getInstance().log("Object added: " + obj.toString(), Logger.TARGETLOG_NONE);
        _addCache.add(obj);
        return true;
    }

    public void logRemove(Object obj) {
        //Logger.getInstance().log("Object removed: " + obj.toString(), Logger.TARGETLOG_NONE);
    }
    
    public synchronized boolean remove(Object obj) {
        logRemove(obj);
        _remCache.add(obj);
        return true;
    }
    
    public synchronized Object remove(int x) {
        Object obj = _data.get(x);
        logRemove(obj);
        _remCache.add(obj);
        return obj;
    }
}
