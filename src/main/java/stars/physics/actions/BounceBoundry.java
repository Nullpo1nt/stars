package stars.physics.actions;

import java.util.AbstractList;

import stars.physics.Universe;
import stars.physics.Vector1x3;
import stars.physics.particles.IParticleOld;

public class BounceBoundry implements IActionOld {
    double BOUNDARY;
    Universe _universe;

    public BounceBoundry(Universe universe, int boundryOffset) {
        _universe = universe;
        BOUNDARY = universe.getBoundRadius() + boundryOffset;
    }

    @Override
    public void execute(double step, AbstractList<IParticleOld> c) {
        for (int i = 0; i < c.size(); i++) {
            IParticleOld p = c.get(i);
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
