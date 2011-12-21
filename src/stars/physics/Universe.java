package stars.physics;

import java.util.*;

public class Universe implements Runnable {
	private Thread universeThread;
	protected AbstractList<IParticle> particles;
    protected AbstractList<IAction> actions;
    protected boolean running = false;
    protected double step = 0.001d;
    protected double totalTime = 0;
    protected double boundRadius = 20000;

    public Universe() {
        this(new Vector<IParticle>(), new Vector<IAction>());
    }

    /**
     * Construct the universe given a list of particles and 
     * actions to perform.
     * 
     * @param c1
     * @param c2
     */
    public Universe(AbstractList<IParticle> c1, AbstractList<IAction> c2) {
        particles = c1;
        actions = c2;
    }
    
    
    public synchronized void start() {
        running = true;
        universeThread = new Thread(this);
        universeThread.start();
    }
    
    public synchronized void step() {
        totalTime += getStep();
        
        for (int i = 0; i < particles.size(); i++) {
        	IParticle p = particles.get(i);
        	p.getForce().set(0,0,0);
        }
        
        for(int i = 0; i < actions.size(); i++) {
            IAction a = (IAction) actions.get(i);
            a.execute(step, particles);
        }
        
        for (int i = 0; i < particles.size(); i++) {
        	IParticle p = particles.get(i);
        	p.update(step, particles);
        }
    }
    
    public synchronized void stop() {
        running = false;
    }

    public synchronized boolean getRunning() {
        return running;
    }
    
    /**
     * 
     */
    public void run() {
        while(getRunning()) {
            step();
        }
    }
    
    //**** GET / SET METHODS **********************
    public void addAction(IAction action) { actions.add(action); }
    public void addParticle(IParticle particle) { particles.add(particle); }
    
    public void setParticles(AbstractList<IParticle> c) { particles = c; }
    public void setActions(AbstractList<IAction> c) { actions = c; }
    public void setStep(double d) { step = d; }
    public void setBoundRadius(double d) { boundRadius = d; }
    
    public AbstractList<IParticle> getParticles() { return particles; }
    public AbstractList<IAction> getActions() { return actions; }
    public double getStep() { return step; }
    public double getBoundRadius() { return boundRadius; }
    //**** END OF GET / SET METHODS ***************
}
