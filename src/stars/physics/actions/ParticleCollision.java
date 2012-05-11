package stars.physics.actions;

import java.util.AbstractList;

import stars.physics.IAction;
import stars.physics.IParticle;

public class ParticleCollision implements IAction {
	public void execute(double step, AbstractList<IParticle> c) {
		for(int i = 0; i < c.size(); i++) {
			IParticle p = c.get(i);
			
			for (int j = i+1; j < c.size(); j++) {
				IParticle pp = c.get(j);
				double d = p.getPosition().getDistance(pp.getPosition());
				
				if (d < p.getRadius() + pp.getRadius()) {
//					Vector1x3 velocity = p.getVelocity();				
//					
//					// uR = uV + 2uNorm
//					Vector1x3 uv = velocity.getUnitVector();
//
//					// 2 uNorm
//					Vector1x3 up = position.getUnitVector();
//					up.scale(-2.0d);
//
//					uv.add(up);
//					uv.getUnitVector(up);
//					up.scale(velocity.getMagnitude());
//
//					velocity.set(up);
//					
//					double bounceEfficiency = p.getElastisity();
//					velocity.scale(bounceEfficiency);
//
//					position.x += velocity.x * step;
//					position.y += velocity.y * step;
//					position.z += velocity.z * step;
				}
			}
		}
	}
}
