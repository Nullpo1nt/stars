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
            }

            // Update velocity and position.
            p1.update(timeDelta);
        }
    }

    public static void main(String[] args) {
        ArrayList<IParticle> particles = new ArrayList<IParticle>();
        NBodySolver solver = new BruteForceSolver();

        particles.add(new TestParticle());
        particles.add(new TestParticle());

        for (long i = 0; i < 10; i++) {
            System.out.println("" + i
                    + "===============================================");
            solver.solve(particles, 1);
        }
    }
}
