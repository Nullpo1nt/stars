package stars.physics.particles;

import java.util.AbstractList;
import stars.physics.Vector1x3;

public class PlanetParticle extends NewtonParticle {
    private Vector1x3 _unitVector = new Vector1x3();
    private Vector1x3 _distanceVector = new Vector1x3();
    
    /**
     * F = G * (m1 * m2) / d^2
     */
    protected Vector1x3 calculateForce(IParticleOld p) {
        Vector1x3 pPosition = p.getPosition();
        Vector1x3 distanceV = getPosition().getDistanceVector(pPosition, _distanceVector);
        Vector1x3 unitV = distanceV.getUnitVector(_unitVector);
        double distance = distanceV.getMagnitude();
        double massProduct = getMass() * p.getMass();
        double force = (NewtonParticle.G * massProduct) / (distance * distance);
        
        unitV.scale(force);
        
        return unitV;
    }
    
    /**
     * 
     * @param listOfStars
     * @param pos
     */
    protected void calculateForce(AbstractList<IParticleOld> c) {
        Vector1x3 tempForce = new Vector1x3();

        for (int i = 0; i < c.size(); i++) {
            IParticleOld p = c.get(i);

            if (p != this) {
                if (p.getMass() > 0) {
                    Vector1x3 f = calculateForce(p);
            
                    tempForce.add(f);
                }
            }
        }
        
        force.set(tempForce);
    }
    
    protected void calculateAcceleration() {
        acceleration.set(force);
        acceleration.scale(1 / mass);
    }
    
    protected void calculateVelocity(double timeDelta) {
        Vector1x3 tempAcceleration = new Vector1x3();
        
        tempAcceleration.set(acceleration);
        tempAcceleration.scale(timeDelta);
        velocity.add(tempAcceleration);
    }
    
    protected void calculatePosition(double timeDelta) {
        Vector1x3 tempVelocity = new Vector1x3();
        
        tempVelocity.set(velocity);
        tempVelocity.scale(timeDelta);
        position.add(tempVelocity);
    }
    
    public void update(double timeDelta, AbstractList<IParticleOld> c) {
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
