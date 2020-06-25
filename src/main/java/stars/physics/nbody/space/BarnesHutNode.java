package stars.physics.nbody.space;

import java.util.AbstractList;
import java.util.ArrayList;

import stars.physics.CenterOfMass;
import stars.physics.particles.IParticle;

public class BarnesHutNode {
    public BarnesHutNode[] nodes = new BarnesHutNode[0];

    private Boundary boundary;

    public CenterOfMass centerOfMass;

    ArrayList<IParticle> particles = new ArrayList<>();

    public BarnesHutNode(Boundary b) {
        boundary = b;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    private void build(AbstractList<IParticle> source) {
        for (IParticle p : source) {
            if (boundary.contains(p.position())) {
                particles.add(p);
            }
        }

        if (particles.size() > 1) {
            centerOfMass = CenterOfMass.calculate(particles);
            nodes = new BarnesHutNode[8];

            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new BarnesHutNode(boundary.divide(i));
                nodes[i].build(particles);
            }
        }
    }

    public boolean isInternal() {
        return nodes.length != 0 || particles.size() > 1;
    }

    public boolean isExternal() {
        return (nodes.length == 0 && particles.size() <= 1);
    }

    public boolean isEmpty() {
        return particles.size() == 0;
    }

    public void solve(double targetTheta, IParticle p) {
        if (isExternal()) {
            for (IParticle ppp : particles) {
                if (ppp != p) {
                    p.calculateForces(ppp);
                }
            }
        } else {
            double theta = calculateTheta(p);

            if (theta < targetTheta) {
                p.calculateForces(new OctTreeParticle(centerOfMass.getMass(), centerOfMass));
            } else {
                for (BarnesHutNode n : nodes) {
                    n.solve(targetTheta, p);
                }
            }
        }
    }

    public double calculateTheta(IParticle p) {
        return boundary.getWidthX() / centerOfMass.getDistance(p.position());
    }

    public static BarnesHutNode build(AbstractList<IParticle> particles, double boundLow, double boundHigh) {
        BarnesHutNode node = new BarnesHutNode(new Boundary(
            boundLow, boundHigh, 
            boundLow, boundHigh, 
            boundLow, boundHigh));
        node.build(particles);
        return node;
    }
}