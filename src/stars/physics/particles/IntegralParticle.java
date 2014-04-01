package stars.physics.particles;

import java.util.AbstractList;

import stars.physics.Vector1x3;

public class IntegralParticle implements IParticleOld {

    @Override
    public void update(double timeDelta, AbstractList<IParticleOld> c) {

        /*
         * particles 0..n
         * 
         * p = this pN = target
         * 
         * Fn = G * m1*m2 / r^2 p = int p + dv / dt r = pN - p r^2 = (pN - p)^2
         * Fn = int (G * (m1*m2) / r^2) dr F = sum ( G * (m1 * m2) / d^2 )
         */
        // TODO Auto-generated method stub

    }

    @Override
    public void addForce(Vector1x3 v) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setMass(double d) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRadius(double d) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setForce(Vector1x3 v) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setElastisity(double d) {
        // TODO Auto-generated method stub

    }

    @Override
    public Vector1x3 getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector1x3 getVelocity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector1x3 getAcceleration() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector1x3 getForce() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getMass() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getRadius() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getElastisity() {
        // TODO Auto-generated method stub
        return 0;
    }

}
