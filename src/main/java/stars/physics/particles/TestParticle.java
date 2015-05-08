package stars.physics.particles;

import java.util.concurrent.atomic.AtomicLong;

import stars.math.Vector3;
import stars.physics.forces.IForce;
import stars.physics.forces.NewtonianGravity;

public class TestParticle implements IParticle {
    private static final AtomicLong idCount  = new AtomicLong(0L);
    private final long              _id;

    private boolean                 _destroy = false;

    private ParticleState           _currentState;
    private ParticleState           _nextState;

    private IForce[]                _forces;

    private final double            density;

    public TestParticle() {
        _id = idCount.getAndIncrement();
        _forces = new IForce[1];
        _forces[0] = new NewtonianGravity();

        // density = 5515d; // kg/m^3 (Earth)
        density = 8e16d; // kg/m^3 (Neutron Star (low estimate))
    }

    public double mass() {
        return _currentState.mass();
    }

    public Vector3 velocity() {
        return _currentState.velocity();
    }

    @Override
    public double radius() {
        return _currentState.radius();
    }

    @Override
    public Vector3 position() {
        return _currentState.position();
    }

    public void resetForces() {
        for (IForce f : _forces) {
            f.reset();
        }
    }

    @Override
    public void calculateForces(IParticle p) {
        for (IForce force : _forces) {
            force.calculate(getCurrentState(), p.getCurrentState());
        }
    }

    @Override
    public void update(double timeDelta) {
        // Euler Method...
        Vector3 a = new Vector3();

        for (IForce force : _forces) {
            a.add(force.getAcceleration());
        }

        // Calculate new velocity using acceleration
        Vector3 v = new Vector3(a).scale(timeDelta).add(velocity());

        // Calculate new position from velocity
        Vector3 p = new Vector3(v).scale(timeDelta).add(
                _currentState.position());

        _nextState.set(_id, timeDelta, _currentState.mass(),
                _currentState.radius(), p, v, a);

        switchStates(_nextState);

        resetForces();
    }

    private void switchStates(ParticleState next) {
        _nextState = _currentState;
        _currentState = next;
    }

    // @Override
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
        return "p" + _id + "(" + _currentState.toString() + ")";
    }

    /**
     * Calculates the radius of a perfect sphere for a given mass and density.
     * 
     * r = (3m / 4PId)^(1/3)
     * 
     * @param mass
     *            (kg)
     * @param density
     *            (kg/m^3)
     * @return radius (m)
     */
    private double massDensityToRadius(double mass, double density) {
        return Math.pow((mass * 3) / (density * 4 * Math.PI), 1d / 3d);
    }

    @Override
    public void randomize(double massBound, double positionBound,
            double velocityBound) {
        double mass = Math.random() * massBound;
        double radius = massDensityToRadius(mass, density);

        _currentState = new ParticleState(_id, 0L, mass, radius,
                new Vector3().setRandomRadius(positionBound),
                new Vector3().setRandomRadius(velocityBound), new Vector3());
        _nextState = new ParticleState(_currentState);
    }

    @Override
    public void merge(IParticleState ps) {
        setCurrentState(getCurrentState().merge(ps));
    }

    @Override
    public IParticleState getCurrentState() {
        return _currentState;
    }

    @Override
    public void setCurrentState(IParticleState state) {
        _currentState = (ParticleState) state;
    }
}