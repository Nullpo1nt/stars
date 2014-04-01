package stars.physics.particles;

import java.util.AbstractList;


public class Blackhole extends NewtonParticle {
    public Blackhole(double mass) {
        super();
        
        super.setMass(mass);
    }
    
    public void update(double timeDelta, AbstractList<IParticleOld> c) {  }
    public void setMass(double d) { }
    public void setRadius(double d) { }
}
