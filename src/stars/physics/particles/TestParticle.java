package stars.physics.particles;

import java.util.ArrayList;

import stars.physics.Vector1x3;
import stars.physics.actions.IForce;
import stars.physics.actions.NewtonianGravity;

public class TestParticle implements IParticle {
    private boolean _destroy = false;

    private Vector1x3 _position;
    private Vector1x3 _velocity;
    private double _mass;
    private double _radius;
    
    public Vector1x3 accel;
    
    private double density = 5515; // kg/m^3
    
    private IForce[] _forces;
    
    private ArrayList<IParticle> _collisions;

    public TestParticle() {
        _position = new Vector1x3();
        _velocity = new Vector1x3();

        _forces = new IForce[1];
        _forces[0] = new NewtonianGravity();
        
        _collisions = new ArrayList<>();
    }

    @Override
    public double mass() {
        return _mass;
    }
    
    public double radius() {
        return _radius;
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

        accel = new Vector1x3(force);
        
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
        return "mass = " + _mass + " kg" + ", radius = " + _radius + "m, density = " + density + "kg/m^3, "
                + "\n\t p = " + _position.toString() + " m"
                + ",\n\t v = " + _velocity.toString() + " m/s";
    }
    
    @Override
    public void randomize(double massBound, double positionBound,
            double velocityBound) {
        
        _mass = Math.random() * massBound;
        _radius = Math.pow((_mass * 3) / (density * 4 * Math.PI), 1d/3d);
        
        _position.setRandom(positionBound);
        _velocity.setRandom(velocityBound);
    }

    @Override
    public void addCollision(IParticle p) {
        _collisions.add(p);
    }

    @Override
    public ArrayList<IParticle> getCollisions() {
        return _collisions;
    }

    @Override
    public void merge(IParticle p) {
        
        double tmass = _mass + p.mass();
        
        _velocity.scale(_mass / tmass);
        p.velocity().scale(p.mass() / tmass);
        _velocity.add(p.velocity());

        //double oldM = _mass;
        
        _mass += p.mass();
        
        double area = (Math.PI * Math.pow(p.radius(),2)) + 
                (Math.PI * Math.pow(_radius, 2));
        
        _radius = Math.sqrt(area / Math.PI);
        //_radius += p.radius();
        

    }
}