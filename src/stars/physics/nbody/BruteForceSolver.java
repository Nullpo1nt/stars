package stars.physics.nbody;

import java.util.AbstractList;
import java.util.ArrayList;

import stars.physics.particles.IParticle;
import stars.physics.particles.TestParticle;

public class BruteForceSolver implements NBodySolver {

    @Override
    public void solve(AbstractList<IParticle> initialParticleStates,
            double timeDelta) {
        int length = initialParticleStates.size();

        for (int i = 0; i < length; i++) {
            IParticle p1 = initialParticleStates.get(i);

            // Calculate forces
            for (int j = i + 1; j < length; j++) {
                IParticle p2 = initialParticleStates.get(j);

                p1.calculate(p2, timeDelta);
                p2.calculate(p1, timeDelta);
                
                // FIXME Collision detection doesn't belong here...
                double dis = Math.abs(p1.position().getDistance(p2.position()));
                
                if (dis <= p1.radius() || dis <= p2.radius()) {
                    p1.addCollision(p2);
                    System.out.println("Collision between:\n\tP1 " + p1 + "\n\tP2 " + p2);
                    System.out.println(dis);
                }
            }

            // Update velocity and position.
            p1.update(timeDelta);
        }
    }
}
