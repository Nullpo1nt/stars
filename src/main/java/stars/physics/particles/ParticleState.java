package stars.physics.particles;

import stars.math.Vector3;

/**
 * Instantaneous state of a particle.
 */
public class ParticleState implements IParticleState {
    private long    id;
    private double  time;        // s

    private double  mass;        // kg
    private double  radius;      // m

    private Vector3 position;    // m
    private Vector3 velocity;    // m/s
    private Vector3 acceleration; // m/s^2

    public ParticleState(IParticleState state) {
        set(state.particleId(), 0d, state.mass(), state.radius(), state
                .position().clone(), state.velocity().clone(), state
                .acceleration().clone());
    }

    public ParticleState(long id, double t, double m, double r, Vector3 p,
            Vector3 v, Vector3 a) {
        set(id, t, m, r, p, v, a);
    }

    public void set(long id, double t, double m, double r, Vector3 p,
            Vector3 v, Vector3 a) {
        this.id = id;
        time = t;
        mass = m;
        radius = r;
        position = p;
        velocity = v;
        acceleration = a;
    }

    public double time() {
        return time;
    }

    @Override
    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    @Override
    public Vector3 position() {
        return position;
    }

    @Override
    public Vector3 velocity() {
        return velocity;
    }

    @Override
    public Vector3 acceleration() {
        return acceleration;
    }

    public IParticleState merge(IParticleState ps) {
        double m = mass() + ps.mass();

        Vector3 p = new Vector3(position()).scale(mass() / m);
        Vector3 p2 = new Vector3(ps.position()).scale(ps.mass() / m);
        p.add(p2);

        Vector3 v1 = new Vector3(velocity()).scale(mass() / m);
        Vector3 v2 = new Vector3(ps.velocity()).scale(ps.mass() / m);
        Vector3 v = v1.add(v2);

        double area = (Math.PI * Math.pow(ps.radius(), 2))
                + (Math.PI * Math.pow(radius(), 2));

        double r = Math.sqrt(area / Math.PI);

        return new ParticleState(id, time, m, r, p, v, new Vector3());
    }

    public String toString() {
        return "m=" + mass + " kg" + ", r=" + radius
                + " m"
                // + ", d=" + density + "kg/m^3"
                + ", p=" + position.toString() + " m" + ", v="
                + velocity.toString() + " m/s" + ", a="
                + acceleration.toString() + " m/s^2";
    }

    @Override
    public long particleId() {
        return id;
    }

}
