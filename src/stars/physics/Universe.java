package stars.physics;

import java.util.AbstractList;
import java.util.Vector;

import stars.physics.actions.IActionOld;
import stars.physics.particles.IParticleOld;

public class Universe implements Runnable {
    private Thread universeThread;

    protected AbstractList<IParticleOld> particles;
    protected AbstractList<IActionOld> actions;

    protected boolean running = false;
    protected double step = 0.001d;
    protected double totalTime = 0d;
    protected double boundRadius = 20000d;

    public Universe() {
        this(new Vector<IParticleOld>(), new Vector<IActionOld>());
    }

    /**
     * Construct the universe given a list of particles and actions to perform.
     * 
     * @param c1
     * @param c2
     */
    public Universe(AbstractList<IParticleOld> c1, AbstractList<IActionOld> c2) {
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
            IParticleOld p = particles.get(i);
            p.getForce().set(0, 0, 0);
        }

        for (int i = 0; i < actions.size(); i++) {
            IActionOld a = (IActionOld) actions.get(i);
            a.execute(step, particles);
        }

        for (int i = 0; i < particles.size(); i++) {
            IParticleOld p = particles.get(i);
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
        while (getRunning()) {
            step();
        }
    }

    // **** GET / SET METHODS **********************
    public void addAction(IActionOld action) {
        actions.add(action);
    }

    public void addParticle(IParticleOld particle) {
        particles.add(particle);
    }

    public void setParticles(AbstractList<IParticleOld> c) {
        particles = c;
    }

    public void setActions(AbstractList<IActionOld> c) {
        actions = c;
    }

    public void setStep(double d) {
        step = d;
    }

    public void setBoundRadius(double d) {
        boundRadius = d;
    }

    public AbstractList<IParticleOld> getParticles() {
        return particles;
    }

    public AbstractList<IActionOld> getActions() {
        return actions;
    }

    public double getStep() {
        return step;
    }

    public double getBoundRadius() {
        return boundRadius;
    }
    // **** END OF GET / SET METHODS ***************
}
