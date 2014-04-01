package stars.physics.actions;

import stars.physics.Vector1x3;
import stars.physics.particles.IParticle;
import stars.physics.particles.NewtonParticle;

public class NewtonianGravity implements IAction {
	Vector1x3 force = new Vector1x3();
	
	@Override
	public void calculate(IParticle p1, IParticle p2, double tDelta) {
		// F = G * (m1 * m2) / d^2
        Vector1x3 distanceV = p2.position().getDistanceVector(p1.position());
		Vector1x3 unitV = distanceV.getUnitVector();
		double distance = distanceV.getMagnitude();
		double massProduct = p1.mass() * p2.mass();
		double force = (NewtonParticle.G * massProduct) / (distance * distance);
		unitV.scale(force);
		
		this.force.add(unitV);
	}
	
	@Override
	public void resetForce() {
		force.set(0,0,0);
	}
	
	@Override
	public Vector1x3 getForce() {
		return force;
	}
	
	public String toString() {
		return "f = " + force.toString() + " N";
	}
}