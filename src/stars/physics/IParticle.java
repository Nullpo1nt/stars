package stars.physics;

import java.util.AbstractList;

public interface IParticle {
    public void update(double timeDelta, AbstractList<IParticle> c);
    
    public void addForce(Vector1x3 v);
    
    //public void setRandom();

    public void setMass(double d);
    public void setRadius(double d);
    public void setForce(Vector1x3 v);
    public void setElastisity(double d);

    public Vector1x3 getPosition();
    public Vector1x3 getVelocity();
    public Vector1x3 getAcceleration();
    public Vector1x3 getForce();
    public double getMass();
    public double getRadius();
    public double getElastisity();
}
