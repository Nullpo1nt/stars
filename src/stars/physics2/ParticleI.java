package stars.physics2;

import java.util.Collection;

public interface ParticleI {
    public void update(double timeDelta, Collection<ParticleI> c);
    
    public Vector3 getPosition();
    public Vector3 getVelocity();
    public Vector3 getAcceleration();
    public Vector3 getForce();
    public double getMass();
}
