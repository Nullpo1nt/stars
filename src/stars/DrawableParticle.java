package stars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;

import stars.physics.Vector1x3;
import stars.physics.particles.IParticle;

public class DrawableParticle {
    boolean _drawTail = false, 
            _drawVelocity = false, 
            _drawAccel = false,
            _drawRadius = false;
    int _maxTailSize = 100;
    IParticle particle;

    LinkedList<Vector1x3> tail = new LinkedList<Vector1x3>();

    public DrawableParticle() {
    }

    public void draw(Graphics g, double scale, IParticle p) {
        particle = p;

        draw((Graphics2D) g);
    }

    public void draw(Graphics2D g) {
        Vector1x3 position = particle.position();
        int x = (int) position.x, y = (int) position.y;

        if (_drawTail) {
            float colorInc = 128 / (float) _maxTailSize;
            float color = colorInc;

            tail.add(new Vector1x3(position));
            if (tail.size() > _maxTailSize) {
                tail.removeFirst();
            }

            Iterator<Vector1x3> i = tail.iterator();
            Vector1x3 start = null, end = null;
            while (i.hasNext()) {
                start = i.next();
                if (end != null) {
                    g.setColor(new Color((int) color, (int) color, (int) color));
                    g.drawLine((int) (start.x), (int) (start.y), (int) (end.x),
                            (int) (end.y));
                }
                end = start;
                color += colorInc;
            }
        }

        if (_drawVelocity) {
            g.setColor(Color.GREEN);
            g.drawLine(x, y, x + (int) (particle.velocity().x), y
                    + (int) (particle.velocity().y));
        }

        // if (_drawAccel) {
        // g.setColor(Color.MAGENTA);
        // g.drawLine(x,y,x+(int)(particle.getAcceleration().x *
        // 20),y+(int)(particle.getAcceleration().y * 20));
        // }

        if (_drawRadius) {
            g.setColor(Color.DARK_GRAY);
             int radius = (int) (10);
            // int radius = (int) ((Math.abs(particle.position().z + 20000) / 40000) * particle.getRadius());
            int diameter = radius * 2;
            g.drawOval(x - radius, y - radius, diameter, diameter);
        }

        g.setColor(Color.WHITE);
        g.drawLine(x, y, x, y);
    }
}
