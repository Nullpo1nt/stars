package stars.physics.particles;

import stars.physics.Vector1x3;
import stars.physics.actions.IAction;
import stars.physics.actions.NewtonianGravity;

public class TestParticle implements IParticle {
    private Vector1x3 _position;
    private Vector1x3 _velocity;
    private double _mass;

    private IAction[] _actors;

    public TestParticle() {
        _position = new Vector1x3(Math.random() * 1000d, Math.random() * 100d,
                Math.random() * 100d);
        _velocity = new Vector1x3(Math.random() * 100d, Math.random() * 100d,
                Math.random() * 100d);
        _mass = Math.random() * 10000000d;

        _actors = new IAction[1];
        _actors[0] = new NewtonianGravity();
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
        for (IAction f : _actors) {
            f.calculate(this, p, tDelta);
        }
    }

    @Override
    public void update(double time) {
        Vector1x3 force = new Vector1x3();

        for (IAction action : _actors) {
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
    public IAction[] getActors() {
        return _actors;
    }

    public String toString() {
        return "mass = " + _mass + " kg" + ", p = " + _position.toString()
                + " m" + ", v = " + _velocity.toString() + " m/s";
    }
}