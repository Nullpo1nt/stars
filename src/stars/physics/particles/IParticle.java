package stars.physics.particles;

import stars.math.Vector3;

/**
 * A particle representation for a given position.
 */
public interface IParticle extends Cloneable {
    IParticleState getCurrentState();

    void setCurrentState(IParticleState state);

    // Properties
    Vector3 position();

    // Vector1x3 velocity();
    double radius();

    // Functions
    void calculateForces(IParticle p);

    void update(double time);

    void markForDeletion();

    boolean isMarkedForDeletion();

    void merge(IParticleState p);

    void randomize(double massBound, double positionBound, double velocityBound);
}
