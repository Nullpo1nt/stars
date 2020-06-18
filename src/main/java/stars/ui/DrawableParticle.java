package stars.ui;

import java.awt.Color;
import java.util.AbstractCollection;
import java.util.ConcurrentModificationException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import stars.math.Tuple3;
import stars.math.Vector3;
import stars.physics.particles.IParticle;
import stars.physics.particles.IParticleState;

public class DrawableParticle {
    boolean                             _drawTail     = true;
    boolean                             _drawVelocity = true;
    boolean                             _drawAccel    = true;
    boolean                             _drawRadius   = true;

    int                                 _maxTailSize  = 100;

    Hashtable<Long, LinkedList<Tuple3>> particleReg   = new Hashtable<Long, LinkedList<Tuple3>>();

    public DrawableParticle() {
    }

    protected LinkedList<Tuple3> getTail(IParticleState particle) {
        LinkedList<Tuple3> tail = particleReg.get(particle.particleId());

        if (tail == null) {
            tail = new LinkedList<>();
            particleReg.put(particle.particleId(), tail);
        }

        return tail;
    }

    public void draw(GraphicsWrapper g, AbstractCollection<IParticle> particles) {
        try {
            for (IParticle particle : particles) {
                draw(g, particle.getCurrentState());
            }
        } catch (ConcurrentModificationException e) {
            System.out
                    .println("Opps: Someone modified the particle list while rendering");
        }
    }

    Tuple3 target = new Vector3();

    public void draw(GraphicsWrapper g, IParticleState particle) {
        final Vector3 position = particle.position();

        if (_drawTail) {
            float colorInc = 128 / (float) _maxTailSize;
            float color = colorInc;

            LinkedList<Tuple3> tail = getTail(particle);

            tail.add(new Vector3(position));
            if (tail.size() > _maxTailSize) {
                tail.removeFirst();
            }

            Iterator<Tuple3> i = tail.iterator();
            Tuple3 start = null, end = null;
            while (i.hasNext()) {
                start = i.next();
                if (end != null) {
                    g.g().setColor(new Color((int) color, (int) color, (int) color));
                    g.drawLine((int) (start.getX()), (int) (start.getY()),
                            (int) (end.getX()), (int) (end.getY()));
                }
                end = start;
                color += colorInc;
            }
        }

        if (_drawVelocity) {
            g.g().setColor(Color.GREEN);
            g.drawVector(position, particle.velocity(), 5e1d);
        }

        if (_drawAccel) {
            g.g().setColor(Color.MAGENTA);
            g.drawVector(position, particle.acceleration(), 1e6d);
        }

        if (_drawRadius) {
            // Logarithm z-scalling of color
            // float red = (float) Math.min(1d, Math.log(1d + Math.max(0d, position.getZ() * -1d)) / Math.log(1d + 1e8));
            // float blue = (float) Math.min(1d, Math.log(1d + Math.max(0d, position.getZ())) / Math.log(1d + 1e8));

            // Linear z-scalling of color
            float red = (float) Math.min(1d, Math.max(0d, position.getZ() * -1d) / 1e8d);
            float blue = (float) Math.min(1d, Math.max(0d, position.getZ()) / 1e8d);

            g.g().setColor(new Color(1-blue, 1 - blue - red, 1-red));
            g.fillCircle(position.getX(), position.getY(), particle.radius() / (g.scale / 0.5));
        }
    }
}
