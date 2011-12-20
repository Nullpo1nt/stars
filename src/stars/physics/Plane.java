package stars.physics;

public class Plane implements Runnable {
    public static final double G = 6.67e-11;
    
    protected NewtonObjectList objects = null;
    
    protected boolean _running = true;
    protected double _boundRadius = 10000, // Cutoff distance
                     _boundDiameter = 20000,
                     _timeStep = 0.1,  // Time (seconds) between each step
                     _totalTime = 0;
    protected int _targetObjectCount = 0;
    
    protected Vector3 maxP = new Vector3(),
                      maxV = new Vector3(),
                      maxA = new Vector3(),
                      maxF = new Vector3();
    
    
    public Plane(NewtonObjectList list) {
        objects = list;
    }
    
    
    public void setRunning(boolean running) { _running = running; }
    public boolean getRunning() { return _running; }
    public void setBoundRadius(double boundRadius) { 
        _boundRadius = boundRadius;
        _boundDiameter = 2 * boundRadius;
    }
    public double getBoundRadius() { return _boundRadius; }
    public double getBoundDiameter() { return _boundDiameter; }
    public void setTimeStep(double timeStep) { _timeStep = timeStep; }
    public double getTimeStep() { return _timeStep; }
    public double getTotalTime() { return _totalTime; }
    public void setTargetObjectCount(int targetObjectCount) { _targetObjectCount = targetObjectCount; }
    public int getTargetObjectCount() { return _targetObjectCount; }
    
    public void run() {
        while(_running) {
            step();
        }
    }
    
    public void step() {
        NewtonObject anObject = null;

        // Add random objects if less than target count
        if (objects.size() < _targetObjectCount) {
            addRandom();
        }
        
        // Could cause problems with multi-threading
        int size = objects.size();
        
        for(int x = 0; x < size; x++) {
            anObject = (NewtonObject) objects.get(x);
            
            if (anObject != null) {
                anObject.update(_timeStep, objects, x);
                
                if (anObject.position.getMagnitude() > _boundRadius)
                    objects.remove(x);
                
                maxP.x = (Math.abs(anObject.position.x) > maxP.x) ? Math.abs(anObject.position.x) : maxP.x;
                maxP.y = (Math.abs(anObject.position.y) > maxP.y) ? Math.abs(anObject.position.y) : maxP.y;
                maxP.z = (Math.abs(anObject.position.z) > maxP.z) ? Math.abs(anObject.position.z) : maxP.z;
                
                maxV.x = (Math.abs(anObject.velocity.x) > maxV.x) ? Math.abs(anObject.velocity.x) : maxV.x;
                maxV.y = (Math.abs(anObject.velocity.y) > maxV.y) ? Math.abs(anObject.velocity.y) : maxV.y;
                maxV.z = (Math.abs(anObject.velocity.z) > maxV.z) ? Math.abs(anObject.velocity.z) : maxV.z;
                
                maxA.x = (Math.abs(anObject.accel.x) > maxA.x) ? Math.abs(anObject.accel.x) : maxA.x;
                maxA.y = (Math.abs(anObject.accel.y) > maxA.y) ? Math.abs(anObject.accel.y) : maxA.y;
                maxA.z = (Math.abs(anObject.accel.z) > maxA.z) ? Math.abs(anObject.accel.z) : maxA.z;
                
                maxF.x = (Math.abs(anObject.force.x) > maxF.x) ? Math.abs(anObject.force.x) : maxF.x;
                maxF.y = (Math.abs(anObject.force.y) > maxF.y) ? Math.abs(anObject.force.y) : maxF.y;
                maxF.z = (Math.abs(anObject.force.z) > maxF.z) ? Math.abs(anObject.force.z) : maxF.z;
            }
        }
        
        _totalTime += _timeStep;
        objects.flush();
    }
    
    public void addRandom() {

    }
}
