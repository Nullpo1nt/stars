package stars.physics.nbody.space;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import stars.math.Vector3;
import stars.physics.nbody.solver.NBodySolver;
import stars.physics.particles.IParticle;
import stars.physics.particles.IParticleState;

/**
 * Approximates space by dividing particles into an octal tree and solving for centeroid masses between leafs.
 */
public class TreeSpace implements SpaceStrategy {
    private static final OctTreeNode[] EMPTY_QUADS     = new OctTreeNode[8];

    private int                        maximumNodeSize = 50;

    private OctTreeNode                lastTree;

    public TreeSpace(int maximumNodeSize) {
        this.maximumNodeSize = maximumNodeSize;
    }

    private OctTreeNode createNode(AbstractList<IParticle> particles) {
        if (particles == null || particles.size() == 0) {
            return null;
        }

        Vector3 centeroid = calculateCenterOfMass(particles);
        double mass = calculateMass(particles);

        OctTreeNode[] children;

        if (particles.size() > maximumNodeSize) {
            children = divideParticles(particles, centeroid);
        } else {
            children = EMPTY_QUADS;
        }

        OctTreeNode node = new OctTreeNode(mass, centeroid, children, particles);

        for (OctTreeNode child : children) {
            if (child != null) {
                child.setParent(node);
            }
        }

        return node;
    }

    private OctTreeNode[] divideParticles(List<IParticle> particles,
            Vector3 centroid) {
        ArrayList<IParticle> p1 = new ArrayList<IParticle>();
        ArrayList<IParticle> p2 = new ArrayList<IParticle>();
        ArrayList<IParticle> p3 = new ArrayList<IParticle>();
        ArrayList<IParticle> p4 = new ArrayList<IParticle>();
        ArrayList<IParticle> p5 = new ArrayList<IParticle>();
        ArrayList<IParticle> p6 = new ArrayList<IParticle>();
        ArrayList<IParticle> p7 = new ArrayList<IParticle>();
        ArrayList<IParticle> p8 = new ArrayList<IParticle>();

        Iterator<IParticle> it = particles.iterator();

        while (it.hasNext()) {
            IParticle particle = it.next();
            IParticleState state = particle.getCurrentState();

            if (state.position().getX() <= centroid.getX()) {
                if (state.position().getY() <= centroid.getX()) {
                    if (state.position().getZ() <= centroid.getZ()) {
                        p1.add(particle);
                    } else {
                        p2.add(particle);
                    }
                } else {
                    if (state.position().getZ() <= centroid.getZ()) {
                        p3.add(particle);
                    } else {
                        p4.add(particle);
                    }
                }
            } else {
                if (state.position().getY() <= centroid.getX()) {
                    if (state.position().getZ() <= centroid.getZ()) {
                        p5.add(particle);
                    } else {
                        p6.add(particle);
                    }
                } else {
                    if (state.position().getZ() <= centroid.getZ()) {
                        p7.add(particle);
                    } else {
                        p8.add(particle);
                    }
                }
            }
        }

        OctTreeNode[] subNodes = new OctTreeNode[8];
        subNodes[0] = createNode(p1);
        subNodes[1] = createNode(p2);
        subNodes[2] = createNode(p3);
        subNodes[3] = createNode(p4);
        subNodes[4] = createNode(p5);
        subNodes[5] = createNode(p6);
        subNodes[6] = createNode(p7);
        subNodes[7] = createNode(p8);

        return subNodes;
    }

    private double calculateMass(List<IParticle> particles) {
        double mass = 0d;

        for (int i = 0; i < particles.size(); i++) {
            IParticle particle = particles.get(i);
            mass += particle.getCurrentState().mass();
        }

        return mass;
    }

    private Vector3 calculateCenterOfMass(List<IParticle> particles) {
        double mx = 0d;
        double my = 0d;
        double mz = 0d;
        double mtotal = 0d;

        for (int i = 0; i < particles.size(); i++) {
            IParticleState p = particles.get(i).getCurrentState();

            mtotal += p.mass();
            mx += (p.mass() * p.position().getX());
            my += (p.mass() * p.position().getY());
            mz += (p.mass() * p.position().getZ());
        }

        return new Vector3((mx / mtotal), (my / mtotal), (mz / mtotal));
    }

    private void addVirtualParticles(AbstractList<IParticle> particles,
            OctTreeNode parent, OctTreeNode node) {

        if (parent != null) {
            for (OctTreeNode child : parent._quaduarants) {
                if (child != null && child != node) {
                    particles.add(child.getNodeParticle());
                }
            }

            addVirtualParticles(particles, parent.getParent(), node.getParent());
        }
    }

    private void walkNode(OctTreeNode node, double timeDelta, NBodySolver solver) {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            AbstractList<IParticle> particles = new ArrayList<>(node.getParticles());

            addVirtualParticles(particles, node.getParent(), node);

            solver.solve(particles, timeDelta);
        } else {
            for (OctTreeNode subNode : node._quaduarants) {
                if (subNode != null) {
                    walkNode(subNode, timeDelta, solver);
                }
            }
        }
    }

    @Override
    public void processSpace(AbstractList<IParticle> particleList,
            double timeDelta, NBodySolver solver) {
        OctTreeNode node = createNode(particleList);

        walkNode(node, timeDelta, solver);

        lastTree = node;
    }

    public OctTreeNode getNode() {
        return lastTree;
    }

}
