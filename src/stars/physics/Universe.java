package stars.physics;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Vector;

import stars.physics.nbody.BruteForceSolver;
import stars.physics.nbody.NBodySolver;
import stars.physics.particles.IParticle;

public class Universe {
    /**
     * Gravitational constant.
     */
    public static final double G = 6.67e-11;
    
    protected AbstractList<IParticle> particles;
    private NBodySolver solver;
    
    protected double step = 0.00001d;
    protected double totalTime = 0d;
    
    protected boolean enforceBounds = false;
    
    protected double boundRadius = 1e6d;
    
    public Universe() {
        this(new Vector<IParticle>(), new BruteForceSolver());
    }
    
    /**
     * Construct the universe given a list of particles and actions to perform.
     * 
     * @param c1
     * @param c2
     */
    public Universe(AbstractList<IParticle> c1, NBodySolver s1) {
        particles = c1;
        
        solver = s1;
    }

    public void step() {
        double step = getStepSize();
        
        totalTime += step;
        solver.solve(particles, step);
        
        if (enforceBounds) {
            // TODO: Figure out life cycle management
            for (int i = 0; i < particles.size(); i++) {
                IParticle particle = particles.get(i);
                
                if (Math.abs(particle.position().getMagnitude()) > boundRadius) {
                    particle.markForDeletion();
                    particles.remove(particle);
                    System.out.println("Out of bounds, deleting:\n\t"+particle);
                    i--;
                    continue;
                }
            }
        }
        
        for (int i = 0; i < particles.size(); i++) {
            IParticle particle = particles.get(i);
            ArrayList<IParticle> col = particle.getCollisions();
            
            if (col.size() > 0) {
                for (IParticle p : col) {
                    particle.merge(p);
                    particles.remove(p);
                }
                
                col.clear();
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
    
    // **** END OF GET / SET METHODS ***************
}
