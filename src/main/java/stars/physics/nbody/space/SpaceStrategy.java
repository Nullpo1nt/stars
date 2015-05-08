package stars.physics.nbody.space;

import java.util.AbstractList;

import stars.physics.nbody.solver.NBodySolver;
import stars.physics.particles.IParticle;

public interface SpaceStrategy {
    public void processSpace(AbstractList<IParticle> particleList,
            double timeDelta, NBodySolver solver);
}
