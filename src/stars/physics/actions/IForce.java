package stars.physics.actions;

import stars.physics.Vector1x3;
import stars.physics.particles.IParticle;

public interface IForce {
    public void resetForce();

    public Vector1x3 getForce();

    public void calculate(IParticle p1, IParticle p2, double tDelta);
}
