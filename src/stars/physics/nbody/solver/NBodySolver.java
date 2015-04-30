package stars.physics.nbody.solver;

import java.util.AbstractList;

import stars.physics.particles.IParticle;

public interface NBodySolver {
    public void solve(AbstractList<IParticle> initialParticleStates,
            double timeDelta);
}
