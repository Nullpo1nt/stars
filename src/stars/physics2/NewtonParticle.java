package stars.physics2;

import java.util.Collection;

public abstract class NewtonParticle implements ParticleI {
    Vector3 position;
    Vector3 velocity;
    Vector3 acceleration;
    Vector3 force;
    double mass;

    public Vector3 getAcceleration() {
        return acceleration;
    }

    public Vector3 getForce() {
        return force;
    }

    public double getMass() {
        return mass;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public abstract void update(double timeDelta, Collection<ParticleI> c);
}
