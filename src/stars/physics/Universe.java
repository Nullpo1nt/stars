package stars.physics;

import java.util.AbstractList;
import java.util.Vector;

import stars.DrawableUniverse;
import stars.physics.nbody.BruteForceSolver;
import stars.physics.nbody.NBodySolver;
import stars.physics.particles.IParticle;
import stars.physics.particles.TestParticle;

public class Universe implements Runnable {
    /**
     * Gravitational constant.
     */
    public static final double G = 6.67e-11;
    
    private Thread universeThread;
    
    protected AbstractList<IParticle> particles;
    private NBodySolver solver;
    
    protected boolean running = false;
    protected double step = 0.00001d;
    protected double totalTime = 0d;
    protected double boundRadius = 20000d;
    
    public Universe() {
        this(new Vector<IParticle>());
    }
    
    /**
     * Construct the universe given a list of particles and actions to perform.
     * 
     * @param c1
     * @param c2
     */
    public Universe(AbstractList<IParticle> c1) {
        particles = c1;
        
        c1.add(new TestParticle());
        c1.add(new TestParticle());
        c1.add(new TestParticle());
        c1.add(new TestParticle());
        c1.add(new TestParticle());
        
        solver = new BruteForceSolver();
    }

    public synchronized void start() {
        running = true;
        universeThread = new Thread(this);
        universeThread.start();
    }
    
    public DrawableUniverse temp = null;

    public synchronized void step() {
        totalTime += getStep();
        solver.solve(particles, getStep());
        
        if (temp != null) {
            temp.step();
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
        while (getRunning()) {
            step();
        }
    }

    // **** GET / SET METHODS **********************

    public void addParticle(IParticle particle) {
        particles.add(particle);
    }

    public void setParticles(AbstractList<IParticle> c) {
        particles = c;
    }

    public void setStep(double d) {
        step = d;
    }

    public void setBoundRadius(double d) {
        boundRadius = d;
    }

    public AbstractList<IParticle> getParticles() {
        return particles;
    }

    public double getStep() {
        return step;
    }

    public double getBoundRadius() {
        return boundRadius;
    }
    
    public double getTotalTime() {
        return totalTime;
    }
    
    // **** END OF GET / SET METHODS ***************
}
