package stars.physics.nbody.solver;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import stars.physics.particles.IParticle;

/**
 * h = step size y{n+1} = y{n} + h f(t{n}, y{n})
 */
public class EulerMethod implements NBodySolver {
    public List<IParticle> calculate(List<IParticle> initial, double deltaT) {
        ArrayList<IParticle> result = new ArrayList<>();

//        for (IParticle particle : initial) {
//            Vector1x3 forceV = new Vector1x3();
//
//            for (IParticle p : initial) {
//                if (p != particle) {
//                    Vector1x3 distanceV = particle.position().getDistanceVector(p.position());
//                    Vector1x3 distanceUnitV = distanceV.getUnitVector();
//                    double distance = distanceV.getMagnitude();
////                    double force = p.mass() / (distance * distance);
//                    distanceUnitV.scale(force);
//                    forceV.add(distanceUnitV);
//                }
//            }
//
//            forceV.scale(Universe.G);
//            forceV.scale(deltaT);
//
//            Vector1x3 velocity = new Vector1x3(particle.velocity());
//            velocity.add(forceV);
//
//            Vector1x3 position = new Vector1x3(velocity);
//            position.scale(deltaT);
//            position.add(particle.position());
//
//            //result.add(new IParticle(particle, position, velocity));
//        }

        return result;
    }

    @Override
    public void solve(AbstractList<IParticle> initialParticleStates,
            double timeDelta) {
//        List<KParticle> currentParticleList = new ArrayList<KParticle>();
//
//        for (IParticle p : initialParticleStates) {
//            currentParticleList.add(new KParticle(new JParticle(p.mass()), p
//                    .position(), p.velocity()));
//        }
//
//        currentParticleList = calculate(currentParticleList, timeDelta);
//        
//        for (int i = 0; i < currentParticleList.size(); i++) {
//            IParticle p = initialParticleStates.get(i);
//            p.position().set(currentParticleList.get(i).position());
//            p.velocity().set(currentParticleList.get(i).velocity());
//        }

    }
}
