package stars.physics.particles;

import stars.physics.Vector1x3;
import stars.physics.actions.IForce;

public interface IParticle extends Cloneable {
    // Properties
    public double mass();

    public Vector1x3 position();

    public Vector1x3 velocity();

    public void calculate(IParticle p, double tDelta);

    public void update(double time);

    public IForce[] getForces();

    public void markForDeletion();
}
