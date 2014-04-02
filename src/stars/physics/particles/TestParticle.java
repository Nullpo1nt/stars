package stars.physics.particles;

import stars.physics.Vector1x3;
import stars.physics.actions.IForce;
import stars.physics.actions.NewtonianGravity;

public class TestParticle implements IParticle {
    private boolean _destroy = false;

    private Vector1x3 _position;
    private Vector1x3 _velocity;
    private double _mass;

    private IForce[] _forces;

    public TestParticle() {
        _position = new Vector1x3();
        _position.setRandom(12500);

        _velocity = new Vector1x3();
        _velocity.setRandom(500);

        _mass = Math.random() * 1e20d;

        _forces = new IForce[1];
        _forces[0] = new NewtonianGravity();
    }

    @Override
    public double mass() {
        return _mass;
    }

    @Override
    public Vector1x3 position() {
        return _position;
    }

    @Override
    public Vector1x3 velocity() {
        return _velocity;
    }

    @Override
    public void calculate(IParticle p, double tDelta) {
        for (IForce f : _forces) {
            f.calculate(this, p, tDelta);
        }
    }

    @Override
    public void update(double time) {
        Vector1x3 force = new Vector1x3();

        for (IForce action : _forces) {
            force.add(action.getForce());
            action.resetForce();
        }

        // Scale to acceleration
        force.scale(1 / _mass);

        // Scale to velocity
        force.scale(time);
        _velocity.add(force);

        // Update position
        Vector1x3 tempVelocity = new Vector1x3(_velocity);
        tempVelocity.scale(time);
        _position.add(tempVelocity);
    }

    @Override
    public IForce[] getForces() {
        return _forces;
    }
    
    @Override
    public void markForDeletion() {
        _destroy = true;
    }
    
    public boolean isMarkedForDeletion() {
        return _destroy;
    }

    public String toString() {
        return "mass = " + _mass + " kg" + ", p = " + _position.toString()
                + " m" + ", v = " + _velocity.toString() + " m/s";
    }
}