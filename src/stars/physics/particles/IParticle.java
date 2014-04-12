package stars.physics.particles;

import java.util.ArrayList;

import stars.physics.Vector1x3;
import stars.physics.actions.IForce;

public interface IParticle extends Cloneable {
    // Properties
    public double mass();

    public Vector1x3 position();

    public Vector1x3 velocity();
    
    public double radius();

    // Functions
    public void calculate(IParticle p, double tDelta);

    public void update(double time);

    public IForce[] getForces();

    public void markForDeletion();
    public boolean isMarkedForDeletion();
    
    public void addCollision(IParticle p);
    public ArrayList<IParticle> getCollisions();
    public void merge(IParticle p);
    
    public void randomize(double massBound, double positionBound, double velocityBound);
}
