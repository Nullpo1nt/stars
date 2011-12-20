package stars.physics2;

import java.util.Collection;

public interface ActionI {
    public void execute(double step, Collection<ParticleI> c);
}
