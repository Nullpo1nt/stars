package stars.physics.actions;

import java.util.AbstractList;

import stars.physics.particles.IParticle;

public class ParticleGenerator {
    private Class<? extends IParticle> _aClass;
    
    private int _maxParticles;
    private int _maxGenerationRate;

    public double weightLimit = 1e18d;
    public double positionLimit = 1e6d;  // x2
    public double velocityLimit = 100d;
    
    public ParticleGenerator(Class<? extends IParticle> particleClass,
            int maxParticles, int maxRate) {
        _aClass = particleClass;
        _maxParticles = maxParticles;
        _maxGenerationRate = maxRate;
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
