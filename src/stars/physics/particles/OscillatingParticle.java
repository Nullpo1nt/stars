package stars.physics.particles;

import java.util.AbstractList;

import stars.physics.Vector1x3;

public class OscillatingParticle extends NewtonParticle {
    Vector1x3 orgPosition = new Vector1x3();
    double time = 0;
    
    public OscillatingParticle(Vector1x3 v) {
        orgPosition.set(v);
        position.set(v);
    }
    
    public void update(double seconds, AbstractList<IParticleOld> c) {
        time += seconds;
        position.x = orgPosition.x + (Math.sin(time/1000) * 5000);
        position.y += 1 * seconds;
    }
    
    public void setMass(double d) { }
    
    public Vector1x3 getPosition() {
        return position;
    }
}
