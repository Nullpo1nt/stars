package stars.physics2;

import java.util.*;

public class Universe {
    public static final double G = 6.67e-11;
    protected Collection<ParticleI> particles;
    protected Collection<ActionI> actions;
    protected boolean running = false;
    protected double step = 1;
    
    
    public void setParticles(Collection<ParticleI> c) { particles = c; }
    public Collection<ParticleI> getParticles() { return particles; }
    public void setActions(Collection<ActionI> c) { actions = c; }
    public Collection<ActionI> getActions() { return actions; }
    public void setStep(double d) { step += d; }
    public double getStep() { return step; }
    
    public void start() {
        running = true;
    }
    
    public void step() {
        Iterator i = actions.iterator();
        
        while(i.hasNext()) {
            ActionI a = (ActionI) i.next();
            a.execute(step, particles);
        }
    }
    
    public void stop() {
        running = false;        
    }
    
    public void run() {
        while(running) {
            step();
        }
    }
    
    public static void main(String[] args) {
        Universe p = new Universe();
        
        Vector<ParticleI> particles = new Vector<ParticleI>();
        particles.add(new OscillatingParticle(new Vector3()));
        p.setParticles(particles);
        
        Vector<ActionI> actions = new Vector<ActionI>();
        actions.add(new ParticleUpdateAction());
        p.setActions(actions);
        
        for(int i = 0; i < 360; i++) {
            p.step();
        }
    }
}
