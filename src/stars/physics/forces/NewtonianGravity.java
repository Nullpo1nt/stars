package stars.physics.forces;

import stars.math.Vector3;
import stars.physics.Universe;
import stars.physics.particles.IParticleState;

public class NewtonianGravity implements IForce {
    Vector3 force = new Vector3();

    @Override
    public void calculate(IParticleState p1, IParticleState p2) {
        // F = G * (m1 * m2) / d^2
        Vector3 distanceV = p1.position().getDistanceVector(p2.position());
        Vector3 unitV = distanceV.getUnitVector();
        double distance = distanceV.getMagnitude();
        //double massProduct = p1.mass() * p2.mass();
        double massProduct = p2.mass();
        //double force = (Universe.G * massProduct) / (distance * distance);
        double force = (massProduct) / (distance * distance);
        unitV.scale(force);

        this.force.add(unitV);
    }

    @Override
    public void reset() {
        force.set(0, 0, 0);
    }

    @Override
    public Vector3 getAcceleration() {
        return force.scale(Universe.G);
    }

    @Override
    public String toString() {
        return "f = " + force.toString() + " N";
    }
}
