package stars.physics.nbody.space;

import stars.math.Vector3;
import stars.physics.particles.IParticle;
import stars.physics.particles.IParticleState;
import stars.physics.particles.ParticleState;

public class OctTreeParticle implements IParticle {
    ParticleState state;

    public OctTreeParticle(double mass, Vector3 position) {
        state = new ParticleState(-1L, -1L, mass, 1d, position, new Vector3(),
                new Vector3());
    }

    @Override
    public IParticleState getCurrentState() {
        return state;
    }

    @Override
    public void setCurrentState(IParticleState state) {
    }

    @Override
    public Vector3 position() {
        return state.position();
    }

    @Override
    public double radius() {
        return state.radius();
    }

    @Override
    public void calculateForces(IParticle p) {
    }

    @Override
    public void update(double time) {
    }

    @Override
    public void markForDeletion() {
    }

    @Override
    public boolean isMarkedForDeletion() {
        return false;
    }

    @Override
    public void merge(IParticleState p) {
    }

    @Override
    public void randomize(double massBound, double positionBound,
            double velocityBound) {
    }

}
