package stars.physics.nbody.space;

import java.util.AbstractList;

import stars.physics.nbody.solver.NBodySolver;
import stars.physics.particles.IParticle;

/**
 * Approximates space using the Barnes Hut algorithm
 */
public class BarnesHut implements SpaceStrategy {

    private double theta;

    private BarnesHutNode lastTree;

    public BarnesHut(double theta) {
        this.theta = theta;
    }

    @Override
    public void processSpace(AbstractList<IParticle> particleList,
            double timeDelta, NBodySolver solver) {
        BarnesHutNode root = BarnesHutNode.build(particleList, -1e15d, 1e15d);

        for (IParticle p : particleList) {
            root.solve(theta, p);
        }

        for (IParticle p : particleList) {
            p.update(timeDelta);
        }

        lastTree = root;
    }

    public BarnesHutNode getNode() {
        return lastTree;
    }

}
