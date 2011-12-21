package stars.physics.actions;

import java.util.AbstractList;
import stars.physics.IAction;
import stars.physics.IParticle;
import stars.physics.Universe;
import stars.physics.Vector1x3;

public class Attraction implements IAction {
	private Vector1x3 _distance = new Vector1x3(),
		_unitVector = new Vector1x3();

    /**
     * F = G * (p.mass * s.mass) / d^2
     * @param s
     * @return
     */
    public Vector1x3 calculateForce(IParticle p, IParticle s) {
        Vector1x3 dVec = p.getPosition().getDistanceVector(s.getPosition(), _distance),
                uVec = dVec.getUnitVector(_unitVector);
        double dis  = dVec.getMagnitude(),
               massProduct  = p.getMass() * s.getMass();  // m1 * m2
        
        dis = dis * dis;  // d^2
        massProduct = massProduct / dis;  // (m1*m2) / d^2
        //massProduct = Universe.G * massProduct;        

	// Scale unit vector and return
        uVec.scale(massProduct);
        
        return uVec;
    }
    
    /**
     * 
     * @param listOfStars
     * @param pos
     */
    public void calculateForce(IParticle p, AbstractList<IParticle> c, int pos) {
//        Vector1x3 force;
//        
//        for (int i = pos; i < c.size(); i++) {
//            IParticle s = listOfStars.get(i);
//            
//            if (s != null && s != this) {
//                force = calculateForce(p,s);
//                
//                // Add force to p
//                p.getForce().add(force);
//
//		// Invert force and add to s
//                force.scale(-1);
//                s.getForce().add(force);
//            }
//        }
    }
	
	public void execute(double t, AbstractList<IParticle> c) {
					//		for (int i = 0; i < c.size(); i++) {
					//			update(p, t, c);
					//		}
	}
}
