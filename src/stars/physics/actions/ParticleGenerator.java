package stars.physics.actions;

import java.util.AbstractList;

import stars.physics.particles.IParticleOld;

public class ParticleGenerator implements IActionOld {
    private Class<? extends IParticleOld> _aClass;
    private int _maxParticles;
    private int _maxGenerationRate;

    public ParticleGenerator(Class<? extends IParticleOld> particleClass,
            int maxParticles, int maxRate) {
        _aClass = particleClass;
        _maxParticles = maxParticles;
        _maxGenerationRate = maxRate;
    }

    @Override
    public void execute(double step, AbstractList<IParticleOld> c) {
        IParticleOld p = null;

        for (int i = 0; i < _maxGenerationRate && c.size() < _maxParticles; i++) {
            try {
                p = _aClass.newInstance();
                c.add(randomizeParticle(p));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    double x = 0d;

    public IParticleOld randomizeParticle(IParticleOld p) {
        p.setMass(Math.random() * (1e20));
        // p.setRadius(Math.random() * 500);
        p.setRadius(500);
        p.getPosition().setRandom(12500);
        p.getVelocity().setRandom(500);

        double d = 0.0d;
        while (d <= 0.90d || d > 0.95d) {
            d = Math.random();
        }
        p.setElastisity(d);

        this.x += 1d;

        return p;
    }
}
