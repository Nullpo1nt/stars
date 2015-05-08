package stars.physics.forces;

import java.util.ArrayList;

import stars.math.Vector3;
import stars.physics.particles.IParticleState;

public class Collision implements IForce {

    private final Vector3             zero        = new Vector3(0d, 0d, 0d);

    private ArrayList<IParticleState> _collisions = new ArrayList<>();

    public void addCollision(IParticleState p) {
        _collisions.add(p);
    }

    public ArrayList<IParticleState> getCollisions() {
        return _collisions;
    }

    @Override
    public void reset() {
        _collisions.clear();
    }

    @Override
    public Vector3 getAcceleration() {
        return zero;
    }

    @Override
    public void calculate(IParticleState p1, IParticleState p2) {
        double dis = Math.abs(p1.position().getDistance(p2.position()));

        if (dis <= p1.radius() || dis <= p2.radius()) {
            addCollision(p2);
            System.out.println("Collision between:\n\tP1 " + this + "\n\tP2 "
                    + p2);
            System.out.println(dis);
        }
    }
}
