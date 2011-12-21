package stars.physics.particles;

import java.util.AbstractList;
import stars.physics.IParticle;
import stars.physics.Vector1x3;

public class NewtonParticle implements IParticle {
    public static final double G = 6.67e-11;
    
    private Vector1x3 tAccel = new Vector1x3(),
    	tVel = new Vector1x3(),
    	tPos = new Vector1x3();
    
    
    public Vector1x3 position;
    public Vector1x3 velocity;
    public Vector1x3 acceleration;
    public Vector1x3 force;
    public double mass;
    public double radius;
    public double elastisity = 1d;

    public NewtonParticle() {
        position = new Vector1x3();
        velocity = new Vector1x3();
        acceleration = new Vector1x3();
        force = new Vector1x3();
        mass = 1;
        radius = 0;
    }

    public Vector1x3 getPosition() { return position; }
    public Vector1x3 getVelocity() { return velocity; }
    public Vector1x3 getAcceleration() { return acceleration; }
    public Vector1x3 getForce() { return force; }
    public double getMass() { return mass; }
    public double getRadius() { return radius; }
    public double getElastisity() { return elastisity; }

/*
    public void setPosition(double x, double y, double z) { }
    public void setPosition(Vector3 v) { }
    public void setVelocity(double x, double y, double z) { }
    public void setVelocity(Vector3 v) { }
    public void setAcceleration(double x, double y, double z) { }
    public void setAcceleration(Vector3 v) { }
    public void setForce(double x, double y, double z) { }
    public void setForce(Vector3 v) { }
*/

    public void update(double d, AbstractList<IParticle> c) {
    	// Update Acceleration
    	// Calculate instantaneous accel from force
    	tAccel.set(force);
    	tAccel.scale(1/mass);
    	acceleration.add(tAccel);

    	// Update Velocity
    	tVel.set(acceleration);
    	tVel.scale(d);
    	velocity.add(tVel);

    	// Update Position
    	tPos.set(velocity);
    	tPos.scale(d);
    	position.add(tPos);
    }
    
    
    public void addForce(Vector1x3 v) {
        force.add(v);
    }
    
    /**
     * Calculates the time (seconds) until a collision with another particle
     * will occur.
     * 
     * @param p
     * @return
     */
    public double timeToCollision(IParticle p) {
//    	final Vector1x3 relVel = new Vector1x3();
//    	final Vector1x3 relPos = new Vector1x3();
//
//    	// Adjust so p's relative velocity is 0
//    	relVel.set(p.getVelocity());
//    	relVel.sub(getVelocity());
//    	double distance = getPosition().getDistance(p.getPosition());
//
//    	return distance / relativeSpeed;
    	return -1;
    }
    
    public void setMass(double d) { mass = d; }
    public void setRadius(double d ) { radius = d; }
    public void setForce(Vector1x3 v) { force.set(v); }
    public void setElastisity(double d) { elastisity = d; }
    
    public String toString() {
        return "mass = " + mass + " kg"
            +", p = " + position.toString() + " m"
            +", v = " + velocity.toString() + " m/s"           
            +", a = " + acceleration.toString() + " m/s^2"
            +", f = " + force.toString() + " N";
    }
}
