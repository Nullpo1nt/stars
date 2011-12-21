package stars.physics.actions;

import java.util.AbstractList;

import stars.physics.IAction;
import stars.physics.IParticle;
import stars.physics.Universe;
import stars.physics.Vector1x3;

public class Boundry implements IAction {
	Universe _universe;
	
	public Boundry(Universe universe) {
		_universe = universe;
	}
	
	public void execute(double step, AbstractList<IParticle> c) {
		for (int i = 0; i < c.size(); i++) {
            IParticle p = c.get(i);
            Vector1x3 position = p.getPosition();
            double bound = _universe.getBoundRadius();
            
            if (Math.abs(position.x) > bound ||
                Math.abs(position.y) > bound ||
                Math.abs(position.z) > bound) {
                c.remove(i);
            }
        }
	}

}
