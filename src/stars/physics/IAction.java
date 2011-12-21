package stars.physics;

import java.util.AbstractList;

public interface IAction {
    public void execute(double step, AbstractList<IParticle> c);
}
