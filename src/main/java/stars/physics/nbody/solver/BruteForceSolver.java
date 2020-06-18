package stars.physics.nbody.solver;

import java.util.AbstractList;

import stars.physics.particles.IParticle;

public class BruteForceSolver implements NBodySolver {

    @Override
    public void solve(AbstractList<IParticle> particleList, double timeDelta) {
        int length = particleList.size();

        for (int i = 0; i < length; i++) {
            IParticle p = particleList.get(i);

            update(particleList, p, i + 1, length);
            p.update(timeDelta);
        }
    }

    private void update(AbstractList<IParticle> particleList, IParticle p1,
            int startIndex, int length) {
        for (int i = startIndex; i < length; i++) {
            IParticle p2 = particleList.get(i);

            p1.calculateForces(p2);
            p2.calculateForces(p1);
        }
    }
}
