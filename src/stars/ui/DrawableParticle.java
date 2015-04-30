package stars.ui;

import java.awt.Color;
import java.awt.Graphics2D;
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
    boolean                             _drawTail     = false;
    boolean                             _drawVelocity = true;
    boolean                             _drawAccel    = false;
    boolean                             _drawRadius   = true;

    int                                 _maxTailSize  = 100;

    Hashtable<Long, LinkedList<Tuple3>> particleReg   = new Hashtable<Long, LinkedList<Tuple3>>();

    public DrawableParticle() {
    }

    protected LinkedList<Tuple3> getTail(IParticleState particle) {
        LinkedList<Tuple3> tail = particleReg.get(particle);

        if (tail == null) {
            tail = new LinkedList<>();
            particleReg.put(particle.particleId(), tail);
        }

        return tail;
    }

    int _width;
    int _height;

    public void draw(Graphics2D g, int width, int height, double scale,
            AbstractCollection<IParticle> particles) {
        _width = width;
        _height = height;

        try {
            for (IParticle particle : particles) {
                draw(g, scale, particle.getCurrentState());
            }
        } catch (ConcurrentModificationException e) {
            System.out
                    .println("Opps: Someone modified the particle list while rendering");
        }

        // // Prune tail collection
        // for (LinkedList<Vector1x3> tail : particleReg.values()) {
        // if (tail.size() > _maxTailSize) {
        // tail.removeFirst();
        //
        // }
        // }
    }

    Tuple3 target = new Vector3();

    private int scaleX(double x, double z, double scale) {
        return (int) ((x - target.getX()) * scale) + (_width / 2);
    }

    private int scaleY(double y, double z, double scale) {
        return (int) ((y - target.getY()) * scale) + (_height / 2);
    }

    public void draw(Graphics2D g, double scale, IParticleState particle) {
        Vector3 position = particle.position();
        int x = scaleX(position.getX(), position.getZ(), scale);
        int y = scaleY(position.getY(), position.getZ(), scale);

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
                    g.setColor(new Color((int) color, (int) color, (int) color));
                    g.drawLine((int) (start.getX()), (int) (start.getY()),
                            (int) (end.getX()), (int) (end.getY()));
                }
                end = start;
                color += colorInc;
            }
        }

        if (_drawVelocity) {
            g.setColor(Color.GREEN);
            g.drawLine(x, y, x
                    + (int) (particle.velocity().getX() * (scale * 2)), y
                    + (int) (particle.velocity().getY() * (scale * 2)));
        }

        if (_drawAccel) {
            g.setColor(Color.MAGENTA);
            g.drawLine(
                    x,
                    y,
                    x
                            + (int) (particle.acceleration().getX() * (320000d * scale)),
                    y
                            + (int) (particle.acceleration().getY() * (320000d * scale)));
        }

        if (_drawRadius) {
            float color = Math.min(1f, Math.max(0f,
                    (float) ((position.getZ() + 1000000d) / 2000000d)));
            g.setColor(new Color(color, color, color));

            int radius = (int) (particle.radius() * scale);
            // int radius = (int) ((Math.abs(particle.position().getZ() + 1e6d)
            // /
            // 1e12) * particle.radius()*2);
            int diameter = (radius * 2);
            g.drawOval(x - radius, y - radius, diameter, diameter);
        }

        // g.setColor(Color.WHITE);
        // g.drawLine(x, y, x, y);
    }
}
