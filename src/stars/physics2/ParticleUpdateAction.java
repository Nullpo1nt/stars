package stars.physics2;

import java.util.Collection;
import java.util.Iterator;

public class ParticleUpdateAction implements ActionI {
    public void execute(double step, Collection<ParticleI> c) {
        Iterator i = c.iterator();
        
        while (i.hasNext()) {
            ParticleI p = (ParticleI) i.next();
            
            p.update(step, c);
        }
    }
}
