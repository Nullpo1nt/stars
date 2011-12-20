package stars.physics2;

import java.util.Collection;
import java.util.Iterator;

public class PlanetParticle extends NewtonParticle {
    private Vector3 _unitVector = new Vector3();
    private Vector3 _distanceVector = new Vector3();
    
    /**
     * F = G * (m1 * m2) / d^2
     */
    protected Vector3 calculateForce(ParticleI p) {
        Vector3 pPosition = p.getPosition();
        Vector3 distanceV = getPosition().getDistanceVector(pPosition, _distanceVector);
        Vector3 unitV = distanceV.getUnitVector(_unitVector);
        double distance = distanceV.getMagnitude();
        double massProduct = getMass() * p.getMass();
        double force = (Universe.G * massProduct) / (distance * distance);
        
        unitV.scale(force);
        
        return unitV;
    }
    
    /**
     * 
     * @param listOfStars
     * @param pos
     */
    protected void calculateForce(Collection<ParticleI> c) {
        Vector3 tempForce = new Vector3();
        Iterator<ParticleI> i = c.iterator();
        
        while (i.hasNext()) {
            ParticleI p = i.next();
            Vector3 f = calculateForce(p);
            
            tempForce.add(f);
        }
        
        force.set(tempForce);
    }
    
    protected void calculateAcceleration() {
        acceleration.set(force);
        acceleration.scale(1 / mass);
    }
    
    protected void calculateVelocity(double timeDelta) {
        Vector3 tempAcceleration = new Vector3();
        
        tempAcceleration.set(acceleration);
        tempAcceleration.scale(timeDelta);
        velocity.add(tempAcceleration);
    }
    
    protected void calculatePosition(double timeDelta) {
        Vector3 tempVelocity = new Vector3();
        
        tempVelocity.set(velocity);
        tempVelocity.scale(timeDelta);
        velocity.add(tempVelocity);
    }
    
    public void update(double timeDelta, Collection<ParticleI> c) {
        calculateForce(c);
        calculateAcceleration();
        calculateVelocity(timeDelta);
        calculatePosition(timeDelta);
    }

    public String toString() {
        return "mass = " + mass + " kg"
            +", p = " + position.toString() + " m"
            +", v = " + velocity.toString() + " m/s"           
            +", a = " + acceleration.toString() + " m/s^2"
            +", f = " + force.toString() + " N";
    }
}
