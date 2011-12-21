package stars.physics.actions;

import java.util.AbstractList;

import stars.physics.IAction;
import stars.physics.IParticle;
import stars.physics.Vector1x3;

public class Gravity implements IAction {
	public static final double GRAVITATIONAL_ACCEL = 9.8; // m/s
	public static final long CYCLES_BEFORE_MODE_SWITCH = 100000;
	public long cycles = 0;
	public double vx, vy, vz, 
		    	  sx, sy, sz;
    
    public void execute(double step, AbstractList<IParticle> c) {
    	final Vector1x3 a = new Vector1x3();
    	
    	cycles = (cycles + 1) % CYCLES_BEFORE_MODE_SWITCH;
        
    	sx = Math.sin(((double) cycles / 50000) * (2 * Math.PI));
    	sy = Math.sin(((double) cycles / 10000) * (2 * Math.PI));
    	sz = Math.sin(((double) cycles / 50000) * (2 * Math.PI));
    	
    	a.set(sx, sy, sz);
        a.scale(GRAVITATIONAL_ACCEL * step);
    	
        for (int i = 0; i < c.size(); i++) {
            IParticle p = c.get(i);
            Vector1x3 velocity = p.getVelocity();
            velocity.add(a);
        }
    }
}
