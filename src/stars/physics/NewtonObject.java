package stars.physics;

import stars.logging.Logger;

public class NewtonObject implements PhysicObjectI {
    // Temporary vectors declared globablly so we don't have to contruct them continously
    private Vector3 _tempForce    = new Vector3(),
                    _tempAccel    = new Vector3(),
                    _tempVelocity = new Vector3(),
                    _distance     = new Vector3(),
                    _unitVector   = new Vector3();
    
    protected boolean _collided = false;
    protected NewtonObject cloneObject = null;
    protected Logger logger = Logger.getInstance();
    protected double _massScale = 1,
                     _radius = 1;

    // Object physical properties
    public double  mass;                   
    public Vector3 position = new Vector3(),
                   velocity = new Vector3(),
                   force    = new Vector3(),
                   accel    = new Vector3();
        
    
    public NewtonObject() { 
        this(1);
    }
    
    public NewtonObject(double mass) {
        this(mass, 1);
    }
    
    public NewtonObject(double mass, double radius) {
        this(mass, radius, 
             new Vector3(), new Vector3(), new Vector3(), new Vector3());
    }
    
    public NewtonObject(double mass, double radius, 
            Vector3 position, Vector3 velocity, Vector3 force, Vector3 accel) {
        setMass(mass);
        _radius = radius;
        this.position = position;
        this.velocity = velocity;
        this.force = force;
        this.accel = accel;
    }
    
    public NewtonObject(NewtonObject object) {
        this._collided = object._collided;
        this.cloneObject = object.cloneObject;
        this.logger = object.logger;
        this._massScale = object._massScale;
        this.mass = object.mass;
        this._radius = object._radius;
        
        this._tempForce    = (Vector3)object._tempForce.clone();
        this._tempAccel    = (Vector3)object._tempAccel.clone();
        this._tempVelocity = (Vector3)object._tempVelocity.clone();
        this._distance     = (Vector3)object._distance.clone();
        this._unitVector   = (Vector3)object._unitVector.clone();
        this.position = (Vector3)object.position.clone();
        this.velocity = (Vector3)object.velocity.clone();
        this.force    = (Vector3)object.force.clone();
        this.accel    = (Vector3)object.accel.clone();
    }

    
    
    public Vector3 getTempForce() {
        return _tempForce;
    }
    
    public void setMass(double mass) {
        this.mass = (mass <= 0) ? 1 : mass;
        _massScale = 1 / mass;
    }
    public double getMass() { return mass; }
    
    public Vector3 getVelocity() { return velocity; }
    public void setVelocity(Vector3 velocity) { this.velocity.set(velocity); }
    public Vector3 getForce() { return force; }
    public void setForce(Vector3 force) { this.force.set(force); }
    public Vector3 getAcceleration() { return accel; }
    public void setAcceleration(Vector3 acceleration) { this.accel.set(acceleration); }
    public Vector3 getPosition() { return position; }
    public void setPosition(Vector3 position) { this.position.set(position); }
    
    public void setRadius(double radius) { _radius = radius; }
    public double getRadius() { return _radius; }
    
    public void setPosition(double x, double y, double z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }
    
    public void setVelocity(double x, double y, double z) {
        velocity.x = x;
        velocity.y = y;
        velocity.z = z;
    }
    
    public void setCollided(boolean b) {
        _collided = b;
    }
    
    public void setMembers(NewtonObject obj) {
        setMass(obj.getMass());
        setRadius(obj.getRadius());
        velocity = obj.velocity;
    }
    
    
    public void handleCollision(NewtonObject s) {
        if (cloneObject == null) {
            cloneObject = (NewtonObject)this.clone();
        }
        
        //System.out.println("---------------");
        cloneObject.velocity.scale(cloneObject.getMass());
        //System.out.println(cloneObject.velocity);
        Vector3 momentum = (Vector3)s.velocity.clone();
        momentum.scale(s.getMass());
        //System.out.println(momentum);
        cloneObject.velocity.add(momentum);
        //System.out.println(cloneObject.velocity);
        cloneObject.setMass(cloneObject.getMass() + s.getMass());
        
        cloneObject.velocity.scale(1/cloneObject.getMass());


        // Add to radius amount of area...
        double area = (_radius * _radius * Math.PI) +
                      (s._radius * s._radius * Math.PI);
        cloneObject.setRadius(Math.sqrt(area / Math.PI));  
    }
    
    /**
     * F = G * (m1 * m2) / d^2
     * @param s
     * @return
     */
    public Vector3 calculateForce(NewtonObject s) {
        Vector3 dVec = position.getDistanceVector(s.position, _distance),
                uVec = dVec.getUnitVector(_unitVector);
        double dis  = dVec.getMagnitude(),
               massProduct  = mass * s.mass;  // m1 * m2
        
        if (dis <= _radius + s._radius) {
            logger.log("Collision\n" + this.toString() + "\n" +s.toString(), Logger.TARGETLOG_NONE);
            
            s.setCollided(true);
            
            handleCollision(s);
        }
        
        dis = dis * dis;  // d^2
        massProduct = massProduct / dis;  // (m1*m2) / d^2
        massProduct = Plane.G * massProduct;        
        uVec.scale(massProduct);
        
        return uVec;
    }
    
    /**
     * 
     * @param listOfStars
     * @param pos
     */
    public void calculateForce(NewtonObjectList listOfStars, int pos) {
        Vector3 f = null;
        
        for (int x = pos, size = listOfStars.size(); x < size; x++) {
            NewtonObject s = (NewtonObject)listOfStars.get(x);
            if (s != null && s != this) {
                f = calculateForce(s);
                
                if(s._collided) {
                    listOfStars.remove(s);           
                } else {
                    _tempForce.add(f);

                    f.scale(-1);
                    s._tempForce.add(f);
                }
            }
        }
        
        if (cloneObject != null) {
            setMembers(cloneObject);
            cloneObject = null;
        }
        
        force.set(_tempForce);
        _tempForce.set(0,0,0);
    }
    
    /**
     * a = F / m
     */
    public void calculateAccel() {
        //double massScale = 1 / mass;
        accel.set(force);
        accel.scale(_massScale);
    }
    
    /**
     * v = v1 + (a*t)
     * @param seconds
     */
    public void calculateVelocity(double seconds) {
        _tempAccel.set(accel);        
        _tempAccel.scale(seconds);
        velocity.add(_tempAccel);
    }
    
    /**
     * p = p1 + (v*t)
     * @param seconds
     */
    public void calculatePosition(double seconds) {
        _tempVelocity.set(velocity);        
        _tempVelocity.scale(seconds);
        position.add(_tempVelocity);
    }
    
    /**
     * Calculate the gravitational force from objects in the Collection
     * and update the acceleration, velocity, and position vectors. 
     * 
     * @param seconds
     * @param objects
     * @param listPosition
     */
    public void update(double seconds, NewtonObjectList objects, int listPosition) {
        calculateForce(objects, listPosition);
        calculateAccel();
        calculateVelocity(seconds);
        calculatePosition(seconds);
    }
    
    /**
     * Clone this object.
     */
    public Object clone() {
        return new NewtonObject(this);
    }
    
    public String toString() {
        return "mass = " + mass + " kg"
            +", p = " + position.toString() + " m"
            +", v = " + velocity.toString() + " m/s"           
            +", a = " + accel.toString() + " m/s^2"
            +", f = " + force.toString() + " N";
    }
}
