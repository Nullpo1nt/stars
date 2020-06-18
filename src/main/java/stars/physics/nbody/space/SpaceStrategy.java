package stars.physics.nbody.space;

import java.util.AbstractList;

import stars.physics.nbody.solver.NBodySolver;
import stars.physics.particles.IParticle;

/**
 * Space strategy, defines a method of dividing up and solving a list of particles.
 */
public interface SpaceStrategy {
    public void processSpace(AbstractList<IParticle> particleList,
            double timeDelta, NBodySolver solver);
}
