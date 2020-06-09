package stars.physics.actions;

import stars.math.Vector3;
import stars.physics.Universe;
import stars.physics.particles.IParticle;

public class NewtonianGravity implements IForce {
    Vector3 force = new Vector3();

    @Override
    public void calculate(IParticle p1, IParticle p2, double tDelta) {
        // F = G * (m1 * m2) / d^2
        Vector3 distanceV = p1.getCurrentState().position().getDistanceVector(p2.position());
        Vector3 unitV = distanceV.getUnitVector();
        double distance = distanceV.getMagnitude();
        double massProduct = p1.getCurrentState().mass() * p2.getCurrentState().mass();
        double force = (Universe.G * massProduct) / (distance * distance);
        unitV.scale(force);

        this.force.add(unitV);
    }

    @Override
    public void resetForce() {
        force.set(0, 0, 0);
    }

    @Override
    public Vector3 getForce() {
        return force;
    }

    @Override
    public String toString() {
        return "f = " + force.toString() + " N";
    }
}