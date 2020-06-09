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

            // FIXME Collision detection doesn't belong here...
//            double dis = Math.abs(p1.position().getDistance(p2.position()));
//
//            if (dis <= p1.radius() || dis <= p2.radius()) {
//                // p1.addCollision(p2);
//                System.out.println("Collision between:\n\tP1 " + p1 + "\n\tP2 "
//                        + p2);
//                System.out.println(dis);
//            }
        }
    }
}
