package stars.physics.forces;

import stars.math.Vector3;
import stars.physics.particles.IParticleState;

/**
 * Representation of a unit of force
 */
public interface IForce {
    public void reset();

    public Vector3 getAcceleration();

    public void calculate(IParticleState p1, IParticleState p2);
}
