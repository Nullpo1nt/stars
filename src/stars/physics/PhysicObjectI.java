package stars.physics;

public interface PhysicObjectI {
    public double getMass();
    public void setMass(double mass);
    
    public Vector3 getVelocity();
    public void setVelocity(Vector3 velocity);
    public Vector3 getForce();
    public void setForce(Vector3 force);
    public Vector3 getAcceleration();
    public void setAcceleration(Vector3 acceleration);
    public Vector3 getPosition();
    public void setPosition(Vector3 position);
        
    public Vector3 calculateForce(NewtonObject s);
    public void    calculateForce(NewtonObjectList objects, int pos);
    public void    calculateAccel();
    public void    calculateVelocity(double seconds);
    public void    calculatePosition(double seconds);
}
