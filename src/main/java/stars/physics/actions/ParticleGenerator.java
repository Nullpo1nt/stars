package stars.physics.actions;

import java.util.AbstractList;

import stars.physics.particles.IParticle;

public class ParticleGenerator {
    private Class<? extends IParticle> _aClass;

    private int                        _maxParticles;
    private int                        _maxGenerationRate;

    public double                      weightLimit;
    public double                      positionLimit;
    public double                      velocityLimit;

    public ParticleGenerator(Class<? extends IParticle> particleClass,
            int maxParticles, int maxRate, double weightLimit,
            double positionLimit, double velocityLimit) {
        _aClass = particleClass;
        _maxParticles = maxParticles;
        _maxGenerationRate = maxRate;
        this.weightLimit = weightLimit;
        this.positionLimit = positionLimit;
        this.velocityLimit = velocityLimit;
    }

    public ParticleGenerator(Class<? extends IParticle> particleClass,
            int maxParticles, int maxRate) {
        this(particleClass, maxParticles, maxRate, 1e20d, 1e6d, 100d);
    }

    public void execute(double step, AbstractList<IParticle> c) {
        IParticle p = null;

        for (int i = 0; i < _maxGenerationRate && c.size() < _maxParticles; i++) {
            try {
                p = _aClass.newInstance();
                p = randomizeParticle(p);
                c.add(p);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public IParticle randomizeParticle(IParticle p) {
        p.randomize(weightLimit, positionLimit, velocityLimit);

        return p;
    }
}
