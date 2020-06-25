package stars.physics;

import java.util.AbstractList;
import java.util.Vector;

import stars.physics.nbody.solver.BruteForceSolver;
import stars.physics.nbody.solver.NBodySolver;
import stars.physics.nbody.space.BarnesHut;
import stars.physics.nbody.space.SpaceStrategy;
import stars.physics.particles.IParticle;

public class Universe {
    /**
     * Gravitational constant.
     */
    public static final double        G             = 6.67e-11;

    protected AbstractList<IParticle> particles;
    private SpaceStrategy             space;
    private NBodySolver               solver;

    protected double                  step          = 0.00001d;
    protected double                  totalTime     = 0d;

    protected boolean                 enforceBounds = true;

    protected double                  boundRadius   = 1e8d;

    public Universe() {
        this(new Vector<IParticle>(), new BarnesHut(50), new BruteForceSolver());
    }

    /**
     * Construct the universe given a list of particles and actions to perform.
     * 
     * @param c1
     * @param c2
     */
    public Universe(AbstractList<IParticle> c1, SpaceStrategy sp1,
            NBodySolver s1) {
        particles = c1;
        space = sp1;
        solver = s1;
    }

    public void step() {
        double step = getStepSize();

        totalTime += step;

        space.processSpace(particles, step, solver);

        for (int i = 0; i < particles.size(); i++) {
            IParticle particle = particles.get(i);

            if (enforceBounds) {
                if (Math.abs(particle.position().getMagnitude()) > boundRadius) {
                    particle.markForDeletion();
                    particles.remove(particle);
                    System.out.println("Ejected:\n\t" + particle);
                    i--;
                    continue;
                }
            }
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

    public double getStepSize() {
        return step;
    }

    public double getBoundRadius() {
        return boundRadius;
    }

    public double getTotalTime() {
        return totalTime;
    }
    
    public SpaceStrategy getSpace() {
        return space;
    }

    // **** END OF GET / SET METHODS ***************
}
