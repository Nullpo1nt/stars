package stars.physics.actions;

import java.util.AbstractList;

import stars.physics.Universe;
import stars.physics.Vector1x3;
import stars.physics.particles.IParticleOld;

public class BounceBoundryEliptical implements IActionOld {
    double radius;

    public BounceBoundryEliptical(Universe universe, double boundryOffset) {
        radius = universe.getBoundRadius();// + boundryOffset;
    }

    public double timeToCollision(IParticleOld p) {
        Vector1x3 pos = p.getPosition();
        Vector1x3 vel = p.getVelocity();

        // Particle distance from radius
        double dis = radius - pos.getMagnitude();

        // Calc time to intercept
        double t = dis / vel.getMagnitude();

        return t;
    }

    @Override
    public void execute(double step, AbstractList<IParticleOld> c) {
        for (int i = 0; i < c.size(); i++) {
            IParticleOld p = c.get(i);
            Vector1x3 position = p.getPosition();

            double tt = timeToCollision(p);

            if (tt < step) {
                Vector1x3 velocity = p.getVelocity();

                // uR = uV + 2uNorm
                Vector1x3 uv = velocity.getUnitVector();

                // 2 uNorm
                Vector1x3 up = position.getUnitVector();
                up.scale(-2.0d);

                uv.add(up);
                uv.getUnitVector(up);
                up.scale(velocity.getMagnitude());

                velocity.set(up);

                double bounceEfficiency = p.getElastisity();
                velocity.scale(bounceEfficiency);

                position.x += velocity.x * step;
                position.y += velocity.y * step;
                position.z += velocity.z * step;

                if (timeToCollision(p) < step) {
                    velocity.set(0, 0, 0);
                }
            }
        }
    }

}
