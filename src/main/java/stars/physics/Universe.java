package stars.physics;

import java.util.AbstractList;
import java.util.Vector;

import stars.math.Tuple3;
import stars.math.Vector3;
import stars.physics.nbody.solver.BruteForceSolver;
import stars.physics.nbody.solver.NBodySolver;
import stars.physics.nbody.space.SpaceStrategy;
import stars.physics.nbody.space.TreeSpace;
import stars.physics.particles.IParticle;
import stars.physics.particles.IParticleState;

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

    public Tuple3                     centerOfMass  = new Vector3();

    public Universe() {
        this(new Vector<IParticle>(), new TreeSpace(50), new BruteForceSolver());
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

            // ArrayList<IParticle> col = particle.getCollisions();
            //
            // if (col.size() > 0) {
            // for (IParticle p : col) {
            // particle.merge(p.getCurrentState());
            // particles.remove(p);
            // }
            //
            // col.clear();
            // }
        }

        centerOfMass = calculateCenterOfMass(particles);
    }

    private Tuple3 calculateCenterOfMass(AbstractList<IParticle> particles) {
        double mx = 0d;
        double my = 0d;
        double mz = 0d;
        double mtotal = 0d;

        for (int i = 0; i < particles.size(); i++) {
            IParticleState p = particles.get(i).getCurrentState();

            mtotal += p.mass();
            mx += (p.mass() * p.position().getX());
            my += (p.mass() * p.position().getY());
            mz += (p.mass() * p.position().getZ());
        }

        return new Vector3((mx / mtotal), (my / mtotal), (mz / mtotal));
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
