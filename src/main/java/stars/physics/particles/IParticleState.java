package stars.physics.particles;

import stars.math.Vector3;

/**
 * Represents a particle state for an instantaneous point in time.
 */
public interface IParticleState {
    long particleId();

    double mass();

    double radius();

    Vector3 position();

    Vector3 velocity();

    Vector3 acceleration();

    IParticleState merge(IParticleState state);
}
