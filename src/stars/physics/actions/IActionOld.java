package stars.physics.actions;

import java.util.AbstractList;

import stars.physics.particles.IParticleOld;

public interface IActionOld {
    public void execute(double step, AbstractList<IParticleOld> c);
}
