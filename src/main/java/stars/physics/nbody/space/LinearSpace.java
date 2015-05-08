package stars.physics.nbody.space;

import java.util.AbstractList;

import stars.physics.nbody.solver.NBodySolver;
import stars.physics.particles.IParticle;

/**
 * Linear space present the whole universe as a single list.
 */
public class LinearSpace implements SpaceStrategy {

    @Override
    public void processSpace(AbstractList<IParticle> particleList,
            double timeDelta, NBodySolver solver) {
        solver.solve(particleList, timeDelta);
    }

}
