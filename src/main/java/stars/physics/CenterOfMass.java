package stars.physics;

import java.util.Collection;

import stars.math.Vector3;
import stars.physics.particles.IParticle;

public class CenterOfMass extends Vector3 {
    double mass;

    public CenterOfMass(double x, double y, double z, double m) {
        super(x, y, z);

        mass = m;
    }

    public static CenterOfMass calculate(Collection<IParticle> particles) {
        double mx = 0d;
        double my = 0d;
        double mz = 0d;
        double mtotal = 0d;

        for (IParticle p : particles) {
            double mass = p.getCurrentState().mass();
            mtotal += mass;
            mx += (mass * p.position().getX());
            my += (mass * p.position().getY());
            mz += (mass * p.position().getZ());
        }

        return new CenterOfMass((mx / mtotal), (my / mtotal), (mz / mtotal), mtotal);
    }

    public double getMass() {
        return mass;
    }
}