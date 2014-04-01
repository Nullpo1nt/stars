package stars.physics.actions;

import java.util.AbstractList;

import stars.physics.Universe;
import stars.physics.Vector1x3;
import stars.physics.particles.IParticleOld;

public class Boundry implements IActionOld {
    Universe _universe;

    public Boundry(Universe universe) {
        _universe = universe;
    }

    @Override
    public void execute(double step, AbstractList<IParticleOld> c) {
        for (int i = 0; i < c.size(); i++) {
            IParticleOld p = c.get(i);
            Vector1x3 position = p.getPosition();
            double bound = _universe.getBoundRadius();

            if (Math.abs(position.x) > bound || Math.abs(position.y) > bound
                    || Math.abs(position.z) > bound) {
                c.remove(i);
            }
        }
    }

}
