package stars.physics.particles;

import java.util.AbstractList;

import stars.physics.IParticle;

public class Blackhole extends NewtonParticle {
    public Blackhole(double mass) {
        super();
        
        super.setMass(mass);
    }
    
    public void update(double timeDelta, AbstractList<IParticle> c) {  }
    public void setMass(double d) { }
    public void setRadius(double d) { }
}
