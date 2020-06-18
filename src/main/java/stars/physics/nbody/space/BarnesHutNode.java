package stars.physics.nbody.space;

import java.util.AbstractList;
import java.util.ArrayList;

import stars.math.Vector3;
import stars.physics.particles.IParticle;

public class BarnesHutNode {
    public BarnesHutNode[] nodes = new BarnesHutNode[0];

    private Boundary boundary;

    public Vector3 centerOfMass = new Vector3();
    private double mass = 0d;

    ArrayList<IParticle> particles = new ArrayList<>();

    public BarnesHutNode(Boundary b) {
        boundary = b;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    private void build(AbstractList<IParticle> source) {
        double mx = 0;
        double my = 0;
        double mz = 0;

        for (IParticle p : source) {
            Vector3 pos = p.getCurrentState().position();

            if (boundary.contains(pos)) {
                particles.add(p);

                mass += p.getCurrentState().mass();
                mx += (p.position().getX() * p.getCurrentState().mass());
                my += (p.position().getY() * p.getCurrentState().mass());
                mz += (p.position().getZ() * p.getCurrentState().mass());

                continue;
            }
        }

        if (particles.size() > 1) {
            centerOfMass.set(mx, my, mz);
            centerOfMass.scale(1d / mass);

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

    public void solve(double target, IParticle p) {
        if (isExternal()) {
            for (IParticle ppp : particles) {
                if (ppp != p) {
                    p.calculateForces(ppp);
                }
            }
        } else {
            double theta = boundary.getWidthX() / centerOfMass.getDistance(p.getCurrentState().position());

            if (theta < target) {
                p.calculateForces(new OctTreeParticle(mass, centerOfMass));
            } else {
                for (BarnesHutNode n : nodes) {
                    n.solve(target, p);
                }
            }
        }
    }

    public static BarnesHutNode build(AbstractList<IParticle> particles, double boundLow, double boundHigh) {
        BarnesHutNode node = new BarnesHutNode(new Boundary(boundLow, boundHigh,boundLow, boundHigh,boundLow, boundHigh));
        node.build(particles);
        return node;
    }
}