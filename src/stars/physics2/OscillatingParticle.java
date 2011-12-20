package stars.physics2;

import java.util.Collection;

public class OscillatingParticle implements ParticleI {
    Vector3 orgPosition = new Vector3();
    Vector3 position = new Vector3();
    double time = 0;
    
    public OscillatingParticle(Vector3 v) {
        orgPosition.set(v);
        position.set(v);
    }
    
    public void update(double seconds, Collection<ParticleI> c) {
        time += seconds;
        position.x = orgPosition.x + Math.sin(time);
        position.y += 1 * seconds;
        
        System.out.println(position.toString());
    }
    
    public Vector3 getPosition() {
        return position;
    }
    
    public Vector3 getVelocity() { return null; }
    public Vector3 getAcceleration() { return null; }
    public Vector3 getForce() { return null; }
    public double getMass() { return 0; }
}
