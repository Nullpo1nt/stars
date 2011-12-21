package stars.physics.actions;

import java.util.AbstractList;

import stars.physics.IAction;
import stars.physics.IParticle;
import stars.physics.Universe;
import stars.physics.Vector1x3;

public class BounceBoundry implements IAction {
	double BOUNDARY;
	Universe _universe;
	
	public BounceBoundry(Universe universe, int boundryOffset) {
		_universe = universe;
		BOUNDARY = universe.getBoundRadius() + boundryOffset;
	}
	
	
	public void execute(double step, AbstractList<IParticle> c) {
		for(int i = 0; i < c.size(); i++) {
			IParticle p = c.get(i);
			Vector1x3 position = p.getPosition();
			Vector1x3 velocity = p.getVelocity();
			double bounceEfficiency = p.getElastisity();
			
			if (Math.abs(position.x) > BOUNDARY) {
				velocity.x = -1 * velocity.x * bounceEfficiency;
				position.x += velocity.x * step;
			}
			
			if (Math.abs(position.y) > BOUNDARY) {
				velocity.y = -1 * velocity.y * bounceEfficiency;
				position.y += velocity.y * step;
			}
			
			if (Math.abs(position.z) > BOUNDARY) {
				velocity.z = -1 * velocity.z * bounceEfficiency;
				position.z += velocity.z * step;
			}
		}
	}

}
